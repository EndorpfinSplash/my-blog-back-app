package repository;

import dao.PostRepository;
import dto.PostDto;
import lombok.AllArgsConstructor;
import model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> findAll() {

        String sql = """
                select p.id, p.title, p.text, p.likes_count, p.tags,
                       count(c.id) as comment_count
                from post p
                left join comment c on p.id = c.post_id
                group by p.id, p.title, p.text, p.likes_count, p.tags
                """;
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        (List<String>) rs.getArray("tags"),
                        rs.getLong("likes_count"),
                        rs.getLong("comment_count")
                ));
    }

    @Override
    public List<PostDto> findAllByTitleContains(String search) {
        String sql = """
                select p.id, p.title, p.text, p.likes_count, p.tags,
                       count(c.id) as comment_count
                from post p
                left join comment c on p.id = c.post_id
                where title ilike ?
                group by p.id, p.title, p.text, p.likes_count, p.tags
                """;
        return jdbcTemplate.query(
                sql,
                new Object[]{"%" + search + "%"},
                (rs, rowNum) -> new PostDto(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        (List<String>) rs.getArray("tags"),
                        rs.getLong("comment_count"),
                        rs.getLong("likes_count")
                ));
    }

    @Override
    public Post save(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO post(title, text) VALUES (?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getText());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            post.setId(key.longValue());
        }
        return post;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from post where id = ?", id);
    }

    @Override
    public Post update(Long id, Post post) {
        jdbcTemplate.update("update post set title = ?, text = ? where id = ?",
                post.getTitle(),
                post.getText(),
                id
        );
        return post;
    }

}