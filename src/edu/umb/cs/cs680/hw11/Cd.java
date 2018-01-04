package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Arrays;

public class Cd implements Command {
	
	public ArrayList<String> fsResources;
	
	private FileSystem fs;
	private String path;
	
	public Cd(FileSystem fs, String path) {
		this.fs = fs;
		this.path = path;
	}

	@Override
	public void execute() {
		this.fsResources = this.parse(path, "/");
		CountingVisitor visitor = new CountingVisitor();
		// TODO Auto-generated method stub
		if (this.path.equals("..")) {
			if (fs.getWorkingDir().getParent() != null) {
				fs.setWorkingDir(fs.getWorkingDir().getParent());
			}
			return;
		}
		if (this.path.equals("")) {
			fs.setWorkingDir(fs.getRootDir());
			return;
		}
		if (this.path.equals(".")) {
			return;			
		}
		Directory workingDir = fs.getWorkingDir();
		if (this.path.equals("/")) {
			workingDir = fs.getRootDir();
			return;			
		}
		if (path.startsWith("/")) 
			workingDir = fs.getRootDir();

		for (String name : fsResources) {
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
				System.out.println("cd: no such file or directory: " + name);
			}
		}
		fs.setWorkingDir(workingDir);
		workingDir.accept(visitor);
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
