package by.jdeveloper.repository;

import by.jdeveloper.configuration.DataSourceConfiguration;
import by.jdeveloper.dao.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {
        DataSourceConfiguration.class,
        JdbcNativePostRepository.class}
)
@TestPropertySource(locations = "classpath:test-application.properties")
class JdbcNativePostRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM post");
    }

    @Test
    void findAllByTitleContains() {
    }

    @Test
    void findAllByTagContains() {
    }

    @Test
    void save() {
    }

    @Test
    void testSave() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAllCommentsByPostId() {
    }

    @Test
    void likesIncrease() {
    }

    @Test
    void findCommentsByPostIdAndCommentId() {
    }

    @Test
    void deleteByPostIdAndCommentId() {
    }

    @Test
    void saveFile() {
    }

    @Test
    void updateFileByPostId() {
    }

    @Test
    void getFileByPostId() {
    }

    @Test
    void countFilesByPostId() {
    }
}