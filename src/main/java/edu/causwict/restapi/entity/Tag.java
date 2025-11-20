package edu.causwict.restapi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private Long tagId;

	@Column(name = "tag_name", nullable = false, length = 50)
	private String tagName;

	@Column(name = "created_time", nullable = false, updatable = false)
	private LocalDateTime createdTime;

	// 양방향 연관관계: Tag -> Post (N:M)
	@ManyToMany(mappedBy = "tags")
	private List<Post> posts = new ArrayList<>();

	public Tag() {
		this.createdTime = LocalDateTime.now();
	}

	public Tag(String tagName) {
		this.tagName = tagName;
		this.createdTime = LocalDateTime.now();
	}

	// Getters and Setters
	public Long getTagId() { return tagId; }
	public void setTagId(Long tagId) { this.tagId = tagId; }

	public String getTagName() { return tagName; }
	public void setTagName(String tagName) { this.tagName = tagName; }

	public LocalDateTime getCreatedTime() { return createdTime; }
	public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

	public List<Post> getPosts() { return posts; }
	public void setPosts(List<Post> posts) { this.posts = posts; }
}
