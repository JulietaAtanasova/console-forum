package commands;

import commands.exceptions.CommandException;
import contracts.Answer;
import contracts.Forum;
import contracts.User;
import entities.posts.BestAnswer;
import entities.posts.QuestionImpl;
import entities.users.Admin;
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

		if (currentUser.getId() != currentQuestion.getAuthor().getId() && !(currentUser instanceof Admin)){
			throw new CommandException(Messages.NO_PERMISSION);
		}

		Answer answer = this.getForum().getAnswers().stream().filter(a -> a.getId() == answerId).findFirst().get();
		BestAnswer bestAnswer = new BestAnswer(answer.getId(), answer.getBody(), answer.getAuthor());
		this.getForum().getCurrentQuestion().getAnswers().remove(answer);
		this.getForum().getCurrentQuestion().getAnswers().add(bestAnswer);
		this.getForum().getOutput().append(String.format(Messages.BEST_ANSWER_SUCCESS, answer.getId()));
	}

}
