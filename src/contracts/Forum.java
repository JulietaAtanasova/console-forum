package contracts;

import java.util.List;

public interface Forum {
	boolean getStarted();

	void setStarted(boolean started);

	List<User> getUsers();

	List<Question> getQuestions();

	List<Answer> getAnswers();

	Question getCurrentQuestion();

	void setCurrentQuestion(Question question);

	boolean getLogged();

	User getCurrentUser();

	void setCurrentUser(User currentUser);

	StringBuilder getOutput();

	void run();
}
