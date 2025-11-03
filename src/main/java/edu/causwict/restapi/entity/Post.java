package edu.causwict.restapi.entity;

public class Post extends BaseEntity {

	private String title;
	private String content;

	public Post() {}

	public Post(Long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
}
