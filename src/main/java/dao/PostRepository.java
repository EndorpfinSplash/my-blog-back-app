package dao;

import model.Post;

import java.util.List;

public interface PostRepository {
    List<Post> findAll();
    void save(Post post);
    void deleteById(Long id);

    void update(Long id, Post post);
}