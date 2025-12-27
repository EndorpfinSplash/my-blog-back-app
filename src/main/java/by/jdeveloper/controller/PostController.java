package by.jdeveloper.controller;

import by.jdeveloper.dto.NewPostDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.dto.PostUpdateDto;
import by.jdeveloper.dto.PostsResponse;
import by.jdeveloper.model.Comment;
import by.jdeveloper.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public PostsResponse getPosts(@RequestParam("search") String search,
                                  @RequestParam("pageNumber") int pageNumber,
                                  @RequestParam("pageSize") int pageSize) {
        return service.findPosts(search, pageNumber, pageSize);
    }

    @PostMapping
    public PostDto save(@RequestBody NewPostDto newPostDto) {
        return service.save(newPostDto);
    }

    @GetMapping(value = "/{id}")
    public PostDto findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public PostDto update(@PathVariable(name = "id") Long id, @RequestBody PostUpdateDto postUpdated) {
        return service.update(id, postUpdated);
    }

    @PostMapping("/{id}/likes")
    public Long incrementLike(@PathVariable("id") Long id) {
        return service.incrementLike(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable("id") Long postId) {
        return service.getCommentsByPostId(postId);
    }

}
