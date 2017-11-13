package cl.intelliware.smartlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;


import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableOAuth2Sso
public class SmartlabApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SmartlabApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartlabApplication.class, args);
	}

	@PostConstruct
	void started() {
		// Changes the default time zone to Santiago de Chile
		TimeZone.setDefault(TimeZone.getTimeZone("America/Santiago"));
	}
}
