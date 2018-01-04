package edu.umb.cs.cs680.hw11;

public class Chown implements Command {
	
	private FileSystem fs;
	private String name;
	private String newOwner;
	
	public Chown(FileSystem fs, String name, String owner)	{
		this.fs = fs;
		this.name = name;
		this.newOwner = owner;
	}

	@Override
	public void execute() {
		boolean foundit = false;
		for(FSElement lookupFile: fs.getWorkingDir().getChildren())	{
			if(lookupFile.getName().equals(name) || name.equals("*")) {
				foundit = true;
				// delete it!
				lookupFile.setOwner(newOwner);
			}
		}
		if(!foundit) System.out.println("shell: chown: cannot find file/directory!");
	}

}
