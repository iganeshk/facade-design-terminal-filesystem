package edu.umb.cs.cs680.hw11;

public class History implements Command {

	private CommandHistory cmdHist;
	
	public History(CommandHistory cmdHist)	{
		this.cmdHist = cmdHist;
	}

	@Override
	public void execute() {
		// parse all the history and print it out
		int num = cmdHist.getHistory().size()-1;
		for(int i = num; i >=0; i--){
			System.out.println(cmdHist.getHistory().get(i));
		}
	}
}
