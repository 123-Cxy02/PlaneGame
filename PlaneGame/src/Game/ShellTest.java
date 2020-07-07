package Game;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;

public class ShellTest {
	
	static Image shellimg=GameUtil.getImage("images/1.gif");
	Shell shell;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShell() {
		shell=new Shell();
		assertEquals(shell.imgImage,null);
		assertEquals(shell.imgWidth,10);
		assertEquals(shell.imgHeight,3);
	}

}
