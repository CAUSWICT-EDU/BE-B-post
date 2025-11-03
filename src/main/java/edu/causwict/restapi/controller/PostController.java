package edu.causwict.restapi.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
		try {
			Post created = postService.create(title, content);

			return created;
		} catch (IllegalArgumentException e) {
			throw e; // 임시적으로 이렇게 처리, 나중에 에러 핸들러 같은거 만들어서 수정할 수도
		}
	}

	// 게시글 수정 api
	@PatchMapping
	public Post update(@RequestBody Map<String, Object> param) {
		String title = (String) param.get("title");
		String content = (String) param.get("content");
		try {
			Post updated = postService.update(title, content);
			return updated;
		} catch (NoSuchElementException e) {
			throw e; // 임시적으로 이렇게 처리, 나중에 에러 핸들러 같은거 만들어서 수정할 수도
		}
	}

	// 게시글 리스트 api
	@GetMapping
	public List<Post> get(@RequestParam Map<String, Object> param) {
		if (param.isEmpty()) return postService.getAll(); // 파라미터를 입력하지 않은 경우
		else return postService.getPost((String) param.get("title"));
	}
}
