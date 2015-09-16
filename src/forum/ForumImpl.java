package forum;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import commands.exceptions.CommandException;
import commands.factories.CommandFactory;
import contracts.Answer;
import contracts.Executable;
import contracts.Forum;
import contracts.Question;
import contracts.User;

public class ForumImpl implements Forum {

	private final String defaultAdminUser = "admin";
	private final String defaultAdminPassword = "admin";
	private final String defaultAdminEmail = "admin@example.com";

	private boolean started;
	private List<User> users;
	private List<Question> questions;
	private List<Answer> answers;
	private Question currentQuestion;
	private boolean logged;
	private User currentUser;
	private StringBuilder output;

	public ForumImpl() {
		this.setUsers(new ArrayList<User>());
		this.setQuestions(new ArrayList<Question>());
		this.setAnswers(new ArrayList<Answer>());
		this.setStarted(true);
		this.setOutput(new StringBuilder());
	}

	@Override
	public boolean getStarted() {
		return this.started;
	}

	@Override
	public void setStarted(boolean started) {
		this.started = started;
	}

	@Override
	public List<User> getUsers() {
		return this.users;
	}

	private void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public List<Question> getQuestions() {
		return this.questions;
	}

	private void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public List<Answer> getAnswers() {
		return this.answers;
	}

	private void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public Question getCurrentQuestion() {
		return this.currentQuestion;
	}

	@Override
	public void setCurrentQuestion(Question question) {
		this.currentQuestion = question;
	}

	@Override
	public boolean getLogged() {
		return this.currentUser != null;
	}

	@Override
	public User getCurrentUser() {
		return this.currentUser;
	}

	@Override
	public void setCurrenUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public StringBuilder getOutput() {
		return this.output;
	}

	private void setOutput(StringBuilder output) {
		this.output = output;
	}

	@Override
	public void run() {
		this.setup();
		while (this.started) {
			this.executeCommandLoop();
		}
	}

	protected void executeCommandLoop() {
		this.setOutput(new StringBuilder());
		Scanner scanner = new Scanner(System.in);
		String inputCommand = scanner.nextLine();

		Executable command = CommandFactory.create(inputCommand, this);
		try {
			command.execute();
		} catch (CommandException e) {
			this.getOutput().append(e.getMessage());
		} catch (IllegalStateException e){
			this.getOutput().append(e.getMessage());
		}
		System.out.println(this.getOutput());
	}

	protected void setup() {
		String registerAdminCommand = String.format("register %s %s $s ADMINISTRATOR", defaultAdminUser,
				defaultAdminPassword, defaultAdminEmail);
		Executable command = CommandFactory.create(registerAdminCommand, this);
		
		try {
			command.execute();
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
}
