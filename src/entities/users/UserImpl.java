package entities.users;

import java.util.ArrayList;
import java.util.List;

import contracts.Question;
import contracts.User;

public class UserImpl implements User {

	private int id;
	private String userName;
	private String password;
	private String email;
	private List<Question> questions;

	public UserImpl(int id, String name, String password, String email) {
		this.setId(id);
		this.setUserName(name);
		this.setPasword(password);
		this.setEmail(email);
		this.setQuestions(new ArrayList<Question>());
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
	public String getUserName() {
		return this.userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPasword(String password) {
		this.password = password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public List<Question> getQuestions() {
		return this.questions;
	}

	private void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
