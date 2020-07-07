package Game;
/*
*飞机类
*/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	boolean left,right,up,down;
	int speed=3;
	
	boolean live=true;
	
	public void drawSeft(Graphics g) {
//		System.out.println("Plane:"+imgX);
		if (live) {
			g.drawImage(imgImage, (int)imgX, (int)imgY, null);

			if (left) {
				imgX-=speed;
			}
			if (right) {
				imgX+=speed;
			}
			if (up) {
				imgY-=speed;
			}
			if (down) {
				imgY+=speed;
			}
		}else {
			System.out.println("飞机爆炸");
		}

		
	}
	
	//按下某个键增加相应的方向
	public void addDirection(KeyEvent e) {
		System.out.println("按键信息："+ e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;

		default:
			break;
		}
	}
	public void minusDirection(KeyEvent e) {
		System.out.println("按键信息："+ e.getKeyCode());
		switch (e.getKeyCode()) {
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
	}
	public Plane(Image imgImage, double imgX, double imgY) {
		this.imgImage=imgImage;
		this.imgX = imgX;
		this.imgY = imgY;
		
		this.imgWidth=imgImage.getWidth(null);
		this.imgHeight=imgImage.getHeight(null);
	}

}
