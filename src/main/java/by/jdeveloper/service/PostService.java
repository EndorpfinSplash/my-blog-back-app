package by.jdeveloper.service;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.dto.NewPostDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.dto.PostUpdateDto;
import by.jdeveloper.dto.PostsResponse;
import by.jdeveloper.mapper.PostMapper;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public PostDto save(NewPostDto newPostDto) {
        Post post = postMapper.toEntity(newPostDto);
        return postMapper.toDto(postRepository.save(post));
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public PostDto update(Long id, PostUpdateDto postUpdated) {
        Post post = postRepository.findById(id);
        post.setTitle(postUpdated.getTitle());
        post.setText(postUpdated.getText());
        post.setTags(postUpdated.getTags());
        return postMapper.toDto(postRepository.update(id, post));
    }

    public PostsResponse findPosts(String search,
                                   int pageNumber,
                                   int pageSize) {
        List<PostDto> posts = postRepository.findAllByTitleContains(search);
        int lastPage = (int) Math.ceil((double) posts.size() / pageSize);
        return PostsResponse.builder()
                .posts(posts)
                .hasNext(pageNumber < lastPage)
                .hasPrev(pageNumber > 1)
                .lastPage(lastPage)
                .build();
    }

    public Long incrementLike(Long postId) {
        Post post = postRepository.findById(postId);
        Long likesCountIncreased = post.getLikesCount() + 1;
        return postRepository.likesIncrease(postId, likesCountIncreased);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return postRepository.findAllCommentsByPostId(postId);
    }

    public PostDto findById(Long id) {
        return postMapper.toDto(postRepository.findById(id));
    }
}
