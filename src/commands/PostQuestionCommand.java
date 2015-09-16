package commands;

import commands.exceptions.CommandException;
import contracts.Forum;
import contracts.Question;
import contracts.User;
import entities.posts.QuestionImpl;
import forum.Messages;
import utility.PasswordUtility;

public class PostQuestionCommand extends AbstractCommand {

	public PostQuestionCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		String title = this.getData().get(1);
		String body = PasswordUtility.hash(this.getData().get(2));

		if(!this.getForum().getLogged()){
			throw new CommandException(Messages.NOT_LOGGED);
		}
		
		int questionId = this.getForum().getQuestions().size() + 1;
		User currentUser = this.getForum().getCurrentUser();
		Question question = new QuestionImpl(questionId, title, body, currentUser);
		this.getForum().getQuestions().add(question);
		this.getForum().getOutput()
				.append(String.format(Messages.POST_QUESTION_SUCCESS, this.getForum().getQuestions().size()));
	}

}
