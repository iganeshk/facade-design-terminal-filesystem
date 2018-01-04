package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Directory extends FSElement	{

	private ArrayList<FSElement>	children = new ArrayList<FSElement>();
	
	public Directory(Directory parent, String name, String owner, Date created, Date lastModified)		{
		super(parent, name, owner, created, lastModified);
		this.size = 0;
		this.type = "directory";
	}
	
	public ArrayList<FSElement> getChildren()	{
		return this.children;
	}
	
	public void appendChild(FSElement child)	{
		children.add(child);
	}
	
	@Override
	public boolean isFile()	{
		return false;
	}
	
	public String getName()	{
		return this.name;
	}
	
	public int getSize()	{	
		// get total size of directory
		size = 0;
		//recursively get sizes (file & dirs)
		for (FSElement resource: this.children)	{
			size = size + resource.getSize();
		}
		return size;		
	}
	
	public void accept(FSVisitor v) {
		v.visit(this);
		for (FSElement res: children) {
			res.accept((v));
		}
	}
	
	public void rescursiveIndex(int spacer, boolean last)	{
		//print the resource list (files/dirs)
		Iterator<FSElement> recursiveList = children.iterator();
		String styler = "├── ";
		while(recursiveList.hasNext())	{
			FSElement resource = recursiveList.next();
			if (!last) System.out.print("│");
			for(int i=0; i<spacer;i++) {
				System.out.print("    ");
			}
			//check for last element
			if(!recursiveList.hasNext())	{
				styler = "└── ";
			}
			System.out.print(styler + resource.getName() + " ("+ resource.getType()+ " " + resource.getSize()+ " bytes");
			if(resource.getType().equals("link")) {
				System.out.print(" --> " + resource.getTagetPath());
			}
			System.out.print(")\n");
			// if a folder, we recurse into it.
			if(!resource.isFile()) {
				((Directory) resource).rescursiveIndex(spacer+1, last);
			}
		}
	}
	
	public void printTree()	{
		int spacer = 1;
		boolean last = false;
		String style = "├── ";
		//print the resource list (files/dirs) (Tree Format)
		Iterator<FSElement> recursiveList = children.iterator();
		System.out.println(".");
		while(recursiveList.hasNext())	{
			FSElement resource = recursiveList.next();
			// if folder
			if (!recursiveList.hasNext()) {
				last = true;
				style = "└── ";
			}
			if (!resource.isFile())	{
				System.out.println(style + resource.getName() + " ("+ resource.getType()+ " " + resource.getSize() + " bytes)");
				((Directory) resource).rescursiveIndex(spacer, last);
			}
			else {
				System.out.print(style + resource.getName() + " ("+ resource.getType() + " " + resource.getSize() + " bytes");
				if(resource.getType().equals("link")) {
					System.out.print(" --> " + resource.getTagetPath());
				}
				System.out.print(")\n");
			}
		}
	}
	
	public String getType()	{
		return "directory";
	}

	public int getDiskUtil() {
		// TODO Auto-generated method stub
		return this.getSize();
	}
}
