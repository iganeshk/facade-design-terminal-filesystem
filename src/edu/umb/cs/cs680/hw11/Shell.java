package edu.umb.cs.cs680.hw11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Shell {

	Scanner input;
	
	public Shell()	{
		
	}

	private CommandHistory history = new CommandHistory();
	
	public CommandHistory getCMDHistory()	{
		return this.history;
	}
	
	public void powerUp(Shell shell, FileSystem fs)	{
		System.out.print("❯ ");
		System.out.print("");
		input = new Scanner(System.in);
		try 	{
			executeCMD(input, shell, fs);
		} finally {
			input.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void executeCMD(Scanner input, Shell shell, FileSystem fs) {
		int whiteSpacer = 0;
		String tehCmd = input.nextLine();
		String[] cmd = stripCommands(tehCmd);
		if (cmd == null) shell.powerUp(shell, fs);
		if(cmd[0].isEmpty()) ++whiteSpacer;
		if(COMMANDS.isInEnum(cmd[0].toUpperCase(Locale.ENGLISH))) {
			while(true) {
	
				switch(COMMANDS.valueOf(cmd[whiteSpacer].toUpperCase(Locale.ENGLISH)))	{
				case ECHO:{
					for(String echox: cmd)
						if(!echox.equals("echo")) System.out.print(echox + " ");
					System.out.println("\n");
					history.push(tehCmd);
					break;
				}
				case PWD:{
					if(cmd.length == 1) {
						Pwd pwd = new Pwd(fs);
						pwd.execute();
						history.push(tehCmd); history.saveCMD(pwd);
					}else System.out.println("shell: command: pwd: invalid argument");
					break;
				}
				case LS:	{
					if(cmd.length == 1) {
						Ls ls = new Ls(fs);
						ls.execute();
						history.push(tehCmd); history.saveCMD(ls);
					} else System.out.println("shell: command: ls: invalid argument");
					break;
				}
				case CD:{
					Cd cd;
					if (cmd.length == 1)	{
						cd = new Cd(fs, "");
						cd.execute();
						history.push(tehCmd); history.saveCMD(cd);
					}
					else if (cmd.length == 2)	{
						cd = new Cd(fs, cmd[1]);
//						cd.fsResources = parse(cmd[1], "/");
						cd.execute();
						history.push(tehCmd); history.saveCMD(cd);
					} else System.out.println("shell: command: cd can only accept upto 1 argument, you've passed: "+ (cmd.length-1));
					break;
				}
				case DIR:{
					Dir dire = null;
					if(cmd.length <= 2)	{
						if(cmd.length == 1) 	dire = new Dir(fs, " ");
						if(cmd.length == 2) dire = new Dir(fs, cmd[1]);
						dire.execute();
						history.push(tehCmd); history.saveCMD(dire);
						break;
					} else System.out.println("shell: command: dir can only accept upto 2 argument, you've passed: "+ (cmd.length-1));
					break;
				}
				case SORT:{
					Comparator compare=new AlphabeticalComparator();
					if (cmd.length >= 1 || cmd.length <= 2)		{
						switch(cmd[1])	{
							case "-r": //reverse
							{
								compare = new ReverseAlphabeticalComparator();
								break;
							}
							case "-t": //timestamp
							{
								compare = new TimestampComparator();
								break;
							}
							default:
							{
								compare = new AlphabeticalComparator();
								break;
							}
						}					
					} else System.out.println("shell: command: sort can only accept 2 arguments, you've passed: "+ (cmd.length-1));
					Sort sort = new Sort(fs,compare);
					sort.execute();
					history.push(tehCmd); history.saveCMD(sort);
					break;
				}
				case MV:{
					Mv mv;
					//mv must have 2 args: source dest 
					if (cmd.length == 3)	{
						mv = new Mv(fs, cmd[1]);
						//mv.sourcePath = parse(cmd[1], "/"); filerenaming?
						mv.dest = cmd[2];
						mv.execute();
						history.push(tehCmd);history.saveCMD(mv);
					} else System.out.println("shell: command: mv can only accept 2 arguments, you've passed: "+ (cmd.length-1));
					break;
				}
				case CP:{
					Cp cp;
					//mv must have 2 args: source dest 
					if (cmd.length == 3)	{
						cp = new Cp(fs, cmd[1]);
						//mv.sourcePath = parse(cmd[1], "/"); filerenaming?
						cp.destPath = parse(cmd[2], "/");
						cp.dest = cmd[2];
						cp.execute();
						history.push(tehCmd);history.saveCMD(cp);
					} else System.out.println("shell: command: mv can only accept 2 arguments, you've passed: "+ (cmd.length-1));
					break;
				}
				case MKDIR:{
					if (cmd.length == 2)	{
						Mkdir mk = new Mkdir(fs, cmd[1]);
						mk.execute();
						history.push(tehCmd); history.saveCMD(mk);
					}
					else System.out.println("shell: command: rmdir can only accept 1 argument, you've passed: "+ (cmd.length-1));
					break;
				}
				case RMDIR:{
					if (cmd.length == 2) {
						Rmdir rmdir = new Rmdir(fs, cmd[1]);
						rmdir.execute();
						history.push(tehCmd); history.saveCMD(rmdir);
					}
					else System.out.println("shell: command: rmdir can only accept 1 argument, you've passed: "+ (cmd.length-1));
					break;
				}
				case LN:{
					Ln ln;
					//ln has 2 args: ln targetPath sourcePath
					if (cmd.length == 3) {
						ln = new Ln(fs, cmd[2]);
						ln.target = cmd[1];
						ln.execute();
						history.push(tehCmd); history.saveCMD(ln);
					}
					else System.out.println("shell: command: ln can only accept 2 arguments, you've passed: "+ (cmd.length-1));
					break;
				}
				case HISTORY:{
					if(cmd.length == 1)	{
						History hist = new History(getCMDHistory());
						hist.execute();
						history.push(tehCmd); history.saveCMD(hist);
					}
					else System.out.println("shell: command: history: invalid argument"); 
					break;
				}
				case REDO:	{
					Redo re = new Redo(getCMDHistory());
					re.execute();
					break;
				}
				case CHOWN:	{
					if(cmd.length == 3) {
						Chown change = new Chown(fs, cmd[1], cmd[2]);
						change.execute();
						history.push(tehCmd); history.saveCMD(change);
					}
					else System.out.println("shell: command: chown: requires 2 arguments, you've passed "+ (cmd.length-1));
					break;
				}
				case VIRUSCHECK:{
					if(cmd.length == 1) {
						VirusCheck viruschk = new VirusCheck(fs);
						viruschk.execute();
						history.push(tehCmd); history.saveCMD(viruschk);
					}
					break;
				}
				case SEARCH:	{
					if(cmd.length == 2) {
						Search search = new Search(fs, cmd[1]);
						search.execute();
						history.push(tehCmd); history.saveCMD(search);
					}
					break;
				}
				case HELP:{
					shell.printHelp();
					break;
				}
				case EXIT:{
					System.out.println("shutting terminal down...");
					System.exit(0);
					return;
				}
				default:
					break;					
				}
				shell.powerUp(shell, fs);
			}
		}
		else {
			System.out.println("shell: command not found:  " + cmd[0]);
			shell.powerUp(shell, fs);
		}
	}
	
    private String[] stripCommands(String input) {
        String terminalText = input;
        int lastPromptIndex = terminalText.lastIndexOf('❯') + 1;
        if (lastPromptIndex < 0 || lastPromptIndex >= terminalText.length() || terminalText.trim().length() <= 0) 
            return null;

        else		{
        		String[] inputs = terminalText.split("\\s+");
        		return inputs;
        }

    }
    
	public ArrayList<String> parse(String input,String symbol) {
		ArrayList<String> res = new ArrayList<>();
		if (input.startsWith(symbol)) {
			input = input.substring(1);
		}
		res.addAll(Arrays.asList(input.split(symbol)));
		return res;
	}
	
	public void printHelp()	{
		System.out.println("JAVA based bash for emulated file system v0.01-alpha, x86_1024");
		System.out.println("These shell commands are defined internally.");
		System.out.println("\nAvailable Commands: ------------------------------------------");
		System.out.println("- cd <dir name>  change the current directory to the specified directory ");
		System.out.println("- 		(cd .. -> go back to previous directory)");
		System.out.println("- 		(cd / -> go root directory)");
		System.out.println("- cp <file/dirname> <dir name>  copy current file/directory to the specified directory");
		System.out.println("- chown <dir/file name> new_owner - changes the owner of a file/directory");
		System.out.println("- 		(chown * new_owner -> recursively change all files to new owner)");
		System.out.println("- dir <dir/file name> print the specified directory's/file's information");
		System.out.println("- help -> Print this information page again");
		System.out.println("- history  lists the sequence of previously executed commands");
		System.out.println("- ln <target path> <link name> Creates a link in the current directory");
		System.out.println("- ls  lists file, directory and link in the current directory");
		System.out.println("- mkdir <dir name> Creates the specified directory in the current directory"); 
		System.out.println("- rmdir <dir name> Deletes the specified directory from the current direcotry"); 
		System.out.println("- pwd  Prints current working directory"); 
		System.out.println("- redo  Perform the most recently executed command");
		System.out.println("- search file/director -> Searched the current directory and returns the count");
		System.out.println("- sort <type> Sort directories and files in the current directory");
		System.out.println("        <-a> sort by Alphabetical (default)");
		System.out.println("        <-r> sort by Reversed Alphabetical");
		System.out.println("        <-t> sort by Timestamp");
		System.out.println("- viruscheck Perform a viruscheck in the current directory");
		System.out.println("- exit -> terminates the session");
		System.out.println("--------------------------------------------------------------");
	}
    
    public static void main(String[] args) {
		Date d1 = new Date();
		FileSystem fs = FileSystem.getFileSystem();
		fs.setRootDir(new Directory(null, "/", "root", d1, d1));
		fs.setWorkingDir(fs.getRootDir());

		// folders - mkdir emulation
		// mkdir(parent name, name, owner, createdate, modDate, destination parent)
		Directory root = fs.mkdir(fs.getRootDir(), "root", "root", d1,d1);
		Directory system = fs.mkdir(root, "system", "root", d1,d1);
		Directory home = fs.mkdir(root, "home", "root", d1,d1);
		Directory pictures = fs.mkdir(home, "pictures", "root", d1,d1);

		// files touch command emulation
		// touch(destination parent, name, owner, createDate, modDate, size)
		fs.touch(system, "a", "user", d1,d1, 130);
		fs.touch(system, "b", "root", d1, d1, 2840);
		fs.touch(system, "c", "gvelu", d1,d1, 4900);
		fs.touch(home, "d", "gvelu", d1,d1, 4930);
		File e = fs.touch(pictures, "e", "gvelu", d1,d1, 3030);
		fs.touch(pictures, "f", "gvelu", d1,d1, 2230);
		
		// links - ln command emulation
		// ln(destination parent, name, owner, createDate, modDate, target parent)
		fs.ln(home, "x", "root", d1, d1, system);
		fs.ln(pictures, "y", "root", d1, d1, e);
		
		Shell shell = new Shell();
		System.out.println("Powering up terminal");
		shell.printHelp();
		shell.powerUp(shell, fs);
		
	}
}
