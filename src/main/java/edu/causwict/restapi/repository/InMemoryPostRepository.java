package edu.causwict.restapi.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import edu.causwict.restapi.entity.verifications.PostVerification;
import edu.causwict.restapi.repository.enums.SearchMode;
import edu.causwict.restapi.repository.utils.GenerateIDUtil;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import edu.causwict.restapi.entity.Post;

@Repository
public class InMemoryPostRepository {

	private final Map<Long, Post> store = new ConcurrentHashMap<>();
	private final GenerateIDUtil<Post> idUtil = new GenerateIDUtil<>();
	private LocalDateTime lastGenerated = null;

	/**
	 * 주어진 Post를 저장합니다.
	 *
	 * @param post Post
	 * @return 저장된 Post를 반환합니다. 만약, 주어진 규칙에 맞지 않는다면 {@code null}을 반환합니다.
	 */
	@Nullable
	public Post save(Post post) {
		if(PostVerification.verify(post, this) != null) {
			return null;
		}
		post = idUtil.generateID(post);
		lastGenerated = post.getGenerated();
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
	 * 주어진 id의 Post 객체를 수정합니다.
	 *
	 * @param id id
	 * @param title 새로 수정할 제목
	 * @param content 새로 수정할 내용
	 * @return 만약 수정에 성공했다면 성공한 Post 객체를 반환합니다. 만약 해당 id의 Post를 찾을 수 없다면 {@code null}을 반환합니다.
	 */
	@Nullable
	public Post edit(Long id, String title, String content) {
		Post post = store.get(id);
		if(post == null) {
			return null;
		}
		if(title != null) post.setTitle(title);
		if(content != null) post.setContent(content);
		return post;
	}

	/**
	 * 주어진 키워드와 검색 모드를 바탕으로 게시물을 검색합니다.
	 *
	 * @param keyword 검색에 사용할 키워드
	 * @param mode 검색 모드
	 * @return 검색된 게시물 리스트를 반환합니다.
	 */
	@NonNull
	public List<Post> search(String keyword, SearchMode mode) {
		List<Post> postList = findAll();
		if(mode == SearchMode.FIND_BY_TITLE) {
			return postList.stream().filter(
					post -> post.getTitle().contains(keyword)
			).collect(Collectors.toList());
		} else if(mode == SearchMode.FIND_BY_CONTENT) {
			return postList.stream().filter(
					post -> post.getContent().contains(keyword)
			).collect(Collectors.toList());
		} else {
			return this.findAll();
		}
	}

	/**
	 * 주어진 ID를 갖는 Post를 반환합니다.
	 *
	 * @param id 글의 ID
	 * @return 해당하는 ID의 Post를 반환합니다. 만약 없다면 {@code null}을 반환합니다.
	 */
	public Post get(Long id) {
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
