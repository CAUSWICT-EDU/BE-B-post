package edu.causwict.restapi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long postId;

	@Column(nullable = false, length = 255)
	private String title;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(name = "view_count", nullable = false)
	private Integer viewCount = 0;

	@Column(name = "created_time", nullable = false, updatable = false)
	private LocalDateTime createdTime;

	// 양방향 연관관계: Post -> User (N:1)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	// 양방향 연관관계: Post -> Comment (1:N)
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	// 양방향 연관관계: Post -> Tag (N:M)
	@ManyToMany
	@JoinTable(
		name = "post_tag",
		joinColumns = @JoinColumn(name = "post_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags = new ArrayList<>();

	public Post() {
		this.createdTime = LocalDateTime.now();
		this.viewCount = 0;
	}

	public Post(String title, String content, User user) {
		this.title = title;
		this.content = content;
		this.user = user;
		this.createdTime = LocalDateTime.now();
		this.viewCount = 0;
	}

	// 연관관계 편의 메서드
	public void setUser(User user) {
		this.user = user;
		if (user != null && !user.getPosts().contains(this)) {
			user.getPosts().add(this);
		}
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setPost(this);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
	}

	public void addTag(Tag tag) {
		tags.add(tag);
		if (!tag.getPosts().contains(this)) {
			tag.getPosts().add(this);
		}
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.getPosts().remove(this);
	}

	// Getters and Setters
	public Long getPostId() { return postId; }
	public void setPostId(Long postId) { this.postId = postId; }

	// getId() 메서드 유지 (기존 코드 호환성)
	public Long getId() { return postId; }
	public void setId(Long id) { this.postId = id; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	public Integer getViewCount() { return viewCount; }
	public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }

	public LocalDateTime getCreatedTime() { return createdTime; }
	public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

	public User getUser() { return user; }

	public List<Comment> getComments() { return comments; }
	public void setComments(List<Comment> comments) { this.comments = comments; }

	public List<Tag> getTags() { return tags; }
	public void setTags(List<Tag> tags) { this.tags = tags; }
}

