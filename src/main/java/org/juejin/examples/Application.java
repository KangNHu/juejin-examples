package org.juejin.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
*   启动类
* @author : KangNing Hu
*/
@SpringBootApplication
@Import({ValidationAutoConfiguration.class})
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class , args);
	}
}
