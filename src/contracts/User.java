package contracts;

import java.util.List;

public interface User {
	int getId();

	void setId(int id);

	String getUserName();

	void setUserName(String userName);

	String getPassword();

	void setPasword(String password);

	String getEmail();

	void setEmail(String email);

	List<Question> getQuestions();
}
