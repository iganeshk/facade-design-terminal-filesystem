package edu.umb.cs.cs680.hw11;

public class Search implements Command {

	private FileSystem fs;
	private String name;

	public Search(FileSystem fs, String string) {
		this.fs = fs;
		this.name = string;
	}

	@Override
	public void execute() {
		FileSearchVisitor visitor = new FileSearchVisitor(this.name);
		fs.getWorkingDir().accept(visitor);
		System.out.println("Found about " + visitor.foundFiles + " matching files and directors(s).");
	}

}
