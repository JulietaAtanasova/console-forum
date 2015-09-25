package commands;

import java.util.List;

import commands.exceptions.CommandException;
import contracts.Answer;
import contracts.Forum;
import contracts.User;
import entities.posts.AnswerImpl;
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
		
		List<Answer> answers = this.getForum().getCurrentQuestion().getAnswers();
		Answer currentBestAnswer = null;
		for (Answer answer : answers) {
			if (answer instanceof BestAnswer) {
				currentBestAnswer = answer;
			}
		}
		
		if(currentBestAnswer != null){
			answers.remove(currentBestAnswer);
			Answer regularAnswer = new AnswerImpl(currentBestAnswer.getId(), currentBestAnswer.getBody(), currentBestAnswer.getAuthor());
			answers.add(regularAnswer);
		}
		
		Answer answer = this.getForum().getAnswers().stream().filter(a -> a.getId() == answerId).findFirst().get();
		BestAnswer bestAnswer = new BestAnswer(answerId, answer.getBody(), answer.getAuthor());
		this.getForum().getAnswers().remove(answer);
		this.getForum().getAnswers().add(bestAnswer);
		answers.remove(answer);
		answers.add(bestAnswer);

		this.getForum().getOutput().append(String.format(Messages.BEST_ANSWER_SUCCESS, answer.getId()));
	}

}
