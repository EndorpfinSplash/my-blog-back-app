package by.jdeveloper.repository;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.dto.NewCommentDto;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InnerPostRepository implements PostRepository {
    private final Map<Long, Post> postStorage = new HashMap<>();
    private final Map<Long, Map<Long, Comment>> commentStorage = new HashMap<>();
    private final Map<Long, Image> imageStorage = new HashMap<>();

    @Override
    public Collection<Post> findAllByTitleContains(String search) {
        return postStorage.values().stream()
                .filter(post -> post.getTitle().contains(search))
                .toList();
    }

    @Override
    public List<Post> getAll() {
        return postStorage.values().stream().toList();
    }

    @Override
    public Collection<Post> findAllByTagContains(String tag) {
        return postStorage.values().stream()
                .filter(post -> post.getTags().contains(tag))
                .toList();
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