package com.looseboxes.demo.webapp;

import com.looseboxes.ratelimiter.spring.util.RateLimitProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackageClasses = {
        com.looseboxes.demo.webapp.DemoWebappApplication.class,
        com.looseboxes.ratelimiter.spring.web.RateLimiterConfiguration.class
})
@EnableConfigurationProperties({ RateLimitProperties.class })
public class DemoWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebappApplication.class, args);
    }

    @Bean
    public CommandLineRunnerToAddBooks commandLineRunnerToAddBooks(BookRepository bookRepository) {
        return new CommandLineRunnerToAddBooks(bookRepository);
    }
}
