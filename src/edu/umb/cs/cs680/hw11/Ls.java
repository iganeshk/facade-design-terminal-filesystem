package edu.umb.cs.cs680.hw11;

public class Ls implements Command {
	
	private FileSystem fs;
	
	public Ls(FileSystem fs) {
		this.fs = fs;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for (FSElement res : fs.getChildren())	{
			System.out.println(res.getName()+"\t\t------ "+ res.getType());
		}
	}
	

}
