package edu.causwict.restapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import edu.causwict.restapi.util.IdGenerator;
import org.springframework.stereotype.Repository;

import edu.causwict.restapi.domain.Post;

@Repository
public class InMemoryPostRepository {

	private final Map<Long, Post> store = new ConcurrentHashMap<>();
	private final IdGenerator idGenerator = new IdGenerator();

	public Post save(Post post) {
		if (post.getId() == null) {
			post.setId(idGenerator.getSequence());
		}
		store.put(post.getId(), post);
		return post;
	}

	public Optional<Post> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	/*public Optional<Post> findByTitle(String title) {
        return store.values().stream()
                .filter(e -> e.getTitle().equals(title))
				.findFirst();
	}*/

	public List<Post> findAll() {
		return new ArrayList<>(store.values());
	}

}
