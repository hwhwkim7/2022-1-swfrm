package spring;

import java.time.LocalDateTime;

public class Board {
	private Long id;
	private String title;
	private String content;
	private String author;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Board(String title,
			String author, String password,
			LocalDateTime createdAt, LocalDateTime updatedAt, String content) {
		this.title = title;
		this.author = author;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.content = content;
	}

	void setId(Long id) {
		this.id = id;
	}
	
	void setContent(String content) {
		this.content = content;
	}
	
	void setUpdatedAtNow() {
		this.updatedAt = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getPassword() {
		return password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
