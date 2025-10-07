package edu.causwict.restapi.service;

import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

	private final InMemoryPostRepository postRepository;

	public PostService(InMemoryPostRepository postRepository) { // 생성자 주입
		this.postRepository = postRepository;
	}

	public Post create(String title, String content) {
		validateTitle(title);

		Post post = new Post(null, title, content);
		return postRepository.save(post);
	}

    public Post update(String title, String content) {
		Optional<Post> res = postRepository.findByTitle(title);
		validateTitle(title);
		if (res.isEmpty()) throw new NoSuchElementException("No data found with title: " + title);

		Post post = res.get();
		post.setTitle(title);
		post.setContent(content);
		return post;
    }

	public List<Post> getAll() {
		return postRepository.findAll();
	}

	public List<Post> getPost(String title) {
		Optional<Post> res = postRepository.findByTitle(title);
		if (res.isEmpty()) throw new NoSuchElementException("No data found with title: " + title);
		return List.of(res.get());
	}

	private void validateTitle(String title) {
		if (title == null || title.isEmpty()) throw new IllegalArgumentException("title is null or empty");
		if (postRepository.findByTitle(title).isPresent()) throw new IllegalArgumentException("title already exists");
		if (title.length() > 30) throw new IllegalArgumentException("title exceeds 30");
	}
}