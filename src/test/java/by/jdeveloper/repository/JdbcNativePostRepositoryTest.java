package by.jdeveloper.repository;

import by.jdeveloper.configuration.DataSourceConfiguration;
import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        jdbcTemplate.update("INSERT INTO post(title, text, tags, likes_count) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, "Test title");
                    ps.setString(2, "test text");
                    java.sql.Array tagsArray = ps.getConnection().createArrayOf("VARCHAR", new String[]{"test_tag"});
                    ps.setArray(3, tagsArray);
                    ps.setInt(4, 0);
                }
        );

        jdbcTemplate.update("INSERT INTO post(title, text, tags, likes_count) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, "Test title 2");
                    ps.setString(2, "test second");
                    java.sql.Array tagsArray = ps.getConnection().createArrayOf("VARCHAR", new String[]{"test_second"});
                    ps.setArray(3, tagsArray);
                    ps.setInt(4, 0);
                }
        );

        jdbcTemplate.update("INSERT INTO post(title, text, tags, likes_count) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, "Test three");
                    ps.setString(2, "test 2");
                    java.sql.Array tagsArray = ps.getConnection().createArrayOf("VARCHAR", new String[]{"three"});
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
    void save_shouldAddPostToDatabase() {
        Post post = Post.builder()
                .title("for save")
                .text("some text")
                .tags(List.of("saved_tag"))
                .build();
        postRepository.save(post);

        Post saved = postRepository.findById(4L);

        assertNotNull(saved);
        assertEquals(4L, saved.getId());
        assertEquals("for save", saved.getTitle());
        assertEquals("some text", saved.getText());
        assertEquals("saved_tag", saved.getTags().getFirst());
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