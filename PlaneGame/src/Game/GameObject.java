package Game;

/*
 * 游戏物体的父类
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	Image imgImage;
	double imgX,imgY;
	int speed;
	int imgWidth,imgHeight;
	
	public GameObject() {
		
	}
	public GameObject(Image imgImage, double imgX, double imgY, int speed, int imgWidth, int imgHeight) {
		super();
		this.imgImage = imgImage;
		this.imgX = imgX;
		this.imgY = imgY;
		this.speed = speed;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}


	public void drawSeft(Graphics g) {
//		System.out.println("gameobject:"+imgX);
		g.drawImage(imgImage, (int)imgX, (int)imgY, null);
	}

	//返回物体所在矩形，便于后续的碰撞检测
	public Rectangle getRect() {
		return new Rectangle((int)imgX, (int)imgY, imgWidth, imgHeight);
	}
	
}
