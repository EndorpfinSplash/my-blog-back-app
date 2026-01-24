package by.jdeveloper.service;

import by.jdeveloper.dao.PostRepository;
import by.jdeveloper.dto.NewCommentDto;
import by.jdeveloper.dto.NewPostDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.dto.PostUpdateDto;
import by.jdeveloper.mapper.PostMapper;
import by.jdeveloper.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(TestConfig.class)
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostMapper postMapper;

    @Autowired
    PostService postService;

    @BeforeEach
    void resetMocks() {
        reset(postRepository);
    }

    @Test
    void save() {
        NewPostDto newPostDto = NewPostDto.builder()
                .title("new title")
                .text("new text")
                .tags(List.of("new_tag"))
                .build();

        Post postForSave = new Post();
        when(postMapper.toEntity(newPostDto)).thenReturn(postForSave);
        when(postRepository.save(any(Post.class))).thenReturn(postForSave);

        postService.save(newPostDto);
        verify(postMapper, times(1)).toEntity(any(NewPostDto.class));
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void SaveComment() {
        NewCommentDto newCommentDto = new NewCommentDto();
        postService.saveComment(1L, newCommentDto);
        verify(postRepository, times(1)).save(1L, newCommentDto);
    }

    @Test
    void deleteById() {
        postService.deleteById(1L);
        verify(postRepository, times(1)).deleteById(1L);
    }

    @Test
    void update_post_when_not_found()  {
        when(postRepository.findById(999L)).thenReturn(Optional.empty());
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> postService.update(999L, new PostUpdateDto())
        );
        assertEquals("Post with id=999 not found", illegalArgumentException.getMessage(), "");
    }

    @Test
    void update_post()  {
        Post post = new Post();
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.update(anyLong(), any(Post.class))).thenReturn(post);
        when(postMapper.toDto(post)).thenReturn(new PostDto());

         postService.update(1L, new PostUpdateDto());

        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).update(anyLong(), any(Post.class));
        verify(postMapper, times(1)).toDto(any(Post.class));
    }

    @Test
    void findPosts() {
    }

    @Test
    void incrementLike() {
    }

    @Test
    void findById() {
    }

    @Test
    void getCommentsByPostId() {
    }

    @Test
    void getCommentsByPostIdAndCommentId() {
    }

    @Test
    void deleteByPostIdAndCommentId() {
    }
}