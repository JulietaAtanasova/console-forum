package commands;

import java.util.stream.Collectors;

import commands.exceptions.CommandException;
import contracts.Forum;
import contracts.Question;
import contracts.User;
import entities.posts.QuestionImpl;
import forum.Messages;

public class PostQuestionCommand extends AbstractCommand {

	public PostQuestionCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		String title = this.getData().get(1);
		String body = this.getData().get(2);

		if (!this.getForum().getLogged()) {
			throw new CommandException(Messages.NOT_LOGGED);
		}

		int lastId = 0;
		if (this.getForum().getAnswers().size() > 0) {
			lastId = this.getForum().getQuestions().stream().sorted((a, b) -> Integer.compare(b.getId(), a.getId()))
					.collect(Collectors.toList()).get(0).getId();
		}

		int questionId = lastId + 1;
		User currentUser = this.getForum().getCurrentUser();
		Question question = new QuestionImpl(questionId, title, body, currentUser);
		this.getForum().getQuestions().add(question);
		this.getForum().getOutput()
				.append(String.format(Messages.POST_QUESTION_SUCCESS, this.getForum().getQuestions().size()));
	}

}
