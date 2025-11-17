package edu.causwict.restapi.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import edu.causwict.restapi.repository.enums.ErrorCode;
import edu.causwict.restapi.utils.GenerateIDUtil;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import edu.causwict.restapi.domain.Post;

@Repository
public class InMemoryPostRepository {

	private final Map<Long, Post> store = new ConcurrentHashMap<>();
	private final GenerateIDUtil<Post> idUtil = new GenerateIDUtil<>();
	private LocalDateTime lastGenerated = null;

	/**
	 * 주어진 Post를 저장합니다.
	 *
	 * @param post Post
	 * @return 저장된 Post를 반환합니다.
	 */
	@NonNull
	public Post save(Post post) {
		idUtil.generateID(post);
		lastGenerated = post.getCreatedAt();
		store.put(post.getId(), post);
		return post;
	}

	/**
	 * 현재 저장된 모든 Post를 반환합니다.
	 *
	 * @return 저장된 모든 Post
	 */
	@NonNull
	public List<Post> findAll() {
		return new ArrayList<>(store.values());
	}

	/**
	 * 주어진 ID를 갖는 Post를 반환합니다.
	 *
	 * @param id 글의 ID
	 * @return 해당하는 ID의 Post를 반환합니다.
	 * @throws NoSuchElementException 만약 해당 ID의 Post가 존재하지 않을 경우 발생합니다.
	 */
	@NonNull
	public Post findById(Long id) throws NoSuchElementException {
		Post post = store.get(id);
		if(post == null) {
			throw new NoSuchElementException(ErrorCode.NO_SUCH_ID_POST.getErrorMessage());
		}
		return store.get(id);
	}

	/**
	 * 마지막으로 글을 쓴 시간을 반환합니다.
	 *
	 * @return 마지막으로 글을 쓴 시간을 반환합니다. 만약 아무 글도 없다면 {@code null}을 반환합니다.
	 */
	@Nullable
	public LocalDateTime getLastGenerated() {
		return lastGenerated;
	}

}
