package Game;

import java.awt.Color;
import java.awt.Graphics;

/*
 * 炮弹类
 */
public class Shell extends GameObject{
	double degree;
	
	public Shell() {
		imgX=200;
		imgY=200;
		imgWidth=10;
		imgHeight=3;
		speed=5;
		
		degree=2*Math.PI*Math.random();
	}
	
	public void draw(Graphics g) {
		Color color=g.getColor();
		g.setColor(Color.YELLOW);
		
		g.fillOval((int)imgX, (int)imgY, imgWidth, imgHeight);
		imgX+=speed*Math.cos(degree);
		imgY+=speed*Math.sin(degree);
		
		if(imgY>Constant.GAME_HEIGHT+25-1.5||imgY<25+1.5) {//标题栏高度40
			degree=-degree;
		}

		if(imgX>Constant.GAME_WEIGHT-10+5||imgX<5) {
			degree=Math.PI-degree;
		}
		
		g.setColor(color);
	}

}
