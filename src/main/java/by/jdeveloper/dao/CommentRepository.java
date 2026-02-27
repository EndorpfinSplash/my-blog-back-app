package by.jdeveloper.dao;

import by.jdeveloper.dto.NewCommentDto;
import by.jdeveloper.model.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Long postId, NewCommentDto newCommentDto);

    Comment updateComment(Long commentId, Comment comment);

    List<Comment> findAllCommentsByPostId(Long postId);

    Comment findCommentByPostIdAndCommentId(Long postId, Long commentId);

}