package edu.causwict.restapi.controller;

import java.util.List;
import java.util.Map;

import edu.causwict.restapi.repository.enums.SearchMode;
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
	@PostMapping("create")
	public Post create(@RequestBody Map<String, Object> param) {
		String title = (String) param.get("title");
		String content = (String) param.get("content");

        return postService.create(title, content);
	}

	// Edit
	@PostMapping("edit")
	public Post edit(@RequestBody Map<String, Object> param) {
		Long id = ((Integer) param.get("id")).longValue();
		String title = (String) param.get("title");
		String content = (String) param.get("content");
		return postService.edit(id, title, content);
	}

	// List
	@GetMapping("list")
	public List<Post> list() {
		return postService.list();
	}

	// Search
	@PostMapping("search")
	public List<Post> search(@RequestBody Map<String, Object> param) {
		String keyword = (String) param.get("keyword");
		Integer mode = (Integer) param.get("searchMode");
		if(mode < 0 || mode >= SearchMode.values().length) return null;

		SearchMode searchMode = SearchMode.values()[mode];
		return postService.search(keyword, searchMode);
	}
}
