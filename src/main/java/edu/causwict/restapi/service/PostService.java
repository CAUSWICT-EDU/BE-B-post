package edu.causwict.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.entity.User;
import edu.causwict.restapi.repository.PostRepository;
import edu.causwict.restapi.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public PostService(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public Post create(String title, String content, Long userId) {
		validateTitle(title);

		if (postRepository.existsByTitle(title)) {
			throw new IllegalArgumentException("중복된 제목의 게시글이 이미 존재합니다.");
		}

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

		Post post = new Post(title, content, user);
		return postRepository.save(post);
	}

	@Transactional
	public Post update(Long id, String title, String content) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

		validateTitle(title);

		if (postRepository.existsByTitleAndPostIdNot(title, id)) {
			throw new IllegalArgumentException("중복된 제목의 게시글이 이미 존재합니다.");
		}

		post.setTitle(title);
		post.setContent(content);
		return postRepository.save(post);
	}

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public List<Post> searchByTitle(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return postRepository.findAll();
		}
		return postRepository.findByTitleContaining(keyword);
	}

	private void validateTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("제목은 비어있을 수 없습니다.");
		}
		if (title.length() > 30) {
			throw new IllegalArgumentException("제목은 30자를 넘을 수 없습니다.");
		}
	}
}
