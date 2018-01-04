package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

	private List<String> history = null;
	private Command cmdHist = null;
	
	public CommandHistory(){
		history = new ArrayList<String>();
	}
	
	public void push (String cmd){
		history.add(cmd);
	}
	
	public void saveCMD (Command cmd)	{
		cmdHist = cmd;
	}

	public List<String> getHistory(){
		return this.history;
	}
	
	public Command getPrevCmd()	{
		return this.cmdHist;
	}
}
