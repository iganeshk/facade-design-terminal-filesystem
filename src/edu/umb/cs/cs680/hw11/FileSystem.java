package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Date;

public class FileSystem	{
	
	private Directory workingDir;
	private static FileSystem instance = null;
	static Date d1 = new Date();

	// Linux respresentation of root directory: /
	public Directory rootDir = new Directory(null, "/", "root", d1, d1);
	
	public FileSystem()	{
	}
	
	public static FileSystem getFileSystem()	{
		if (instance == null) 	{
			instance = new FileSystem();
			return instance;
		}
		return instance;
	}
	
	public Directory getRootDir()		{
		return rootDir;		
	}
	
	public void setRootDir(Directory dir)	{
		this.rootDir = dir;
	}
	
	public Directory getWorkingDir()		{
		return workingDir;
	}
	
	public void setWorkingDir(Directory dir)		{
		this.workingDir = dir;
	}
	
	public void showAllElements()		{
		getRootDir().printTree();
	}
	
	public void index(Directory parent, FSElement child)	{
		parent.appendChild(child);
	}
	
	public int getTotalSize(FSElement resource)	{
		return resource.getSize();
	}
	
	public ArrayList<FSElement> getChildren()	{
		return this.workingDir.getChildren();
	}
	
	public void deleteChild(String fileName)	{
		FSElement res = null;
		// search and delete
		for (FSElement element : this.workingDir.getChildren()) {
			if (element.getName().equals(fileName)) {
				res = element;
				break;
			}
		}
		if (res != null) {
			this.workingDir.getChildren().remove(res);
		}	
	}
	
	public ArrayList<String> getFullPath(Directory directory){

		ArrayList<String> list = new ArrayList<> ();
		Directory parent = directory.getParent();
		while (parent != null) {
			String name = parent.getName();
			list.add(name);			
			parent = parent.getParent();
		}
		ArrayList<String> parsed = new ArrayList<> ();
		for (int x = list.size()-1; x > -1; x--){
			parsed.add(list.get(x));
		}
		return parsed;
	}
		
	// commands to create dirs/links/file
	
	public Directory mkdir(Directory parent, String name, String owner, Date created, Date lastModified)	{
		Directory createDir = new Directory(parent, name, owner, created, lastModified);
		parent.appendChild(createDir);
		return createDir;
	}
	
	public File touch(Directory parent, String name, String owner, Date created, Date lastModified, int size)	{
		File createFile = new File(parent, name, owner, created, lastModified, size);
		parent.appendChild(createFile);
		return createFile;
		
	}
	
	public Link ln(Directory parent, String name, String owner, Date created, Date lastModified, FSElement target) {
		Link createLink = new Link(parent, name, owner, created, lastModified, target);
		parent.appendChild(createLink);
		return createLink;
	}	
}
