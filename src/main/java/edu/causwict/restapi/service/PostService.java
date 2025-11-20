package edu.causwict.restapi.service;

import edu.causwict.restapi.dto.PostResponse;
import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

	private final InMemoryPostRepository postRepository;

	public PostService(InMemoryPostRepository postRepository) { // 생성자 주입
		this.postRepository = postRepository;
	}

	public PostResponse create(String title, String content) {

		// 유효성 검사
		// 1. 제목이 비어있는 경우, 게시글 작성 불가
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty");
		}

		// 2. 제목 30자 이상 불가
		if (title.length() > 30) {
			throw new IllegalArgumentException("Title cannot be longer than 30 characters");
		}

		// 3. 제목 중복 게시글 작성 불가
		if (postRepository.findByTitle(title) != null) {
			throw new IllegalArgumentException("Same title post already exists");
		}

		Post post = new Post(null, title, content);
		Post savedPost = postRepository.save(post);

		return PostResponse.from(savedPost);
	}

	// 모든 게시물 리스트
	public List<PostResponse> findAll() {
		List<Post> posts = postRepository.findAll();
		return posts.stream()
				.map(PostResponse::from)
				.collect(Collectors.toList());
	}

	// 게시물 수정하기
	public PostResponse update(long id, String title, String content) {
		Post post = postRepository.findById(id);
		if (post == null) {
			throw new IllegalArgumentException("Post does not exist");
		}
		post.setTitle(title);
		post.setContent(content);

		Post updatedPost = postRepository.save(post);

		return PostResponse.from(updatedPost);
	}

	// 제목 키워드 통해서 게시글 찾기
	public List<PostResponse> searchByTitleKeyworkd(String keyword) {
		List<Post> posts = postRepository.findByTitleKeyword(keyword);
		return posts.stream()
				.map(PostResponse::from)
				.collect(Collectors.toList());
	}

}