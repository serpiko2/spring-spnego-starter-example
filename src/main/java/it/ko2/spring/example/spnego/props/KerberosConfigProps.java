package it.ko2.spring.example.spnego.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.security.kerberos")
public record KerberosConfigProps(String servicePrincipal, String keytabLocation) {
}
