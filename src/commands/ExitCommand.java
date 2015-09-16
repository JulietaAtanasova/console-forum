package commands;

import contracts.Forum;

public class ExitCommand extends AbstractCommand {

	public ExitCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() {
		this.getForum().setStarted(false);
	}

}
