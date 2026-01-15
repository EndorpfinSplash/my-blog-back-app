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
      //  jdbcTemplate.execute("DELETE FROM post");

        jdbcTemplate.update("INSERT INTO post(title, text, tags, likes_count) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, "Test title");
                    ps.setString(2, "test text");
                    java.sql.Array tagsArray = ps.getConnection().createArrayOf("VARCHAR", new String[]{"test_tag"});
                    ps.setArray(3, tagsArray);
                    ps.setInt(4, 0);
                }
        );
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