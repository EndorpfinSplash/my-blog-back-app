package dao;

import dto.PostDto;
import model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    List<PostDto> findAllByTitleContains(String search);
    void save(Post post);
    void deleteById(Long id);

    void update(Long id, Post post);
}