package edu.causwict.restapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	// 외래키
	private Long professor_id;
	private Long subject_id;

	private String title;
	private String content;

	public Post() {}

	public Post(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
}
