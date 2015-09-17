package forum;

import java.util.stream.Collectors;

import contracts.Answer;
import entities.posts.BestAnswer;

public class ExtendedForum extends ForumImpl {

	@Override
	public void run() {
		this.setup();
		while (getStarted()) {
			welcomeUser();
			this.executeCommandLoop();
		}
	}

	protected void welcomeUser() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		if (this.getLogged()) {
			System.out.printf(Messages.USER_WELCOME_MESSAGE, this.getCurrentUser().getUserName());
			System.out.println();
		} else {
			System.out.println(Messages.GUEST_WELCOME_MESSAGE);
		}

		int hotQuestions = getHotQuestions();

		int activeUsers = getActiveUsers();
		System.out.printf(Messages.GENERAL_HEADER_MESSAGE, hotQuestions, activeUsers);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~");
	}

	private int getActiveUsers() {
		int activeUsers = this.getAnswers().stream().map(Answer::getAuthor)
				.collect(Collectors.groupingBy(u -> u, Collectors.counting())).values().stream().filter(v -> v >= 3)
				.collect(Collectors.toList()).size();
		return activeUsers;
	}

	private int getHotQuestions() {
		int hotQuestions = this.getQuestions().stream()
				.filter(q -> q.getAnswers().stream().anyMatch(a -> a instanceof BestAnswer))
				.collect(Collectors.toList()).size();
		return hotQuestions;
	}

}
