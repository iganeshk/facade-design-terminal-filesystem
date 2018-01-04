package edu.umb.cs.cs680.hw11;

import java.util.Date;

public class Mkdir implements Command {

	private String dirName;
	private FileSystem fs;
	
	public Mkdir(FileSystem fs, String dirName)		{
		this.fs = fs;
		this.dirName = dirName;
	}
	
	@Override
	public void execute() {
		boolean dupe = false;
		Date date = new Date();
		for(FSElement lookupFile: fs.getWorkingDir().getChildren())	{
			if(lookupFile.getName().equals(dirName)) dupe = true;
		}
		if(dupe) {
			System.out.println("shell: mkdir: error directory already exists: " + dirName);
		} else {	
			fs.mkdir(fs.getWorkingDir(), this.dirName, "root", date, date);
		}
	}

}
