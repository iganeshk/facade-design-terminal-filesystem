package edu.umb.cs.cs680.hw11;

import java.util.Arrays;

public enum COMMANDS {
	
	ECHO,CD,PWD,LS,MV,CP,MKDIR,RMDIR,LN,SORT,CHOWN,QUIT,REDO,HISTORY,DIR,SEARCH,VIRUSCHECK,EXIT,HELP;
	
	public static boolean isInEnum(String value) {
	     return Arrays.stream(COMMANDS.values()).anyMatch(e -> e.name().equals(value));
	}

}
