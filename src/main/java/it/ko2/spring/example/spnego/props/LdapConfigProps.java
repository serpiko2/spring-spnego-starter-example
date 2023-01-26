package it.ko2.spring.example.spnego.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.security.ldap")
public record LdapConfigProps(String ldapUrl, Manager manager, User user) {

  public record Manager(String ldapUserSearchBase,
                        String ldapManagerDn,
                        String ldapManagerPassword) {

  }

  public record User(String ldapUserSearchBase, String ldapSearchFilter) {
  }

}
