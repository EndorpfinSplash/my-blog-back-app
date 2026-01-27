package by.jdeveloper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"by.jdeveloper"})
@PropertySource("classpath:application.properties") // Для считывания application.properties
public class WebConfiguration {}