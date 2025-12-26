package by.jdeveloper.controller;

import by.jdeveloper.dto.NewPostDto;
import by.jdeveloper.dto.PostDto;
import by.jdeveloper.dto.PostsResponse;
import lombok.AllArgsConstructor;
import by.jdeveloper.model.Comment;
import by.jdeveloper.model.Post;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import by.jdeveloper.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public PostsResponse getPosts(@RequestParam String search,
                                  @RequestParam int pageNumber,
                                  @RequestParam int pageSize) {
        return service.findPosts(search, pageNumber, pageSize);
    }

    @PostMapping
    public PostDto save(@RequestBody NewPostDto newPostDto) {
        return  service.save(newPostDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Post post) {
        service.update(id, post);
    }

    @PostMapping("/{id}/likes")
    public Long incrementLike(@PathVariable Long id) {
        return  service.incrementLike(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable("id") Long postId) {
        return  service.getCommentsByPostId(postId);
    }

}
