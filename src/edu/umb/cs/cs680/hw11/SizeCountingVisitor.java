package edu.umb.cs.cs680.hw11;

public class SizeCountingVisitor implements FSVisitor {
	
	int totalSize = 0;
	
	@Override
	public void visit(Directory dir) {
		// TODO Auto-generated method stub
		totalSize += dir.getDiskUtil();
	}
	
	@Override
	public void visit(Link link) {
		// TODO Auto-generated method stub
		totalSize += link.getDiskUtil();
		
	}

	@Override
	public void visit(File file) {
		// TODO Auto-generated method stub
		totalSize += file.getDiskUtil();
		
	}
	
	public int getTotalSize() {
		return totalSize;
	}

}
