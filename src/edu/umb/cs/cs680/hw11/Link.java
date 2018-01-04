package edu.umb.cs.cs680.hw11;

import java.util.Date;

public class Link extends FSElement	{

	protected FSElement target;
	
	public Link(Directory parent, String name, String owner, Date created, Date lastModified, FSElement target) {
		super(parent, name, owner, created, lastModified);
		// TODO Auto-generated constructor stub
		this.target = target;
		this.size = 0;
		this.type = "link";
	}
	
	public void accept(FSVisitor v) {
		v.visit(this);
	}
	
	@Override
	public boolean isFile()	{
		return true;
	}

	public int getTargetSize()	{
		return target.getSize();
	}
	
	public int getSize()	{
		return 0;
	}
	
	public String getName()	{
		return this.name;
	}
	
	public String getType()	{
		return "link";
	}

	public FSElement getTarget()	{
		return target;
	}

	public int getDiskUtil() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getTagetPath() {
		StringBuffer path = new StringBuffer();
		path.append(target.name);
		FSElement res = target.parent;
		while(res != null)
		{
			path.insert(0, res.getName() + "/");
			res = res.parent;
		}
		return path.toString().substring(1);
	}
}
