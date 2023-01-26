package it.ko2.spring.example.spnego;

import it.ko2.spring.example.spnego.props.KerberosConfigProps;
import it.ko2.spring.example.spnego.props.LdapConfigProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({KerberosConfigProps.class, LdapConfigProps.class})
public class SpnegoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpnegoApplication.class, args);
	}

}
