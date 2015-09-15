package commands;

import java.util.ArrayList;
import java.util.List;

import commands.exceptions.CommandException;
import contracts.Command;
import contracts.Forum;

public abstract class AbstractCommand implements Command {

	private final List<String> data = new ArrayList<String>();
	private Forum forum;
	
	protected AbstractCommand(Forum forum){
		this.setForum(forum);
	}
	
	public Forum getForum(){
		return this.getForum();
	}
	
	private void setForum(Forum forum){
		this.forum = forum;
	}
	
	public List<String> getData(){
		return this.data;
	}
	public abstract void execute() throws CommandException;
}
