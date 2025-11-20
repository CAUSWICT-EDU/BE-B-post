package edu.causwict.restapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;

	@Column(nullable = false, length = 500)
	private String content;

	@Column(name = "created_time", nullable = false, updatable = false)
	private LocalDateTime createdTime;

	// 양방향 연관관계: Comment -> User (N:1)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// 양방향 연관관계: Comment -> Post (N:1)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	public Comment() {
		this.createdTime = LocalDateTime.now();
	}

	public Comment(String content, User user, Post post) {
		this.content = content;
		this.user = user;
		this.post = post;
		this.createdTime = LocalDateTime.now();
	}

	// 연관관계 편의 메서드
	public void setUser(User user) {
		this.user = user;
		if (user != null && !user.getComments().contains(this)) {
			user.getComments().add(this);
		}
	}

	public void setPost(Post post) {
		this.post = post;
		if (post != null && !post.getComments().contains(this)) {
			post.getComments().add(this);
		}
	}

	// Getters and Setters
	public Long getCommentId() { return commentId; }
	public void setCommentId(Long commentId) { this.commentId = commentId; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	public LocalDateTime getCreatedTime() { return createdTime; }
	public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

	public User getUser() { return user; }

	public Post getPost() { return post; }
}
