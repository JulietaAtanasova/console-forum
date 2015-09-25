package commands;

import java.util.List;
import java.util.stream.Collectors;

import commands.exceptions.CommandException;
import contracts.Answer;
import contracts.Forum;
import contracts.User;
import entities.posts.AnswerImpl;
import forum.Messages;

public class PostAnswerCommand extends AbstractCommand {

	public PostAnswerCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		String body = this.getData().get(1);

		if (!this.getForum().getLogged()) {
			throw new CommandException(Messages.NOT_LOGGED);
		}

		if (this.getForum().getCurrentQuestion() == null) {
			throw new CommandException(Messages.NO_QUESTION_OPENED);
		}

		User currentUser = this.getForum().getCurrentUser();
		int lastId = 0;
		if (this.getForum().getAnswers().size() > 0) {
			lastId = this.getForum().getAnswers().stream().sorted((a, b) -> Integer.compare(b.getId(), a.getId()))
					.collect(Collectors.toList()).get(0).getId();
		}

		int answerId = lastId + 1;
		Answer answer = new AnswerImpl(answerId, body, currentUser);

		List<Answer> answers = this.getForum().getCurrentQuestion().getAnswers();
		answers.add(answer);
		this.getForum().getAnswers().add(answer);
		this.getForum().getOutput().append(String.format(Messages.POST_ANSWER_SUCCESS, answerId));
	}

}
