package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Arrays;

public class Cp implements Command {

//	public ArrayList<String> sourcePath = null;
	public ArrayList<String> destPath = null;
	public String dest = null;
	
	private FileSystem fs;
	private String filename;
	
	public Cp(FileSystem fs, String filename){
		this.fs = fs;
		this.destPath = new ArrayList<String>();
		this.filename = filename;
	}
	
	@Override
	public void execute() {
		boolean dupe = false; //duplicate file flag
		boolean foundFile = false;
		Directory workingDir = fs.getWorkingDir();
		Directory orignalWork = fs.getWorkingDir();
		if (this.dest.equals("..")) {
			if (fs.getWorkingDir().getParent() != null) {
				destPath = fs.getFullPath(fs.getWorkingDir());
				dest = String.join("", destPath);
				destPath = parse(dest, "/");
			}
		}
		//check if dir/file exists in workingDir
		for(FSElement lookupFile: workingDir.getChildren())	{
			if(lookupFile.getName().equals(filename))	{
				foundFile = true;
				// check if dest path exists & copy
				if (dest.startsWith("/")) {
					workingDir = fs.getRootDir();
				}
				for (String name : destPath) {
					boolean exists = false;
					for (FSElement child : workingDir.getChildren()) {
						if (exists) {
							break;
						}
						if (child.getName().equals(name)) {
								switch (child.getType()) {
								case "directory": {
									workingDir = (Directory) child;
									exists = true;
									break;
								}
								case "link": {
									FSElement target = child.getTarget();
									if (target instanceof Directory) {
										workingDir = (Directory) target;
										exists = true;
										break;
									} else {
										continue;
									}
								}
								default: {
									continue;
								}
							}
						} else {
							continue;
						}
					}
					if (!exists) {
						System.out.println("cp: destination Path is invalid: " + name);
					}
					if (exists)	{
						for(FSElement duplicate: workingDir.getChildren())	{
							if(duplicate.getName().equals(lookupFile.getName())) dupe = true;
						}
						if(!dupe) {
							workingDir.appendChild(lookupFile);
						}
						else System.out.println("cp: file/directory with name " + lookupFile.name + " already exists!");
					}
				}	// end of destpath check
			}// end of if
		}// end of working check
		if(!foundFile) System.out.println("shell: cp: cannot find file/directory with name " + filename);
		fs.setWorkingDir(orignalWork);
	}
	
	public ArrayList<String> parse(String input,String symbol) {
		ArrayList<String> res = new ArrayList<>();
		if (input.startsWith(symbol)) {
			input = input.substring(1);
		}
		res.addAll(Arrays.asList(input.split(symbol)));
		return res;
	}

}
