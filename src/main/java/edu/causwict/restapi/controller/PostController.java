package edu.causwict.restapi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import edu.causwict.restapi.repository.enums.SearchMode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.causwict.restapi.domain.Post;
import edu.causwict.restapi.service.PostService;
import org.springframework.web.multipart.MultipartFile;

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
	@PatchMapping("edit")
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
	@GetMapping("search")
	public List<Post> search(@RequestParam String keyword, @RequestParam SearchMode mode) {
		return postService.search(keyword, mode);
	}

	// Upload
	@PostMapping("upload")
	public Post upload(@RequestParam("file") MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		String s = new String(inputStream.readAllBytes());
		if(s.isEmpty()) return null;

		String[] bodies = s.split("\n");
		if(bodies.length == 1) return null;

		String title = bodies[0];
		String content = s.substring(title.length() + 1);

		return postService.create(title, content);
	}

	// Download
	@GetMapping("download")
	public ResponseEntity<byte[]> download(@RequestParam Long id) {
		Post post = postService.get(id);

        String s = post.getTitle() + "\n" + post.getContent();
		return ResponseEntity.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body(s.getBytes());
	}
}
