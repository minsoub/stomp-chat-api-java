package kr.co.fns.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan("kr.co.fns.chat")
public class FantooChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantooChatApplication.class, args);
	}

}
