package commands.factories;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import commands.AbstractCommand;
import contracts.Executable;
import contracts.Forum;

public final class CommandFactory {
	private static final String COMMANDS_PATH = ".\\bin\\commands";
	private static final String COMMANDS_PACKAGE = "commands.";
	private static final String COMMAND_SUFFIX = "Command";
	private static final String CLASS_SUFFIX = ".class";

	public static Executable create(String commandInput, Forum forum) {
		String[] data = commandInput.split(" ");
		String commandName = data[0].toLowerCase();
		Path commandPath = findCommandPath(commandName);
		if (commandPath == null) {
			return null;
		}

		String className = commandPath.getFileName().toString().replace(CLASS_SUFFIX, "");
		Class<?> commandClass = findCommandClass(className);
		AbstractCommand command = createCommand(forum, commandClass);
		for (String field : data) {
			command.getData().add(field);
		}

		return (Executable) command;
	}

	private static Path findCommandPath(String commandName) {
		Path commandPath = null;
		Path dir = Paths.get(COMMANDS_PATH);
		try (Stream<Path> fileTree = Files.walk(dir)) {
			try {
				commandPath = fileTree.filter(f -> f.getFileName().toString().toLowerCase()
						.equals(commandName + COMMAND_SUFFIX.toLowerCase() + CLASS_SUFFIX)).findFirst().get();
			} catch (NoSuchElementException e) {
				return commandPath;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return commandPath;
	}

	private static Class<?> findCommandClass(String className) {
		Class<?> commandClass = null;
		try {
			commandClass = Class.forName(COMMANDS_PACKAGE + className);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return commandClass;
	}

	private static AbstractCommand createCommand(Forum forum, Class<?> commandClass) {
		Constructor<?> cons[] = commandClass.getConstructors();
		AbstractCommand command = null;
		try {
			command = (AbstractCommand) cons[0].newInstance(forum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return command;
	}
}
