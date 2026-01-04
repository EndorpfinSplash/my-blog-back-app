package by.jdeveloper.dao;

import by.jdeveloper.dto.NewCommentDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;

import java.util.Collection;
import java.util.List;

public interface PostRepository {

    Collection<PostDto> findAllByTitleContains(String search);

    Collection<PostDto> findAllByTagContains(String tag);

    Post save(Post post);

    Post update(Long id, Post post);

    Post findById(Long id);

    void deleteById(Long id);

    Long likesIncrease(Long postId);

    List<Comment> findAllCommentsByPostId(Long postId);

    Comment save(Long postId, NewCommentDto newCommentDto);

    Comment findCommentsByPostIdAndCommentId(Long postId, Long commentId);

    void deleteByPostIdAndCommentId(Long postId, Long commentId);

    void saveFile(Long postId, String name, byte[] data);

    void updateFileByPostId(Long postId, String fileName, byte[] data);

    byte[] getFileByPostId(Long postId);

    Long countFilesByPostId(Long postId);
}