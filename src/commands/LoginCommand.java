package commands;

import commands.exceptions.CommandException;
import contracts.Forum;
import contracts.User;
import forum.Messages;
import utility.PasswordUtility;

public class LoginCommand extends AbstractCommand {

	public LoginCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		String userName = this.getData().get(1);
		String password = PasswordUtility.hash(this.getData().get(2));

		if(this.getForum().getLogged()){
			throw new CommandException(Messages.ALREADY_LOGGED_IN);
		}
		
		if(!isUserWithUserName(userName)){
			throw new CommandException(Messages.INVALID_LOGIN_DETAILS);
		}
		
		User user = this.getForum().getUsers().stream().filter(u -> u.getUserName().equals(userName)).findFirst().get();
		if(!user.getPassword().equals(password)){
			throw new CommandException(Messages.INVALID_LOGIN_DETAILS);
		}
		
		this.getForum().setCurrenUser(user);
		this.getForum().getOutput()
				.append(String.format(Messages.LOGIN_SUCCESS, user.getUserName()));
	}

}
