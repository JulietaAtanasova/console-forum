package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import commands.exceptions.CommandException;
import contracts.Forum;
import contracts.Question;
import forum.Messages;

public class ShowQuestionsCommand extends AbstractCommand {

	public ShowQuestionsCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		List<Question> questions = this.getForum().getQuestions();
		if (questions.isEmpty()) {
			throw new CommandException(Messages.NO_QUESTIONS);
		}

		this.getForum().setCurrentQuestion(null);
		List<String> questionsToPrint = new ArrayList<String>(
				questions.stream().map(Object::toString).collect(Collectors.toList()));
		this.getForum().getOutput().append(String.join("\n", questionsToPrint));
	}

}
