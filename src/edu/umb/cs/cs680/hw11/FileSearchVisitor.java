package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;

public class FileSearchVisitor implements FSVisitor	{
	
	private String extension;
	ArrayList<File> foundFiles = new ArrayList<File>();
	
	public FileSearchVisitor(String key)	{
		extension = key;
	}
	
	@Override
	public void visit(Directory dir)	{
		// TODO Auto-generated method stub
		return;
	}
	
	@Override
	public void visit(Link link)	{
		// TODO Auto-generated method stub
		return;
	}	
	
	@Override
	public void visit(File file)	{
		// TODO Auto-generated method stub
		if(file.getName().contains(extension)) foundFiles.add(file);
	}
	
	public ArrayList<File> getFoundFiles()	{
		return foundFiles;
	}
}
