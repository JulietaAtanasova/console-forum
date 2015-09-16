package commands.factories;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

		Path commandPath = null;
		Path dir = Paths.get(COMMANDS_PATH);
		try (Stream<Path> fileTree = Files.walk(dir)) {
			commandPath = fileTree
					.filter(f -> f.getFileName().toString().toLowerCase().equals(commandName + COMMAND_SUFFIX.toLowerCase() + CLASS_SUFFIX))
					.findFirst().get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String className = commandPath.getFileName().toString().replace(CLASS_SUFFIX, "");
		Class<?> commandClass = null;
		try {
			commandClass = Class.forName(COMMANDS_PACKAGE + className);
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
