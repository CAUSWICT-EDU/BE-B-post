package edu.causwict.restapi.domain;

import edu.causwict.restapi.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@Column(nullable = false, length = 30)
	private String title;

	@Column(nullable = false, length = 1000)
	private String content;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> commentList = new ArrayList<>();

	public Post(Long id, String title, String content) {
		super(id);
		this.title = title;
		this.content = content;
	}

	public void addComment(Comment comment) {
		commentList.add(comment);
		comment.setPost(this);
	}

}
