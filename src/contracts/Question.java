package contracts;

import java.util.List;

public interface Question extends Post{
	String getTitle();

	void setTitle(String title);

	List<Answer> getAnswers();
}
