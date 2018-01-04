package edu.umb.cs.cs680.hw11;

public class Redo implements Command {

	private CommandHistory history;
	
	public Redo(CommandHistory history)	{
		this.history = history;
	}
	
	@Override
	public void execute() {
		Command old = history.getPrevCmd();
		old.execute();
	}

}
