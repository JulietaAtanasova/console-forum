package commands.factories;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import commands.AbstractCommand;
import contracts.Executable;
import contracts.Forum;

public final class CommandFactory {
	private final static String commandsPackage = "commands.";
	private final static String commandSuffix = "Command";

	public static Executable create(String commandInput, Forum forum) {
		String[] data = commandInput.split(" ");
		String commandName = data[0].toLowerCase();
		List<Class<?>> classes = new ArrayList<>();
		classes = ClassFinder.find(commandsPackage);

		Class<?> commandClass = classes.stream()
				.filter(c -> c.getName().replace(commandSuffix, "").toLowerCase().equals(commandName)).findFirst().get();
		try {
			commandClass = Class.forName(commandsPackage + "RegisterCommand");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Constructor<?> cons[] = commandClass.getConstructors();
		AbstractCommand command = null;
		try {
			command = (AbstractCommand) cons[0].newInstance(forum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String field : data) {
			command.getData().add(field);
		}

		return (Executable) command;
	}

}
