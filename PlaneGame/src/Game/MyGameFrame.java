package Game;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


/**
 * 
* @project_name：MyGame   
* @type_name：MyGameFrame  
* @version：2020年5月14日 下午 
* @author：王广 程小宇 
* @class_description：飞机游戏主窗口
*
 */

public class MyGameFrame extends Frame{
	
	//加载图像
    Image planeimg=GameUtil.getImage("images/plane.png");
    Image bg=GameUtil.getImage("images/bg.jpg");
    
    Plane plane=new Plane(planeimg,400,400);
//	int x=250,y=250;
    Shell[] shells=new Shell[30];
    Explode bao;
    
    Date startTime=new Date();
    Date endTime;
    int period;//游戏持续时间

	@Override
	public void paint(Graphics g) {//paint自动被调用，g相当于一只画笔

		//绘制图像
		g.drawImage(bg, 0, 0, null);
		plane.drawSeft(g);//画飞机
		
		for (int i = 0; i < shells.length; i++) {
			shells[i].draw(g);//画炮弹
			
			//碰撞检测
			boolean peng=shells[i].getRect().intersects(plane.getRect());
			if (peng) {
				System.out.println("相撞了");
				plane.live=false;				
				if(bao==null) {//只检测一次碰撞
					bao=new Explode(plane.imgX, plane.imgY);
					bao.draw(g);
					
					endTime=new Date();
					period=(int)(endTime.getTime()-startTime.getTime())/1000;
				}				


			}
			if (!plane.live) {
				Color color=g.getColor();
				g.setColor(Color.white);
				g.drawString("游戏持续时间："+period+"s", (int)plane.imgX, (int)plane.imgY);
				g.setColor(color);
			}

		}
		
//		g.drawImage(planeimg, x, y, null);
//		x++;

	}
	
	//JFrame还是有点闪烁问题，这里使用Frame利用双缓冲的解决闪烁问题
	private Image offScreenImage=null;
	
	public void update(Graphics g) {//update自动调用
		if (offScreenImage==null) {
			offScreenImage=this.createImage(Constant.GAME_WEIGHT,Constant.GAME_HEIGHT);
		}
		Graphics gOffGraphics=offScreenImage.getGraphics();
		paint(gOffGraphics);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	class PaintThread extends Thread{//内部类，帮助我们重复画窗口
		@Override
		public void run() {
			while(true) {
//				System.out.println("窗口重画...");
				repaint();//重画
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//定义键盘监听内部类
	class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
	}
    /*        初始化窗口                  */
	public void launchFrame() {//画窗口
		this.setTitle("@@飞机游戏小练习@@");
		this.setLocation(100,100);
		this.setSize(Constant.GAME_WEIGHT,Constant.GAME_HEIGHT);
		this.setVisible(true);//默认不可见
		
		this.addWindowListener(new WindowAdapter() {//用于接收窗口事件的侦听器接口。
			@Override
			public void windowClosing(WindowEvent e) {//方法重写，实现“关闭窗口时程序自动关闭”的功能
				System.exit(0);
			}
		});
		
		new PaintThread().start();//启动重画窗口
		addKeyListener(new KeyMonitor());//给窗口增加键盘监听
		
		for (int i = 0; i < shells.length; i++) {//初始化50个炮弹
			shells[i]=new Shell();
		}
	}
	

	
	
	public static void main(String[] args) {
		MyGameFrame start=new MyGameFrame();
		start.launchFrame();
		
	}
}


