package entities.posts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import contracts.Answer;
import contracts.Question;
import contracts.User;

public class QuestionImpl implements Question {
	private int id;
	private String title;
	private String body;
	private List<Answer> answers;
	private User author;

	public QuestionImpl(int id, String title, String body, User author) {
		this.setId(id);
		this.setTitle(title);
		this.setBody(body);
		this.setAuthor(author);
		this.setAnswers(new ArrayList<Answer>());
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
	public String getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
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
	public List<Answer> getAnswers() {
		return this.answers;
	}

	private void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		String output = String.format(
				"[ Question ID: %s ]\nPosted by: %s\nQuestion Title: %s\nQuestion Body: %s\n====================",
				this.getId(), this.getAuthor().getUserName(), this.getTitle(), this.getBody());
		output += "\n" + printAnswers();
		return output;
	}

	private String printAnswers() {
		String result = "No answers";
		if (!this.getAnswers().isEmpty()) {
			result = "Answers:";
			String regularAnswers = "";
			List<Answer> answers = this.getAnswers().stream()
					.sorted((a1, a2) -> Integer.compare(a1.getId(), a2.getId())).collect(Collectors.toList());
			for (Answer answer : answers) {
				if (answer instanceof BestAnswer) {
					result += "\n" + answer;
					continue;
				}
				regularAnswers += "\n" + answer;
			}
			result += regularAnswers;
		}
		return result;
	}
}
