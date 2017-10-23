package cl.intelliware.smartlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.python.util.PythonInterpreter;
import org.python.core.*;

@SpringBootApplication
public class SmartlabApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SmartlabApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartlabApplication.class, args);


		PythonInterpreter P = Python.getInstance();

		System.out.println("hola mundo");

		try {
			P.exec(
					"def algo():\n\tprint \"hago algo\"\n\treturn 2+2\n\nresultado = algo()\nprint resultado"
			);
		}
		catch (Exception e){
			System.out.println(e);
		}

		P.cleanup();
		P.exec("print \"chao mundo\"");

		System.out.println("lol");


	}
}
