package commands;

import commands.exceptions.CommandException;
import contracts.Forum;
import contracts.Question;
import forum.Messages;

public class OpenQuestionCommand extends AbstractCommand {

	public OpenQuestionCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		String id = this.getData().get(1);

		if(!id.matches("\\d+")){
			throw new CommandException(Messages.NO_QUESTION);
		}
		
		int questionId = Integer.parseInt(id);
		if(!hasQuestion(questionId)){
			throw new CommandException(Messages.NO_QUESTION);
		}
		
		Question question = this.getForum().getQuestions().stream().filter(q -> q.getId() == questionId).findFirst().get();
		this.getForum().setCurrentQuestion(question);
		this.getForum().getOutput()
				.append(question);
	}

}
