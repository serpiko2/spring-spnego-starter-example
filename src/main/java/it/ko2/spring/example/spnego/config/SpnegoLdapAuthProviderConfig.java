package it.ko2.spring.example.spnego.config;

import it.ko2.spring.example.spnego.props.KerberosConfigProps;
import it.ko2.spring.example.spnego.props.LdapConfigProps;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.kerberos.authentication.KerberosAuthenticationProvider;
import org.springframework.security.kerberos.authentication.KerberosServiceAuthenticationProvider;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosClient;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosTicketValidator;
import org.springframework.security.kerberos.client.config.SunJaasKrb5LoginConfig;
import org.springframework.security.kerberos.client.ldap.KerberosLdapContextSource;
import org.springframework.security.kerberos.web.authentication.SpnegoAuthenticationProcessingFilter;
import org.springframework.security.kerberos.web.authentication.SpnegoEntryPoint;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpnegoLdapAuthProviderConfig {

  final KerberosConfigProps kerberosConfigProps;
  final LdapConfigProps ldapConfigProps;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    AuthenticationManager authManager = authManager(http);
    http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/api/**").permitAll()
        )
        .authorizeHttpRequests((request) -> request
            .requestMatchers("/**/admin", "/**/admin/**").hasRole("ADMIN"))
        .authorizeHttpRequests((request) -> request
            .requestMatchers("/**/user", "/**/user/**").permitAll())
        .formLogin((form) -> form
            .loginPage("/login")
            .permitAll()
        )
        .logout(LogoutConfigurer::permitAll)
        .addFilterBefore(spnegoAuthenticationProcessingFilter(authManager),
            BasicAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(kerberosAuthenticationProvider())
        .authenticationProvider(kerberosServiceAuthenticationProvider())
        .build();
  }

  @Bean
  public KerberosAuthenticationProvider kerberosAuthenticationProvider() {
    KerberosAuthenticationProvider provider =
        new KerberosAuthenticationProvider();
    SunJaasKerberosClient client = new SunJaasKerberosClient();
    client.setDebug(true);
    provider.setKerberosClient(client);
    provider.setUserDetailsService(ldapUserDetailsService());
    return provider;
  }

  @Bean
  public SpnegoEntryPoint spnegoEntryPoint() {
    return new SpnegoEntryPoint("/login");
  }

  @Bean
  public SpnegoAuthenticationProcessingFilter spnegoAuthenticationProcessingFilter(
      AuthenticationManager authenticationManager) {
    SpnegoAuthenticationProcessingFilter filter =
        new SpnegoAuthenticationProcessingFilter();
    filter.setAuthenticationManager(authenticationManager);
    return filter;
  }

  @Bean
  public KerberosServiceAuthenticationProvider kerberosServiceAuthenticationProvider() {
    KerberosServiceAuthenticationProvider provider =
        new KerberosServiceAuthenticationProvider();
    provider.setTicketValidator(sunJaasKerberosTicketValidator());
    provider.setUserDetailsService(ldapUserDetailsService());
    return provider;
  }

  @Bean
  public SunJaasKerberosTicketValidator sunJaasKerberosTicketValidator() {
    SunJaasKerberosTicketValidator ticketValidator =
        new SunJaasKerberosTicketValidator();
    ticketValidator.setServicePrincipal(kerberosConfigProps.servicePrincipal());
    FileSystemResource fs = new FileSystemResource(kerberosConfigProps.keytabLocation());
    ticketValidator.setKeyTabLocation(fs);
    ticketValidator.setDebug(true);
    return ticketValidator;
  }

  @Bean
  public KerberosLdapContextSource kerberosLdapContextSource() {
    KerberosLdapContextSource contextSource = new KerberosLdapContextSource(ldapConfigProps.ldapUrl());
    contextSource.setBase(ldapConfigProps.manager().ldapUserSearchBase());
    contextSource.setUserDn(ldapConfigProps.manager().ldapManagerDn());
    contextSource.setPassword(ldapConfigProps.manager().ldapManagerPassword());
    SunJaasKrb5LoginConfig loginConfig = new SunJaasKrb5LoginConfig();
    loginConfig.setKeyTabLocation(new FileSystemResource(kerberosConfigProps.keytabLocation()));
    loginConfig.setServicePrincipal(kerberosConfigProps.servicePrincipal());
    loginConfig.setDebug(true);
    loginConfig.setIsInitiator(true);
    contextSource.setLoginConfig(loginConfig);
    return contextSource;
  }

  @Bean
  public LdapUserDetailsService ldapUserDetailsService() {
    FilterBasedLdapUserSearch userSearch =
        new FilterBasedLdapUserSearch(ldapConfigProps.user().ldapUserSearchBase(),
            ldapConfigProps.user().ldapSearchFilter(), kerberosLdapContextSource());
    LdapUserDetailsService service = new LdapUserDetailsService(userSearch);
    service.setUserDetailsMapper(new LdapUserDetailsMapper());
    return service;
  }

}
