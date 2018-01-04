package edu.umb.cs.cs680.hw11;

public class CountingVisitor implements FSVisitor		{
	
	int dirNum = 0;
	int linkNum = 0;
	int fileNum = 0;
			

	@Override
	public void visit(Directory dir) {
		// TODO Auto-generated method stub
		++dirNum;
		
	}

	@Override
	public void visit(Link link) {
		// TODO Auto-generated method stub
		++linkNum;
		
	}

	@Override
	public void visit(File file) {
		// TODO Auto-generated method stub
		++fileNum;
	}

	public int getDirNum()	{
		return dirNum;
	}
	
	public int getLinkNum()	{
		return linkNum;
	}
	
	public int getFileNum()	{
		return fileNum;
	}
}
