package Game;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

public class PlaneTest {
	
	static Image planeimg=GameUtil.getImage("images/plane.png");
	private static Plane plane;
	boolean left,right,up,down;
	int speed=3;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMinusDirection() {
		switch(37) {
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;	
		}
		
		assertEquals(left,false);
	}

	@Test
	public void testPlane() {
		plane=new Plane(planeimg,50,50);
		assertEquals(plane.imgImage,planeimg);
	}

}
