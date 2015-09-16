package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import commands.exceptions.CommandException;
import contracts.Command;
import contracts.Forum;
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

	protected boolean isUserWithUserName(String userName) {
		Optional<User> existingUser = this.getForum().getUsers().stream()
				.filter(u -> u.getUserName().equals(userName)).findFirst();
		
		if (existingUser.isPresent()) {
			return true;
		}
		return false;
	}

	protected boolean isUserWithEmail(String email) {
		Optional<User> existingUser = this.getForum().getUsers().stream().filter(u -> u.getEmail().equals(email))
				.findFirst();
		if (existingUser.isPresent()) {
			return true;
		}
		return false;
	}
}
