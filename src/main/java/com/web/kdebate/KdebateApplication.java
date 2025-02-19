package com.web.kdebate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.web"})
@MapperScan("com.web.kdebate.**.mapper")
public class KdebateApplication extends SpringBootServletInitializer{
	private static Class<KdebateApplication> applicationClass = KdebateApplication.class;
	public static void main(String[] args) {
		SpringApplication.run(KdebateApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
