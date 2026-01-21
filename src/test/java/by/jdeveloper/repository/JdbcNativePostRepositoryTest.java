package by.jdeveloper.repository;

import by.jdeveloper.configuration.DataSourceConfiguration;
import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
                    java.sql.Array tagsArray = ps.getConnection().createArrayOf("VARCHAR", new String[]{"simple_tag"});
                    ps.setArray(3, tagsArray);
                    ps.setInt(4, 0);
                }
        );

        jdbcTemplate.update("INSERT INTO post(title, text, tags, likes_count) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, "TEST title 2");
                    ps.setString(2, "test second");
                    java.sql.Array tagsArray = ps.getConnection().createArrayOf("VARCHAR", new String[]{"test_second"});
                    ps.setArray(3, tagsArray);
                    ps.setInt(4, 0);
                }
        );

        jdbcTemplate.update("INSERT INTO post(title, text, tags, likes_count) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, "Title");
                    ps.setString(2, "simple text");
                    java.sql.Array tagsArray = ps.getConnection().createArrayOf("VARCHAR", new String[]{"simple_tag", "second_tag"});
                    ps.setArray(3, tagsArray);
                    ps.setInt(4, 0);
                }
        );

        jdbcTemplate.update("INSERT INTO comment(text, post_id) VALUES (?, ?)",
                ps -> {
                    ps.setString(1, "Comment text for post");
                    ps.setLong(2, 1L);
                }
        );

    }

    @Test
    void findAllByExistedTitle_shouldReturnPosts() {
        Collection<PostDto> posts = postRepository.findAllByTitleContains("test");

        assertEquals(2, posts.size());
        assertTrue(List.of(1L, 2L).containsAll(posts.stream().map(PostDto::getId).toList()));
    }

    @Test
    void findAllByNonExistedTitle_shouldReturnEmptyList() {
        Collection<PostDto> posts = postRepository.findAllByTitleContains("absent test");

        assertEquals(0, posts.size());
    }

    @Test
    void findAllByExistedTag_shouldReturnPosts() {
        Collection<PostDto> posts = postRepository.findAllByTagContains("simple_tag");

        assertEquals(2, posts.size());
        assertTrue(posts.stream().map(PostDto::getId).toList().containsAll(List.of(1L, 3L)));
    }

    @Test
    void findAllByNonExistedTag_shouldReturnEmptyList() {
        Collection<PostDto> posts = postRepository.findAllByTagContains("tag");

        assertEquals(0, posts.size());
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
        postRepository.deleteById(3L);

        List<PostDto> allPosts = postRepository.getAll();
        assertEquals(2, allPosts.size());
        assertTrue(allPosts.stream().noneMatch(u -> u.getId().equals(3L)));
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
        Post post = postRepository.findById(3L);

        assertEquals("Title", post.getTitle());
        assertEquals("simple text", post.getText());
        assertEquals(0, post.getLikesCount());
        assertTrue(post.getTags().containsAll(List.of("simple_tag", "second_tag")));
        assertEquals(2, post.getTags().size());
    }

    @Test
    void findAllCommentsByPostId() {
        List<Comment> allCommentsByPostId = postRepository.findAllCommentsByPostId(1L);
        assertEquals(1, allCommentsByPostId.size());
        assertEquals("Comment text for post", allCommentsByPostId.getFirst().getText());
    }

    @Test
    void likesIncrease() {
        Long likes = postRepository.likesIncrease(2L);

        assertEquals(1, likes);
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