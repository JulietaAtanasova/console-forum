package entities.posts;

import contracts.Answer;
import contracts.User;

public class AnswerImpl implements Answer {
	private int id;
	private String body;
	private User author;

	public AnswerImpl(int id, String body, User author) {
		this.setId(id);
		this.setBody(body);
		this.setAuthor(author);
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getBody() {
		return this.body;
	}

	@Override
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public User getAuthor() {
		return this.author;
	}

	@Override
	public void setAuthor(User author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return String.format("[ Answer ID: %s ]\nPosted by: %s\nAnswer Body: %s\n--------------------", this.getId(),
				this.getAuthor().getUserName(), this.getBody());
	}
}
