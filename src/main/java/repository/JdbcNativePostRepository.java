package repository;

import dao.PostRepository;
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
                "select id, title, text from post",
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("text")
                ));
    }

    @Override
    public List<Post> findAllByTitleContains(String search, int pageNumber, int pageSize) {
        return jdbcTemplate.query(
                "select id, title, text from post where title ilike ?",
                new Object[]{"%" + search + "%"},
                (rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("text")
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