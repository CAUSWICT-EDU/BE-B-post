package edu.causwict.restapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
		Post created = postService.create(title, content);

		return created;
	}

    // Update
    @PutMapping("/{id}") // PUT 메서드로 /api/posts/{id} 요청을 처리
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Map<String, Object> param) {
        String title = (String) param.get("title");
        String content = (String) param.get("content");
        Optional<Post> updated = postService.update(id, title, content);

        // updated 객체가 존재하면(게시글 수정 성공) 수정된 Post와 200 OK
        // 그렇지 않으면 404 반환
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Read (List & Search)
    @GetMapping
    public List<Post> list(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            // 키워드가 있으면 검색
            return postService.searchByTitle(keyword);
        }
        // 키워드가 없으면(null이거나 비어있으면) 전체 목록 반환
        return postService.findAll();
    }

    // 이 컨트롤러 내에서 IllegalArgumentException이 발생하면 이 메서드가 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
