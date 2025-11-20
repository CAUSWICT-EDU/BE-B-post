package edu.causwict.restapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> create(@RequestBody Map<String, Object> param) {
		try {
			String title = (String) param.get("title");
			String content = (String) param.get("content");
			Long userId = param.get("userId") != null ?
				((Number) param.get("userId")).longValue() : null;

			if (userId == null) {
				return ResponseEntity.badRequest().body(Map.of("error", "userId는 필수입니다."));
			}

			Post created = postService.create(title, content, userId);
			return ResponseEntity.status(HttpStatus.CREATED).body(created);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}

	// Update
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> param) {
		try {
			String title = (String) param.get("title");
			String content = (String) param.get("content");
			Post updated = postService.update(id, title, content);
			return ResponseEntity.ok(updated);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}

	// List
	@GetMapping
	public ResponseEntity<List<Post>> list() {
		List<Post> posts = postService.findAll();
		return ResponseEntity.ok(posts);
	}

	// Search
	@GetMapping("/search")
	public ResponseEntity<List<Post>> search(@RequestParam(required = false) String keyword) {
		List<Post> posts = postService.searchByTitle(keyword);
		return ResponseEntity.ok(posts);
	}
}
