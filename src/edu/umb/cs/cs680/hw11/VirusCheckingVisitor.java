package edu.umb.cs.cs680.hw11;

public class VirusCheckingVisitor implements FSVisitor	{
	
	int dirQuarantinedNum = 0;
	int linkQuarantinedNum = 0;
	int fileQuarantinedNum = 0;

	@Override
	public void visit(Directory dir) {
		// TODO Auto-generated method stub
		++dirQuarantinedNum;
		
	}

	@Override
	public void visit(Link link) {
		// TODO Auto-generated method stub
		++linkQuarantinedNum;
		
	}

	@Override
	public void visit(File file) {
		// TODO Auto-generated method stub
		++fileQuarantinedNum;
		
	}

}
