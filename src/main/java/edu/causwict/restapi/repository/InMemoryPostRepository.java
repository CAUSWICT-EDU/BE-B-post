package edu.causwict.restapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import edu.causwict.restapi.entity.PostVerification;
import edu.causwict.restapi.entity.enums.ErrorCode;
import edu.causwict.restapi.repository.enums.SearchMode;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import edu.causwict.restapi.entity.Post;

@Repository
public class InMemoryPostRepository {

	private final Map<Long, Post> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(0);

	// 저장
	public Post save(Post post) {
		if(PostVerification.getInstance().verify(post, this.findAll()) != ErrorCode.NO_ERROR) {
			return null;
		}
		if (post.getId() == null) {
			post.setId(sequence.incrementAndGet());
		}
		store.put(post.getId(), post);
		return post;
	}

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



}
