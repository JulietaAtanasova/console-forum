package commands;

import java.util.List;

import commands.exceptions.CommandException;
import contracts.Forum;
import contracts.User;
import entities.users.Admin;
import entities.users.UserImpl;
import forum.Messages;
import utility.PasswordUtility;

public class RegisterCommand extends AbstractCommand {

	public RegisterCommand(Forum forum) {
		super(forum);
	}

	@Override
	public void execute() throws CommandException {
		List<User> users = this.getForum().getUsers();
		String userName = this.getData().get(1);
		String password = PasswordUtility.hash(this.getData().get(2));
		String email = this.getData().get(3);

		if(hasUserWithUserName(userName) || hasUserWithEmail(email)){
			throw new CommandException(Messages.USER_ALREADY_REGISTRED);
		}
		
		User user = null;
		if (this.getData().size() > 4) {
			String role = this.getData().get(4);

			switch (role.toLowerCase()) {
			case "administrator":
				if (!users.isEmpty()) {
					throw new CommandException(Messages.REG_ADMIN_NOT_ALLOWED);
				}
				user = new Admin(userName, password, email);
				break;
			default:
				user = new UserImpl(userName, password, email);
				break;
			}
		} else {
			user = new UserImpl(userName, password, email);
		}

		users.add(user);
		this.getForum().getOutput()
				.append(String.format(Messages.REGISTER_SUCCESS, userName, user.getId()));
	}

}
