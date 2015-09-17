package entities.posts;

import contracts.User;

public class BestAnswer extends AnswerImpl {

	public BestAnswer(int id, String body, User author) {
		super(id, body, author);
	}

	@Override
	public String toString() {
		return "********************\n" + super.toString() + "\n********************";
	}
}
