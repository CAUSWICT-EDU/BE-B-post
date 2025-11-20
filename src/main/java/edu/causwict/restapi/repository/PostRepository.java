package edu.causwict.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.causwict.restapi.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	boolean existsByTitle(String title);

	boolean existsByTitleAndPostIdNot(String title, Long postId);

	List<Post> findByTitleContaining(String keyword);
}
