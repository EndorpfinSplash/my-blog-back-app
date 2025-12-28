package by.jdeveloper.repository;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.dto.NewCommentDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.util.Collections;
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
                        rs.getLong("id"),
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
                    "INSERT INTO post(title, text, tags, likes_count) VALUES (?, ?, ?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getText());
            ps.setLong(4, post.getLikesCount());

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
    public Comment save(Long postId, NewCommentDto newCommentDto) {
        Comment comment = new Comment();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO comment(text, post_id) VALUES (?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, newCommentDto.getText());
            ps.setLong(2,postId);

            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            comment.setId(key.longValue());
            comment.setText(newCommentDto.getText());
            comment.setPostId(postId);
        }
        return comment;
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
    public Post findById(Long postId) {
        String sql = """
                select p.id, p.title, p.text, p.likes_count, p.tags,
                       count(c.id) as comment_count
                from post p
                left join comment c on p.id = c.post_id
                where p.id = ?
                group by p.id, p.title, p.text, p.likes_count, p.tags
                """;
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        List.of((String[]) rs.getArray("tags").getArray()),
                        rs.getLong("comment_count"),
                        rs.getLong("likes_count")
                ),
                postId
        );
    }

    @Override
    public List<Comment> findAllCommentsByPostId(Long postId) {
        String sql = """
                select c.id, c.text, c.post_id
                from comment c
                where c.post_id = ?
                """;
        List<Comment> commentList = jdbcTemplate.query(
                sql,
                new Object[]{postId},
                (rs, rowNum) -> new Comment(
                        rs.getLong("id"),
                        rs.getString("text"),
                        rs.getLong("post_id")
                ));
        return commentList == null ? Collections.emptyList() : commentList;
    }

    @Override
    public Long likesIncrease(Long postId, Long likesCountIncreased) {
        jdbcTemplate.update("""
                        update post set likes_count = ?
                        where id = ?
                        """,
                likesCountIncreased,
                postId);
        return likesCountIncreased;
    }

    @Override
    public Comment findCommentsByPostIdAndCommentId(Long postId, Long commentId) {
        String sql = """
                select c.id, c.text, c.post_id
                from comment c
                where c.post_id = ? and c.id = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new Comment(
                        rs.getLong("id"),
                        rs.getString("text"),
                        rs.getLong("post_id")
                ),
                postId,
                commentId
        );
    }

}