package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class Pwd implements Command {
	
	private FileSystem fs;

	public Pwd(FileSystem fs)		{
		this.fs = fs;
	}
	
	@Override
	public void execute() {
		ArrayList<String> fullPath = fs.getFullPath(fs.getWorkingDir());
		StringBuilder parsedFullPath = new StringBuilder();
		for (String dir: fullPath){
			parsedFullPath.append(dir + "/");
		}
		parsedFullPath.append(fs.getWorkingDir().getName());
		String out = parsedFullPath.toString();
		System.out.println(out.replaceAll("//", "/"));
	}

}
