package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Ln implements Command {
	private FileSystem fs;
	
	public ArrayList<String> targetPath;
	private String filename;
	public String target;
	
	public Ln(FileSystem fs, String filename) {
		this.fs = fs;
		this.filename = filename;
	}
	
	@Override
	public void execute() {
		Date d1 = new Date();
		FSElement targetFS = null;
		boolean dupe = false; //duplicate file flag
		this.targetPath = this.parse(target, "/");
		Directory workingDir = fs.getWorkingDir();
		Directory orignalWork = fs.getWorkingDir();
		for(FSElement lookupFile: workingDir.getChildren())	{
			if(lookupFile.getName().equals(filename)) dupe = true;
		}
		if(!dupe) {
			//check if targetPath element exists
			if (target.startsWith("/")) {
				workingDir = fs.getRootDir();
			}
			boolean exists = false;
			for (String name : targetPath) {
				for (FSElement child : workingDir.getChildren()) {
					if(child.getName().equals(targetPath.get(targetPath.size()-1))) {
						exists = true;
						targetFS = child;
					}
					if (exists) {
						break;
					}
					if (child.getName().equals(name)) {
							switch (child.getType()) {
							case "directory": {
								workingDir = (Directory) child;
								break;
							}
							case "link": {
								FSElement target = child.getTarget();
								if (target instanceof Directory) {
									workingDir = (Directory) target;
									break;
								} else {
									continue;
								}
							}
							default: {
								continue;
							}
						}
					}
				}
			}	// end of destpath check
			if (!exists) {
				System.out.println("ln: destination target is invalid: " + target);
			}
			if (exists && targetFS != null)	{
				// link it
				fs.ln(fs.getWorkingDir(), filename, "owner", d1, d1, targetFS);
			}
		}
		else {
			System.out.println("ln : a link with the same name exists here :" + filename);
		}
		fs.setWorkingDir(orignalWork);
	} //
	
	public ArrayList<String> parse(String input,String symbol) {
		ArrayList<String> res = new ArrayList<>();
		if (input.startsWith(symbol)) {
			input = input.substring(1);
		}
		res.addAll(Arrays.asList(input.split(symbol)));
		return res;
	}
}