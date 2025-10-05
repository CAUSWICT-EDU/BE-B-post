package edu.causwict.restapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	// Create
	@PostMapping
	public Post create(@RequestBody Map<String, Object> param) {
		String title = (String) param.get("title");
		String content = (String) param.get("content");

        if(title == null || title.trim() == null) return null;

        if (title.length() >= 30) return null;

		Post created = postService.create(title, content);

		return created;
	}


    // Edit (PATCH /api/posts/{id})
    @PatchMapping("/{id}")
    public Post edit(@PathVariable Long id, @RequestBody Map<String, Object> param) {
        String title = (String) param.get("title");
        String content = (String) param.get("content");

        return postService.edit(id, title, content);
    }

    //getPosts(GET /api/posts)
    @GetMapping
    public List<Post> getPosts() {
        return postService.findAll();
    }
}
