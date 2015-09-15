package contracts;

import commands.exceptions.CommandException;

public interface Executable {
	void execute() throws CommandException;
}
