package spring;

public class WriteRequest {
	private String title;
	private String author;
	private String password;
	private String content;
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
}
