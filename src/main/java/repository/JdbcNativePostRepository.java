package repository;

import dao.PostRepository;
import dto.PostDto;
import model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {

        return jdbcTemplate.query(
                "select id, title, text, likes_count from post",
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getLong("likes_count")
                ));
    }

    @Override
    public List<PostDto> findAllByTitleContains(String search) {
        String sql = """
                select p.id, p.title, p.text, p.likes_count , 
                       count(c.id) as comment_count,
                     as tags
                from post p
                left join comment c on p."id" = c."post_id"
                left join tag t on t.post_id = p.id
                where title ilike ?
                group by p.id, p.title, p.text, p.likes_count
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
    public void save(Post post) {
        jdbcTemplate.update("insert into post(title, text) values(?, ?)",
                post.getTitle(), post.getText());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from post where id = ?", id);
    }

    @Override
    public void update(Long id, Post post) {
        jdbcTemplate.update("update post set title = ?, text = ? where id = ?",
                post.getTitle(), post.getText());
    }

}