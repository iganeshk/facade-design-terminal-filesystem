package edu.umb.cs.cs680.hw11;

public class VirusCheck implements Command {

	private FileSystem fs;
	
	public VirusCheck(FileSystem fs) {
		// TODO Auto-generated constructor stub
		this.fs = fs;
	}

	@Override
	public void execute() {
		VirusCheckingVisitor visitor = new VirusCheckingVisitor();
		fs.getWorkingDir().accept(visitor);
		System.out.println("Directories Quarantined: "+ visitor.dirQuarantinedNum);
		System.out.println("Files Quarantined: "+ visitor.fileQuarantinedNum);
		System.out.println("Link Quarantined: "+ visitor.linkQuarantinedNum);
	}

}
