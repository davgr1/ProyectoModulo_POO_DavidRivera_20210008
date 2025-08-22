package ProyectoModulo_DavidRivera_20210008;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DavidRivera2021000Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(enty -> System.setProperty(enty.getKey(), enty.getValue())
		);
		SpringApplication.run(DavidRivera2021000Application.class, args);
	}

}
