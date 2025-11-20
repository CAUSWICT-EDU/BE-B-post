package edu.causwict.restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.causwict.restapi.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	Optional<Tag> findByTagName(String tagName);

	boolean existsByTagName(String tagName);
}
