package edu.causwict.restapi.domain;

import edu.causwict.restapi.domain.enums.SemesterType;
import edu.causwict.restapi.domain.enums.TermType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@Column(nullable=false, length = 1000)
	private String content;

	@Column(nullable=false)
	private Short year;

	@Enumerated(EnumType.STRING)
	private SemesterType semester;

	@Enumerated(EnumType.STRING)
	private TermType term;

	@Column
	private LocalDateTime deleted_at;

	public void updateContent(String new_content) { this.content = new_content; }

	public void delete() { this.deleted_at = LocalDateTime.now(); }

}
