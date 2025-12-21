package controller;

import dto.NewPostDto;
import dto.PostDto;
import dto.PostsResponse;
import lombok.AllArgsConstructor;
import model.Post;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.PostService;

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
    public void delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody Post post) {
        service.update(id, post);
    }

}
