package by.jdeveloper.service;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.mapper.PostMapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("unit-test")
@ComponentScan("by.jdeveloper.service")
public class UnitTestConfig {

    @Bean
    @Primary
    public PostRepository mockOrderRepository() {
        return Mockito.mock(PostRepository.class);
    }

    @Bean
    @Primary
    public PostMapper mockPostMapper() {
        return Mockito.mock(PostMapper.class);
    }

}
