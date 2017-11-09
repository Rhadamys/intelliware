package cl.intelliware.smartlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.python.util.PythonInterpreter;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
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
