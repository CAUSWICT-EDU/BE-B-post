package edu.causwict.restapi.repository;

import edu.causwict.restapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);
    List<Post>findByTitleContaining(String keyword);
}
