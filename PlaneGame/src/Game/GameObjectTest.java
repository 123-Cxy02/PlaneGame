package Game;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;

public class GameObjectTest {
	
	private static GameObject gb;
	static Image gbimg=GameUtil.getImage("images/bg.jpg");
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGameObject() {
		gb=new GameObject();
		assertEquals(gb.imgImage,null);
	}

	@Test
	public void testGameObjectImageDoubleDoubleIntIntInt() {
		gb=new GameObject(gbimg,200,200,3,200,200);
		assertEquals(gb.imgImage,gbimg);
		assertEquals(gb.imgWidth,200);
		assertEquals(gb.speed,3);
	}

	@Test
	public void testGetRect() {
		gb=new GameObject(gbimg,200,200,3,200,200);
		Rectangle rec=new Rectangle(200,200,200,200);
		assertEquals(gb.getRect(),rec);
	}

}
