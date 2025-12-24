package dao;

import dto.PostDto;
import model.Comment;
import model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    List<PostDto> findAllByTitleContains(String search);
    Post save(Post post);
    void deleteById(Long id);

    Post update(Long id, Post post);

    Post findById(Long id);

    List<Comment> findAllCommentsByPostId(Long postId);
}