package edu.umb.cs.cs680.hw11;

public class Dir implements Command {

	private String path;
	private FileSystem fs;
	
	public Dir(FileSystem fs, String path)		{
		this.fs = fs;
		this.path = path;
	}
	
	@Override
	public void execute() {
		System.out.println(path);
		System.out.println("Filename\t  " + "Filetype\t" + "  Owner\t\t\t" + "Size\t" + "Date Created");
		if (path.equals("..")) {
			for(FSElement res: fs.getWorkingDir().getParent().getChildren())	{
				if(res.getType().equals("directory"))
					System.out.println(res.getName() + "\t  " + res.getType() + "\t\t" + res.owner + "\t\t" + res.getSize() + "\t" + res.created);
				else System.out.println(res.getName() + "\t  " + res.getType() + "\t\t  " + res.owner + "\t\t      " + res.getSize() + "\t" + res.created);
			}
		} else if(path.equals(" ")) {
			for(FSElement res: fs.getWorkingDir().getChildren())	{
				if(res.getType().equals("directory"))
					System.out.println(res.getName() + "\t  " + res.getType() + "\t\t" + res.owner + "\t\t" + res.getSize() + "\t" + res.created);
				else System.out.println(res.getName() + "\t  " + res.getType() + "\t\t    " + res.owner + "\t\t      " + res.getSize() + "\t" + res.created);
			}
		} else System.out.println("dir: invalid arguments!");
	}
}
