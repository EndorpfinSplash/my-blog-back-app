package repository;

import dao.PostRepository;
import dto.PostDto;
import lombok.AllArgsConstructor;
import model.Comment;
import model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
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
                        List.of((String[]) rs.getArray("tags").getArray()),
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
                        List.of((String[]) rs.getArray("tags").getArray()),
                        rs.getLong("comment_count"),
                        rs.getLong("likes_count")
                ));
    }

    @Override
    public Post save(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO post(title, text, tags) VALUES (?, ?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getText());

            Array sqlArray = connection.createArrayOf("text",
                    post.getTags().toArray(new String[0])
            );
            ps.setArray(3, sqlArray);
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
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    """
                            update post
                            set title = ?, text = ? , tags= ?
                            where id = ?
                            """,
                    new String[]{"id"}
            );
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getText());

            Array sqlArray = connection.createArrayOf("text",
                    post.getTags().toArray(new String[0])
            );
            ps.setArray(3, sqlArray);
            ps.setLong(4, id);
            return ps;
        });
        return post;
    }

    @Override
    public Post findById(Long id) {
        String sql = """
                select p.id, p.title, p.text, p.likes_count, p.tags,
                       count(c.id) as comment_count
                from post p
                left join comment c on p.id = c.post_id
                where p.id = ?
                group by p.id, p.title, p.text, p.likes_count, p.tags
                """;
        Post post = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        List.of((String[]) rs.getArray("tags").getArray()),
                        rs.getLong("comment_count"),
                        rs.getLong("likes_count")
                ));
        return post;
    }

    @Override
    public List<Comment> findAllCommentsByPostId(Long postId) {
        String sql = """
                select c.id, c.text, c.post_id
                from comment c
                where c.post_id = ?
                """;
        return jdbcTemplate.query(
                sql,
                new Object[]{postId},
                (rs, rowNum) -> new Comment(
                        rs.getLong("id"),
                        rs.getString("text"),
                        rs.getLong("post_id")
                ));
    }

}