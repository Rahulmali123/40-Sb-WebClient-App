package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.client.BookClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

		BookClient bean = run.getBean(BookClient.class);

	//	bean.invokeSaveBook();
	//	bean.invokeGetBooksOld();
		bean.invokeGetBooksAsync();
	}

}
