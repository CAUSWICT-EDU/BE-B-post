package edu.causwict.restapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import edu.causwict.restapi.entity.Post;

@Repository
public class InMemoryPostRepository {

	private final Map<Long, Post> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(0);

	public Post save(Post post) {
		if (post.getId() == null) {
			post.setId(sequence.incrementAndGet());
		}
		store.put(post.getId(), post);
		return post;
	}

	public List<Post> findAll() {
		return new ArrayList<>(store.values());
	}

	// Find By exact Title - 정확한 제목을 통해서 게시글 찾기
	public Post findByTitle(String title) {
		return store.values().stream()
				.filter(post -> post.getTitle().equals(title))
				.findFirst().orElse(null);
	}

	// Find by title keyword - 제목에 포함된 단어(키워드)를 통해서 게시글 찾기
	public List<Post> findByTitleKeyword(String keyword) {
		return store.values().stream()
				.filter(post -> post.getTitle().contains(keyword))
				.toList();
	}

	// Find By Id - id 통해서 게시글 찾기
	public Post findById(Long id) { return store.get(id); }
}
