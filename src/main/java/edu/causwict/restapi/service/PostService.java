package edu.causwict.restapi.service;

import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            if (title != null) {
                existingPost.setTitle(title);
            }
            if (content != null) {
                existingPost.setContent(content);
            }

            return postRepository.save(existingPost);
        }

        return null;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> allPosts = postRepository.findAll();
        List<Post> matchingPosts = new ArrayList<>();


        String lowerCaseKeyword = keyword.toLowerCase();

        for (Post post : allPosts) {
            boolean titleMatches = post.getTitle().toLowerCase().contains(lowerCaseKeyword);



            if (titleMatches) {
                matchingPosts.add(post);
            }
        }

        return matchingPosts;
    }

}