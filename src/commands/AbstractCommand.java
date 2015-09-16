package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import commands.exceptions.CommandException;
import contracts.Answer;
import contracts.Command;
import contracts.Forum;
import contracts.Question;
import contracts.User;

public abstract class AbstractCommand implements Command {

	private final List<String> data = new ArrayList<String>();
	private Forum forum;

	protected AbstractCommand(Forum forum) {
		this.setForum(forum);
	}

	public Forum getForum() {
		return this.forum;
	}

	private void setForum(Forum forum) {
		this.forum = forum;
	}

	public List<String> getData() {
		return this.data;
	}

	public abstract void execute() throws CommandException;

	protected boolean hasUserWithUserName(String userName) {
		Optional<User> existingUser = this.getForum().getUsers().stream().filter(u -> u.getUserName().equals(userName))
				.findFirst();

		if (existingUser.isPresent()) {
			return true;
		}
		return false;
	}

	protected boolean hasUserWithEmail(String email) {
		Optional<User> existingUser = this.getForum().getUsers().stream().filter(u -> u.getEmail().equals(email))
				.findFirst();
		if (existingUser.isPresent()) {
			return true;
		}
		return false;
	}

	protected boolean hasQuestion(int id) {
		Optional<Question> question = this.getForum().getQuestions().stream().filter(q -> q.getId() == id)
				.findFirst();
		if (question.isPresent()) {
			return true;
		}
		return false;
	}
	
	protected boolean hasAnswer(int id) {
		Optional<Answer> answer = this.getForum().getAnswers().stream().filter(a -> a.getId() == id)
				.findFirst();
		if (answer.isPresent()) {
			return true;
		}
		return false;
	}
}
