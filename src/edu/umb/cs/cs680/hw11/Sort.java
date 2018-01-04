package edu.umb.cs.cs680.hw11;

import java.util.Collections;
import java.util.Comparator;

public class Sort implements Command {

	private Comparator<FSElement> comparator;
	private FileSystem fs;
	
	public Sort(FileSystem fs,Comparator<FSElement> comparator) {
		this.fs=fs;
		this.comparator = comparator;
	}

	@Override
	public void execute() {
		Collections.sort(fs.getChildren(), this.comparator);
		for(FSElement fe : fs.getChildren())
			System.out.println(fe.getName());
	}
}
