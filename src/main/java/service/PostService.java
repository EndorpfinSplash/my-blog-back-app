package service;

import dao.PostRepository;
import dto.PostDto;
import dto.PostsResponse;
import lombok.AllArgsConstructor;
import model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public void update(Long id, Post post) {
        postRepository.update(id, post);
    }

    public PostsResponse findPosts(String search,
                                   int pageNumber,
                                   int pageSize) {
        List<PostDto> posts = postRepository.findAllByTitleContains(search);

        return PostsResponse.builder()
                .posts(posts)
                .build();
    }
}
