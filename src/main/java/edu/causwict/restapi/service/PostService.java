package edu.causwict.restapi.service;

import edu.causwict.restapi.dto.PostResponse;
import edu.causwict.restapi.entity.Board;
import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.entity.User;
import edu.causwict.restapi.repository.BoardRepository;
import edu.causwict.restapi.repository.PostRepository;
import edu.causwict.restapi.repository.UserRepository;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;


	@Transactional // 쓰기 작업이므로 readOnly = false
	public PostResponse create(Long userId, Long boardId, String title, String content) {
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
		if (postRepository.existsByTitle(title)) {
			throw new IllegalArgumentException("Same title post already exists");
		}

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));

		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new IllegalArgumentException("Board not found"));

		Post post = Post.builder()
				.user(user)
				.board(board)
				.title(title)
				.content(content)
				.build();

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
	@Transactional
	public PostResponse update(long id, String title, String content) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Post does not exist"));
		post.update(title, content);
		return PostResponse.from(post);
	}

	// 제목 키워드 통해서 게시글 찾기
	public List<PostResponse> searchByTitleKeyword(String keyword) {
		return postRepository.findByTitleContaining(keyword).stream()
				.map(PostResponse::from)
				.collect(Collectors.toList());
	}

}