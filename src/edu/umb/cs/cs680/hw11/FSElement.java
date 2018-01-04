package edu.umb.cs.cs680.hw11;

import java.util.Date;

public class FSElement {
	
	protected String owner;
	protected String name;
//	protected String permissions;
	
	protected Date created;
	protected Date lastModified;
	
	protected int size;
	protected String type;
	protected Directory parent;
	protected FSElement target;
	
	public FSElement(Directory parent, String name, String owner, Date created, Date lastModified)	{
		
		this.name = name;
		this.owner = owner;
		this.created = created;
		this.lastModified = lastModified;
		this.parent = parent;
//		if (this.parent != null) this.parent.appendChild(parent);
	}
	
	public boolean isFile()	{
		return true;
	}
	
	public Directory getParent()	{
		return this.parent;
	}
	
	public FSElement getTarget()	{
		return target;
	}
	
	public int getSize()	{
		return this.size;
	}
	
	public String getOwner()	{
		return this.owner;
	}
	
	public String getName()	{
		return this.name;
	}
	
	public String getType()	{
		return this.type;
	}
	
	public void setOwner(String owner)	{
		this.owner = owner;
	}
	
	public void setSize(int size)	{
		this.size = size;
	}
	
	public void setModifiedDate()	{
		this.lastModified = new Date();
	}
	
	public Date DateCreated()	{
		return this.created;
	}
	
	public Date DateModified()	{
		return this.lastModified;
	}
	
	public void accept(FSVisitor v) {
	}
	
	public String getPath() {
		StringBuffer path = new StringBuffer();
		path.append(this.name);
		FSElement res = this.parent;
		while(res != null)
		{
			path.insert(0, res.getName() + "/");
			res = res.parent;
		}
		return path.toString().substring(1);
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
