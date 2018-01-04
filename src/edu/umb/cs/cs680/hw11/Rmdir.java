package edu.umb.cs.cs680.hw11;

public class Rmdir implements Command {

	private String dirName;
	private FileSystem fs;
	
	public Rmdir(FileSystem fs, String dirName)		{
		this.fs = fs;
		this.dirName = dirName;
	}
	
	@Override
	public void execute() {
		boolean foundDir = false;
		for(FSElement lookupFile: fs.getWorkingDir().getChildren())	{
			if(lookupFile.getName().equals(dirName)) {
				if(lookupFile.getType().equals("directory")) 
					{
					foundDir = true;
					}
			}
		}
		if(foundDir) fs.deleteChild(dirName);
		if(!foundDir) System.out.println("shell: rmdir: cannot find the directory!");

	}

}
