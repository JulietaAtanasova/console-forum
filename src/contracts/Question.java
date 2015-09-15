package contracts;

import java.util.List;

public interface Question {
	String getTitle();

	void setTitle(String title);

	List<Answer> getAnswers();
}
