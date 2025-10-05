package edu.causwict.restapi.service;

import edu.causwict.restapi.repository.enums.SearchMode;
import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

import java.util.List;

@Service
public class PostService {

	private final InMemoryPostRepository postRepository;

	public PostService(InMemoryPostRepository postRepository) { // 생성자 주입
		this.postRepository = postRepository;
	}

	public Post create(String title, String content) {
		Post post = new Post(null, title, content);
		return postRepository.save(post);
	}

	public Post edit(Long id, String title, String content) {
        return postRepository.edit(id, title, content);
	}

	public Post get(Long id) {
		return postRepository.get(id);
	}

	public List<Post> list() {
		return postRepository.findAll();
	}

	public List<Post> search(String keyword, SearchMode searchMode) {
		return postRepository.search(keyword, searchMode);
	}

}