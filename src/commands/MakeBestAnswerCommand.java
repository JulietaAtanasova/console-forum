package commands;

import commands.exceptions.CommandException;
import contracts.Answer;
import contracts.Forum;
import contracts.User;
import entities.posts.QuestionImpl;
import forum.Messages;

public class MakeBestAnswerCommand extends AbstractCommand {

	public MakeBestAnswerCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		String id = this.getData().get(1);

		if (!this.getForum().getLogged()) {
			throw new CommandException(Messages.NOT_LOGGED);
		}

		QuestionImpl currentQuestion = (QuestionImpl) this.getForum().getCurrentQuestion();
		if (currentQuestion == null) {
			throw new CommandException(Messages.NO_QUESTION_OPENED);
		}

		if (!id.matches("\\d+")) {
			throw new CommandException(Messages.NO_ANSWER);
		}

		int answerId = Integer.parseInt(id);
		if (!hasAnswer(answerId)) {
			throw new CommandException(Messages.NO_ANSWER);
		}

		User currentUser = this.getForum().getCurrentUser();

		System.out.println(currentUser.getId());
		System.out.println(this.getForum().getCurrentQuestion().getAuthor().getId());
		if ((currentUser.getId() != this.getForum().getCurrentQuestion().getAuthor().getId()) || !currentUser.getUserName().equals("admin")) {
			throw new CommandException(Messages.NO_PERMISSION);
		}

		Answer answer = this.getForum().getAnswers().stream().filter(a -> a.getId() == answerId).findFirst().get();
		currentQuestion.setBestAnswer(answer);
		
		this.getForum().getOutput().append(String.format(Messages.BEST_ANSWER_SUCCESS, answerId));
	}

}
