package edu.causwict.restapi.service;

import edu.causwict.restapi.entity.verifications.PostVerification;
import edu.causwict.restapi.repository.enums.SearchMode;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

	private final InMemoryPostRepository postRepository;

	public PostService(InMemoryPostRepository postRepository) { // 생성자 주입
		this.postRepository = postRepository;
	}

	/**
	 * 주어진 파라미터를 바탕으로 새 Post를 만듭니다.
	 *
	 * @param title Post의 제목
	 * @param content Post의 내용
	 * @return 저장된 Post를 반환합니다. 만약, 주어진 규칙에 맞지 않는다면 {@code null}을 반환합니다.
	 */
	@Nullable
	public Post create(String title, String content) {
		Post post = new Post(null, title, content);
		System.out.println(PostVerification.verify(post, postRepository));
		if(PostVerification.verify(post, postRepository) != null) {
			return null;
		}
		return postRepository.save(post);
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
		Post post = postRepository.findById(id);
		if(post == null) {
			return null;
		}
		if(title != null) post.setTitle(title);
		if(content != null) post.setContent(content);
		return post;
	}

	/**
	 * 주어진 ID를 갖는 Post를 반환합니다.
	 *
	 * @param id 글의 ID
	 * @return 해당하는 ID의 Post를 반환합니다. 만약 없다면 {@code null}을 반환합니다.
	 */
	@Nullable
	public Post get(Long id) {
		return postRepository.findById(id);
	}

	/**
	 * 모든 Post를 반환합니다.
	 *
	 * @return 저장된 모든 Post
	 */
	@NonNull
	public List<Post> list() {
		return postRepository.findAll();
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
		List<Post> postList = postRepository.findAll();
		if(mode == SearchMode.FIND_BY_TITLE) {
			return postList.stream().filter(
					post -> post.getTitle().contains(keyword)
			).collect(Collectors.toList());
		} else if(mode == SearchMode.FIND_BY_CONTENT) {
			return postList.stream().filter(
					post -> post.getContent().contains(keyword)
			).collect(Collectors.toList());
		} else {
			return postList;
		}
	}

}