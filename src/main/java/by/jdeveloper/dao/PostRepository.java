package by.jdeveloper.dao;

import by.jdeveloper.dto.PostDto;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    List<PostDto> findAllByTitleContains(String search);
    Post save(Post post);
    void deleteById(Long id);

    Post update(Long id, Post post);

    Post findById(Long id);

    List<Comment> findAllCommentsByPostId(Long postId);

    Long likesIncrease(Long postId, Long likesCountIncreased);
}