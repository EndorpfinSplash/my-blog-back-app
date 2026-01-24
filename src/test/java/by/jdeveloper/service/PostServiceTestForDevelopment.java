package by.jdeveloper.service;

import by.jdeveloper.configuration.TestDataSourceInitializer;
import by.jdeveloper.dto.PostsResponse;
import by.jdeveloper.mapper.PostMapperImpl;
import by.jdeveloper.repository.JdbcNativePostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@ExtendWith(SpringExtension.class) // Подключаем Spring TestContext
@SpringJUnitConfig(classes = {
        TestConfig.class,
        PostService.class,
        JdbcNativePostRepository.class,
        PostMapperImpl.class
},
        initializers = TestDataSourceInitializer.class)
@PropertySource("classpath:application.properties") // Для считывания application.properties
class PostServiceTestForDevelopment {

    @Autowired
    private PostService postService;

    @Test
    void findPosts() {
        PostsResponse posts = postService.findPosts("т", 1, 5);
        System.out.println(posts);
        Assertions.assertNotNull(posts);
    }
}