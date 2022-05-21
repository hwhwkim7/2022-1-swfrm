package spring;

import java.time.LocalDateTime;

public class Board {
	private Long id;
	private String title;
	private String content;
	private String author;
	private String password;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;

	public Board(String title, String content, String author,
				 String password, LocalDateTime createAt, LocalDateTime updateAt) {
		this.title = title;
		this.password = password;
		this.content = content;
		this.author = author;
		this.createAt = createAt;
		this.updateAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public void changeContent(String password, String content) {
		if (!this.password.equals(password))
			throw new WrongIdPasswordException();
		this.content = content;
		this.updateAt = LocalDateTime.now();
	}
}
