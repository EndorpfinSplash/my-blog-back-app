package service;

import dao.PostRepository;
import dto.NewPostDto;
import dto.PostDto;
import dto.PostsResponse;
import lombok.AllArgsConstructor;
import mapper.PostMapper;
import model.Comment;
import model.Post;
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

    public void update(Long id, Post post) {
        postRepository.update(id, post);
    }

    public PostsResponse findPosts(String search,
                                   int pageNumber,
                                   int pageSize) {
        List<PostDto> posts = postRepository.findAllByTitleContains(search);
        int lastPage = (int) Math.ceil((double) posts.size() / pageSize);
        return PostsResponse.builder()
                .posts(posts)
                .hasNext(pageNumber<lastPage)
                .hasPrev(pageNumber>1)
                .lastPage(lastPage)
                .build();
    }

    public Long incrementLike(Long id) {
        Post post = postRepository.findById(id);
        long likesCountIncreased = post.getLikesCount() + 1L;
        post.setLikesCount(likesCountIncreased);
        postRepository.save(post);
        return likesCountIncreased;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return postRepository.findAllCommentsByPostId(postId);
    }
}
