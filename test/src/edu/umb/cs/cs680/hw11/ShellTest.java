package edu.umb.cs.cs680.hw11;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.Before;

public class ShellTest {
	
	private FileSystem fs;
	private Shell shell;
	private String path;

	@Before
	public void initialize() {
		Date d1 = new Date();
		fs = FileSystem.getFileSystem();
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
		
		this.shell = new Shell();
	}
	
	@Test
	public void testMKDIR() {
		int actual = fs.getChildren().size();
		Mkdir md = new Mkdir(fs, "test");
		md.execute();
		assertThat(actual,is(fs.getChildren().size()-1));
	}
	
	@Test
	public void testCD()	{
		path = "/root/home/pictures";
		Cd cd = new Cd(fs, path);
		cd.execute();
		String out = String.join("/", fs.getFullPath(fs.getWorkingDir()));
		out = out + "/" + fs.getWorkingDir().getName();
		ArrayList<String > out1 = shell.parse(out, "/");
		String finalx = String.join("/", out1);
		
		assertThat(path,is(finalx));
	}
	
	@Test
	public void testRMDir()	{
		path = "/root/home";
		Cd cd = new Cd(fs, path);
		cd.execute();
		int actual = fs.getChildren().size();
		Rmdir rmd = new Rmdir(fs, "pictures");
		rmd.execute();
		assertThat(actual,is(fs.getChildren().size()+1));
	}
	@Test
	public void testChown()	{
		Chown chown = new Chown(fs, "root", "test");
		chown.execute();
		path = "/root";
		Cd cd = new Cd(fs, path);
		cd.execute();
		String actual = fs.getWorkingDir().getOwner();
		assertThat(actual,is("test"));
	}
	
	@Test
	public void testLink()	{
		int c = fs.getChildren().size();
		Ln ln = new Ln(fs,"linkTest");
		ln.target = "/root";
		ln.targetPath = shell.parse(ln.target,"/");
		ln.execute();
		assertThat(fs.getChildren().size(), is(c+1));
	}
	
	@Test
	public void testCP()	{
		path = "/root/system";
		Cd cd = new Cd(fs, path);
		cd.execute();
		int actual = fs.getWorkingDir().getParent().getChildren().size();
		Cp cp = new Cp(fs,"a");
		cp.dest = "..";
		cp.execute();
		assertThat(actual, is(fs.getWorkingDir().getParent().getChildren().size()-1));
	}
	
	@Test
	public void testLN()	{
		Cd cd = new Cd(fs, "/root");
		cd.execute();
		int actual = fs.getWorkingDir().getChildren().size();
		Ln ln = new Ln(fs,"shortcut");
		ln.target = "/root/home/pictures";
		ln.execute();

		assertThat(actual, is(fs.getWorkingDir().getChildren().size()-1));
	}
	
	@Test
	public void testDir()	{
		Cd cd = new Cd(fs, "/root/home");
		cd.execute();
		Dir dirs = new Dir(fs, " ");
		dirs.execute();
		System.out.println("----------------------------------------------");
	}
	
	@Test
	public void testMv()	{
		Cd cd = new Cd(fs, "/root/system");
		cd.execute();
//		Mv mv = new Mv(fs,);
	}
	
	@Test 
	public void testZshowall()	{
		fs.showAllElements();
	}

}
