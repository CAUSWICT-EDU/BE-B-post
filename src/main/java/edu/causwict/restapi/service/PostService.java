package edu.causwict.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

import java.util.Optional;
import java.util.List;

import java.util.List;

@Service
public class PostService {

	private final InMemoryPostRepository postRepository;

	public PostService(InMemoryPostRepository postRepository) { // 생성자 주입
		this.postRepository = postRepository;
	}

    // 제목 유효성 검사
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        // 제목 길이에 대한 조건 : 30자 이하
        if (title.length() > 30) {
            throw new IllegalArgumentException("Title cannot exceed 30 characters.");
        }
    }
    // 제목 중복 검사
    private void validateDuplicateTitle(String title) {
        postRepository.findByTitle(title).ifPresent(p -> {
            throw new IllegalArgumentException("A post with this title already exists.");
        });
    }

    // 제목의 키워드로 게시글을 검색
    public List<Post> searchByTitle(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

	public Post create(String title, String content) {
        validateTitle(title);
        validateDuplicateTitle(title);
		Post post = new Post(null, title, content);
		return postRepository.save(post);
	}

    // 게시글 수정 로직 추가
    public Optional<Post> update(Long id, String title, String content) {
        // 1. 저장소에서 id에 해당하는 게시글 찾기
        Optional<Post> optionalPost = postRepository.findById(id);

        // 2. 게시글이 존재하면, 제목과 내용을 업데이트
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(title);
            post.setContent(content);
            // 3. 업데이트된 게시글을 저장
            postRepository.update(id, post);
            return Optional.of(post);
        } else {
            return Optional.empty(); // 게시글이 없으면 비어있는 optional 반환
        }
    }

    // 모든 게시글을 조회하는 로직 추가
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}