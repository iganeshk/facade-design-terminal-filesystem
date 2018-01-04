package edu.umb.cs.cs680.hw11;

import java.util.Comparator;

public class TimestampComparator implements Comparator<FSElement> {

	@Override
	public int compare(FSElement o1, FSElement o2) {
		return o1.DateModified().compareTo(o2.lastModified);
	}

}
