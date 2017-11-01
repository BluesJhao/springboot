package com.example.springboot;

import com.example.springboot.utils.SpringContextHolder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;

/**
 * befor 1.2.0
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.example.springboot.web"})
public class SpringbootApplication implements ApplicationRunner{

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(SpringbootApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);
		ApplicationContext app = SpringApplication.run(SpringbootApplication.class, args);
		SpringContextHolder.setApplicationContext(app);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		//TODO: Do something before SpringApplication.run(…​) completes...
	}
}
