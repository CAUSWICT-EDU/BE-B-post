package edu.causwict.restapi.web.controller;


import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

        // title validation rule
        if (title == null || title.trim().isEmpty()) return null;
        if (title.length() > 30) return null;

        //Not allow create post with duplicated title.
        List<Post> Posts = postService.findAll();
        for (Post post : Posts) {
            if (post.getTitle().equals(title)) return null;
        }


		return postService.create(title, content);
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

    //getPosts(GET /api/posts/search/{keyword})
    @GetMapping("/search/{keyword}")
    public List<Post> searchPostsByKeyword(@PathVariable String keyword) {
        return postService.searchPostsByKeyword(keyword);

    }
}
