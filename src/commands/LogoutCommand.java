package commands;

import commands.exceptions.CommandException;
import contracts.Forum;
import forum.Messages;

public class LogoutCommand extends AbstractCommand {

	public LogoutCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		if(!this.getForum().getLogged()){
			throw new CommandException(Messages.NOT_LOGGED);
		}
		
		this.getForum().setCurrenUser(null);
		this.getForum().getOutput()
				.append(String.format(Messages.LOGOUT_SUCCESS));
	}

}
