package edu.causwict.restapi.repository;

import edu.causwict.restapi.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
