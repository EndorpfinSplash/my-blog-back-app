package by.jdeveloper.dao;

import by.jdeveloper.dto.NewCommentDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();

    List<PostDto> findAllByTitleContains(String search);

    Post save(Post post);

    Comment save(Long postId, NewCommentDto newCommentDto);

    void deleteById(Long id);

    Post update(Long id, Post post);

    Post findById(Long id);

    List<Comment> findAllCommentsByPostId(Long postId);

    Long likesIncrease(Long postId);

    Comment findCommentsByPostIdAndCommentId(Long postId, Long commentId);

    void deleteByPostIdAndCommentId(Long postId, Long commentId);

    void saveByteArray(Long postId, String name, byte[] data);

    byte[] getFileByPostId(Long postId);
}