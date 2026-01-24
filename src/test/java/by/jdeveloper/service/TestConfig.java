package by.jdeveloper.service;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.mapper.PostMapper;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("by.jdeveloper.service")
public class TestConfig {

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
