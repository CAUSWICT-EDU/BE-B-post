package edu.causwict.restapi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(nullable = false, length = 255)
	private String email;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, length = 50)
	private String nickname;

	@Column(name = "created_time", nullable = false, updatable = false)
	private LocalDateTime createdTime;

	@Column(name = "deleted_time")
	private LocalDateTime deletedTime;

	// 양방향 연관관계: User -> Post (1:N)
	@OneToMany(mappedBy = "user")
	private List<Post> posts = new ArrayList<>();

	// 양방향 연관관계: User -> Comment (1:N)
	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new ArrayList<>();

	public User() {
		this.createdTime = LocalDateTime.now();
	}

	public User(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.createdTime = LocalDateTime.now();
	}

	// Getters and Setters
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getNickname() { return nickname; }
	public void setNickname(String nickname) { this.nickname = nickname; }

	public LocalDateTime getCreatedTime() { return createdTime; }
	public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

	public LocalDateTime getDeletedTime() { return deletedTime; }
	public void setDeletedTime(LocalDateTime deletedTime) { this.deletedTime = deletedTime; }

	public List<Post> getPosts() { return posts; }
	public void setPosts(List<Post> posts) { this.posts = posts; }

	public List<Comment> getComments() { return comments; }
	public void setComments(List<Comment> comments) { this.comments = comments; }
}
