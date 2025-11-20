package edu.causwict.restapi.controller;

import java.util.List;
import java.util.Map;

import edu.causwict.restapi.dto.PostCreateRequest;
import edu.causwict.restapi.dto.PostResponse;
import edu.causwict.restapi.dto.PostUpdateRequrest;
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
	public PostResponse create(@RequestBody PostCreateRequest request) {
		return postService.create(request.title(), request.content());
	}

	// List - 게시글 list api
	@GetMapping
	public List<PostResponse> findAll() {
		return postService.findAll();
	}

	// Update - 게시글 수정
	@PutMapping("/{postId}")
	public PostResponse update(@PathVariable Long postId, @RequestBody PostUpdateRequrest request) {
		return postService.update(postId, request.title(), request.content());
	}

	// Search by Title Keyword - 제목 키워드 통해서 게시글 찾기
	@GetMapping("/search")
	public List<PostResponse> searchbyTitleKeyword(@RequestParam String keyword) {
		return postService.searchByTitleKeyworkd(keyword);
	}

}
