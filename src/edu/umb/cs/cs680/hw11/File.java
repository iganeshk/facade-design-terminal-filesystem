package edu.umb.cs.cs680.hw11;

import java.util.Date;

public class File extends FSElement {

	public File(Directory parent, String name, String owner, Date created, Date lastModified, int size) {
		super(parent, name, owner, created, lastModified);
		// TODO Auto-generated constructor stub
		this.size = size;
		this.type = "file";
	}

	public void accept(FSVisitor v) {
		v.visit(this);
	}
	
	@Override
	public boolean isFile() {
		return true;
	}
	
	public int getSize()	{
		return this.size;
	}
	
	public String getName()	{
		return this.name;
	}

	public String getType()	{
		return "file";
	}

	public int getDiskUtil() {
		// TODO Auto-generated method stub
		return 0;
	}
}
