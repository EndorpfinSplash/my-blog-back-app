package by.jdeveloper.service;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.mapper.PostMapper;
import by.jdeveloper.mapper.PostMapperImpl;
import by.jdeveloper.repository.InnerPostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("integration-tests")
@ComponentScan("by.jdeveloper.service")
public class InnerIntegrationTestConfig {

    @Bean
    public PostRepository orderRepository() {
        return new InnerPostRepository();
    }

    @Bean
    public PostMapper postMapper() {
        return new PostMapperImpl();
    }

}
