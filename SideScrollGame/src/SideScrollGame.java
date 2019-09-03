//Shreyas Vaderiyattil
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
public class SideScrollGame extends JPanel implements KeyListener,Runnable
{
	private float angle;
	private int x;
	private int y;
	private int bx = 960;
	private int bx2 = 960;
	private int bx3 = 960;
	private JFrame frame;
	Thread t;
	Thread l;
	private boolean gameOn;
	BufferedImage guy;
	BufferedImage guy2;
	BufferedImage enem;
	BufferedImage img;
	BufferedImage zstillright;
	BufferedImage zstillleft;
	BufferedImage jumpright;
	BufferedImage jumpleft;
	BufferedImage[] guys=new BufferedImage[11];
	BufferedImage[] guys2=new BufferedImage[11];
	BufferedImage[] back = new BufferedImage[3];
	BufferedImage[] enemy = new BufferedImage[8];
	boolean right = true;
	boolean move = false;
	boolean restart=false;
	boolean jump = false;
	int imgCount=3;
	int enemCount=3;
	int jnum;
	int ex;
	int ey;
	Polygon poly;
	Polygon poly2;
	Rectangle samus;
	Rectangle ghost;
	int gamecount = 0;

	public SideScrollGame()
	{
		frame=new JFrame();
		x=0;
		y=575;
		gameOn=true;
		samus = new Rectangle(x,y,5,5);
		ghost = new Rectangle(ex,ey,5,5);
		ex = 980;
		ey = 575;
		
		
		try {
			img = ImageIO.read(new File("far-buildings.png"));
			back[0] = img;
			img = ImageIO.read(new File("back-buildings.png"));
			back[1] = img;
			img = ImageIO.read(new File("foreground.png"));
			back[2] = img;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			guy = ImageIO.read(new File("zero.gif"));
			
			for(int x=0;x<11;x++)
				guys[x]=guy.getSubimage(x*30,70,23,40);
			
		
			
		}
		catch (IOException e) {
		}
		try {
			guy2 = ImageIO.read(new File("zero.gif"));
		
			for(int x=0;x<11;x++)
				guys2[x]=guy2.getSubimage(x*31,115,23,40);
			
		
			
		}
		catch (IOException e) {
		}
		try {
			enem = ImageIO.read(new File("enemy.gif"));
		
			for(int x=0;x<8;x++)
				enemy[x]=enem.getSubimage(x*50,293,45,45);
			
		
			
		}
		catch (IOException e) {
		}
		
		try {
			guy = ImageIO.read(new File("zero.gif"));
			
			zstillright = guy.getSubimage(60, 67, 23, 40);
			zstillleft = guy.getSubimage(365, 115, 23, 40);
			jumpright = guy.getSubimage(120, 322, 23, 40);
			jumpleft = guy.getSubimage(322, 322, 23, 40);
			
		}
		catch (IOException e) {
		}
		int[] x={1,2,3};
		int[] y={4,5,6};

		poly=new Polygon(x,y, x.length);

		poly2=new Polygon();
		poly2.addPoint(1,4);
		poly2.addPoint(2,5);
		poly2.addPoint(3,6);
		
		

		frame.addKeyListener(this);
		frame.add(this);
		frame.setSize(1000,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		l = new Thread(this);
		t.start();
		l.start();
	}

	public void run()
	{
		while(true)
		{
			if(gameOn)
			{
				samus = new Rectangle(x,y,23,40);
				ghost = new Rectangle(ex,ey,40,40);
				//Math happens here!
				
				
				
				
				
				if(jump ==true) {
					for(int i=0;i<12;i++) {
						jnum = i;
						try
						{
							t.sleep(100);
						}catch(InterruptedException e)
						{
						}
						
						repaint();
						if(right&&(x+(x+20)<400))
							x+=5;
						else if(right&&(x+(x+20)>400)) {
							x+=1;
						}
						else if(!right&&(x-(x-20)>150))
							x-=5;
						else if(!right&&(x-(x-20)<150)) {
							x-=1;
						}
					}
					
					jump = false;
					jnum = 0;
				}
				
				
				if(ex==-600) {
					ex=980;
					ey = 575;
				}
			

				repaint();
			}
			
			
			if(restart)
			{
				restart=false;
				gameOn=true;
			}
			
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting happens here!

		g2d.setColor(Color.BLUE);
		//g2d.fillRect(0,0,990,1000);
		g2d.setColor(Color.BLACK);
		g2d.drawImage(back[0],(int) ((bx)-960),0,null);
		g2d.drawImage(back[0],(int) ((bx)+960),0,null);
		g2d.drawImage(back[1],(int) ((bx2)-960),0,null);
		g2d.drawImage(back[1],(int) ((bx2)+960),0,null);
		g2d.drawImage(back[2],(int) ((bx3)-960),0,null);
		g2d.drawImage(back[2],(int) ((bx3)+960),0,null);
		
		if(bx==-1920) {
			bx+=1920;
			
		}
		if(bx2==-1920) {
			bx2+=1920;
		}
		if(bx3==-1920) {
			bx3+=1920;
			gamecount++;
		}
		try
		{
			t.sleep(60);
		}catch(InterruptedException e)
		{
		}
		samus = new Rectangle(x,y,23,40);
		ghost = new Rectangle(ex,ey,40,40);
		if(samus.intersects(ghost)&&ex>455&&y==575) {
			gameOn = false;
		}
		enemCount++;
		ex-=20;
		if(enemCount>7) {
			enemCount = 3;
		}
		if(ex>455)
			g2d.drawImage(enemy[enemCount].getScaledInstance(100, 100, Image.SCALE_DEFAULT),ex,ey,null);
		
		if(right == true&&move==true&&jump==false) {
			g2d.drawImage(guys[imgCount].getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
			
		}else if(right == false&&move==true&&jump==false) {
			g2d.drawImage(guys2[imgCount].getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
			
		}else if(right ==true&&jump==false) {
			g2d.drawImage(zstillright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
		}
		else if(right ==false&&jump==false) {
			g2d.drawImage(zstillleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
		}
		
		if(jump==true&&right==true&&jnum==0) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
			
		}
		if(jump==true&&right==false&&jnum==0) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
		}
		if(jump==true&&right==true&&jnum==1) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
			
		}
		if(jump==true&&right==false&&jnum==1) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
		}
		if(jump==true&&right==true&&jnum==2) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-300,null);
			
		}
		if(jump==true&&right==false&&jnum==2) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-300,null);
		}
		if(jump==true&&right==true&&jnum==3) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-301,null);
			
		}
		if(jump==true&&right==false&&jnum==3) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-301,null);
		}
		if(jump==true&&right==true&&jnum==4) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-301,null);
			
		}
		if(jump==true&&right==false&&jnum==4) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-301,null);
		}
		if(jump==true&&right==true&&jnum==5) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-301,null);
			
		}
		if(jump==true&&right==false&&jnum==5) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-301,null);
		}
		if(jump==true&&right==true&&jnum==6) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-321,null);
			
		}
		if(jump==true&&right==false&&jnum==6) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-321,null);
		}
		if(jump==true&&right==true&&jnum==7) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-321,null);
			
		}
		if(jump==true&&right==false&&jnum==7) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-321,null);
		}
		if(jump==true&&right==true&&jnum==8) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-240,null);
			
		}
		if(jump==true&&right==false&&jnum==8) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-240,null);
		}
		if(jump==true&&right==true&&jnum==9) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-140,null);
			
		}
		if(jump==true&&right==false&&jnum==9) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-140,null);
		}
		if(jump==true&&right==true&&jnum==10) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-80,null);
			
		}
		if(jump==true&&right==false&&jnum==10) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-80,null);
		}
		if(jump==true&&right==true&&jnum==11) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-40,null);
			
		}
		if(jump==true&&right==false&&jnum==11) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y-40,null);
		}
		if(jump==true&&right==true&&jnum==12) {
			g2d.drawImage(jumpright.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
			
		}
		if(jump==true&&right==false&&jnum==12) {
			g2d.drawImage(jumpleft.getScaledInstance(100, 100, Image.SCALE_DEFAULT),x,y,null);
		}
		
		
		g2d.setColor(Color.MAGENTA);
		GradientPaint gp = new GradientPaint((float)0.0, (float)0.0, Color.BLUE, (float)500.0, (float)500, Color.WHITE, true);
		g2d.setPaint(gp);
		if(!gameOn) {
			g2d.setColor(Color.WHITE);
			Font font = g.getFont().deriveFont( 80.0f );
			g2d.setFont(font);
			 g2d.drawString("GAME OVER", 300,300);
		}
		if(!gameOn&&x==900) {
			g2d.setColor(Color.WHITE);
			Font font = g.getFont().deriveFont( 80.0f );
			g2d.setFont(font);
			 g2d.drawString("You WIN!", 300,300);
		}
		//g2d.drawLine(100,100,500,500);

	}
	public void keyPressed(KeyEvent key)
	{
		if(gameOn) {
		System.out.println(key.getKeyCode());
		
		if(key.getKeyCode()==39)
		{
			right = true;
			move = true;
			if(x<400&&gamecount<3) {
				x+=30;
				
			}
			imgCount++;
			if(gamecount<3) {
			bx-=10;
			bx2-=15;
			bx3-=20;
			}
			if(gamecount==3) {
				if(x<900) {
					x+=30;
				}
				if(x==900) {
					gameOn = false;
				}
			}
			
			
			if(imgCount>10)
				imgCount=3;

			
		
			
		}
		
		else if(key.getKeyCode()==37)
		{
			right = false;
			move = true;
			if(x>150) {
			x-=30;
			}
			imgCount++; 
			if(x>0) {
			bx+=10;
			bx2+=15;
			bx3+=20;
			}
			if(imgCount>10)
				imgCount=3;

			
			
			
		}
		if(key.getKeyCode()==38)
		{

			
			jump = true;
			
		}
		
			
			repaint();
		
		if(key.getKeyCode()==82)
			restart=true;
		}
	}
	public void keyReleased(KeyEvent key)
	{
		
		if(key.getKeyCode()==39)
		{
			right = true;
			move = false;
		
			
		}
		
		else if(key.getKeyCode()==37)
		{
			right = false;
			move = false;
		
			
		}
	}
	
	public void keyTyped(KeyEvent key)
	{
		
	}
	public static void main(String args[])
	{
		SideScrollGame app=new SideScrollGame();
	}
	
}
