package by.jdeveloper.repository;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.dto.NewCommentDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InnerPostRepository implements PostRepository {
    private final Map<Long, Post> postStorage = new HashMap<>();

    @Override
    public Collection<PostDto> findAllByTitleContains(String search) {
        return List.of();
    }

    @Override
    public List<PostDto> getAll() {
        return List.of();
    }

    @Override
    public Collection<PostDto> findAllByTagContains(String tag) {
        return List.of();
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post update(Long id, Post post) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Long likesIncrease(Long postId) {
        return 0L;
    }

    @Override
    public List<Comment> findAllCommentsByPostId(Long postId) {
        return List.of();
    }

    @Override
    public Comment save(Long postId, NewCommentDto newCommentDto) {
        return null;
    }

    @Override
    public Comment findCommentByPostIdAndCommentId(Long postId, Long commentId) {
        return null;
    }

    @Override
    public void deleteByPostIdAndCommentId(Long postId, Long commentId) {

    }

    @Override
    public void saveFile(Long postId, String name, byte[] data) {

    }

    @Override
    public boolean updateFileByPostId(Long postId, String fileName, byte[] data) {
        return false;
    }

    @Override
    public byte[] getFileByPostId(Long postId) {
        return new byte[0];
    }

    @Override
    public Long countFilesByPostId(Long postId) {
        return 0L;
    }
}