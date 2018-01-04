package edu.umb.cs.cs680.hw11;

public interface FSVisitor {

	public void visit(Directory dir);
	public void visit(Link link);
	public void visit(File file);

}