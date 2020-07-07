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
* @project_name��MyGame   
* @type_name��MyGameFrame  
* @version��2020��5��14�� ���� 
* @author������ ��С�� 
* @class_description���ɻ���Ϸ������
*
 */

public class MyGameFrame extends Frame{
	
	//����ͼ��
    Image planeimg=GameUtil.getImage("images/plane.png");
    Image bg=GameUtil.getImage("images/bg.jpg");
    
    Plane plane=new Plane(planeimg,400,400);
//	int x=250,y=250;
    Shell[] shells=new Shell[30];
    Explode bao;
    
    Date startTime=new Date();
    Date endTime;
    int period;//��Ϸ����ʱ��

	@Override
	public void paint(Graphics g) {//paint�Զ������ã�g�൱��һֻ����

		//����ͼ��
		g.drawImage(bg, 0, 0, null);
		plane.drawSeft(g);//���ɻ�
		
		for (int i = 0; i < shells.length; i++) {
			shells[i].draw(g);//���ڵ�
			
			//��ײ���
			boolean peng=shells[i].getRect().intersects(plane.getRect());
			if (peng) {
				System.out.println("��ײ��");
				plane.live=false;				
				if(bao==null) {//ֻ���һ����ײ
					bao=new Explode(plane.imgX, plane.imgY);
					bao.draw(g);
					
					endTime=new Date();
					period=(int)(endTime.getTime()-startTime.getTime())/1000;
				}				


			}
			if (!plane.live) {
				Color color=g.getColor();
				g.setColor(Color.white);
				g.drawString("��Ϸ����ʱ�䣺"+period+"s", (int)plane.imgX, (int)plane.imgY);
				g.setColor(color);
			}

		}
		
//		g.drawImage(planeimg, x, y, null);
//		x++;

	}
	
	//JFrame�����е���˸���⣬����ʹ��Frame����˫����Ľ����˸����
	private Image offScreenImage=null;
	
	public void update(Graphics g) {//update�Զ�����
		if (offScreenImage==null) {
			offScreenImage=this.createImage(Constant.GAME_WEIGHT,Constant.GAME_HEIGHT);
		}
		Graphics gOffGraphics=offScreenImage.getGraphics();
		paint(gOffGraphics);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	class PaintThread extends Thread{//�ڲ��࣬���������ظ�������
		@Override
		public void run() {
			while(true) {
//				System.out.println("�����ػ�...");
				repaint();//�ػ�
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//������̼����ڲ���
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
    /*        ��ʼ������                  */
	public void launchFrame() {//������
		this.setTitle("@@�ɻ���ϷС��ϰ@@");
		this.setLocation(100,100);
		this.setSize(Constant.GAME_WEIGHT,Constant.GAME_HEIGHT);
		this.setVisible(true);//Ĭ�ϲ��ɼ�
		
		this.addWindowListener(new WindowAdapter() {//���ڽ��մ����¼����������ӿڡ�
			@Override
			public void windowClosing(WindowEvent e) {//������д��ʵ�֡��رմ���ʱ�����Զ��رա��Ĺ���
				System.exit(0);
			}
		});
		
		new PaintThread().start();//�����ػ�����
		addKeyListener(new KeyMonitor());//���������Ӽ��̼���
		
		for (int i = 0; i < shells.length; i++) {//��ʼ��50���ڵ�
			shells[i]=new Shell();
		}
	}
	

	
	
	public static void main(String[] args) {
		MyGameFrame start=new MyGameFrame();
		start.launchFrame();
		
	}
}


