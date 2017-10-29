import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class DrawingComponent extends JComponent{
	int width, height;
	Node node1;
	Buttons addNode,addEdge,saveLevel;
	private ArrayList<Shape> shapes = new ArrayList<>();
    private Paint Background;

	public DrawingComponent(int x, int y){
		width=x;
		height=y;
		Background= Color.BLACK;
		node1= new Node(400,400,40,Color.WHITE);
		addNode =new Buttons(20,20,120,30,Color.WHITE,"Add Node");
		addEdge =new Buttons(150,20,120,30,Color.WHITE,"Add Edge");
		saveLevel = new Buttons(280,20,140,30,Color.WHITE,"Save Level");

	}
	protected void paintComponent (Graphics g){
		Graphics2D g2d = (Graphics2D) g;
       	g2d.setPaint(Background);
       	g2d.fillRect(0,0,width,height);

       	g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
       	
       	node1.draw(g2d);
       	addNode.draw(g2d);
       	addEdge.draw(g2d);
       	saveLevel.draw(g2d);

       	this.addMouseListener(new bListener());
	}

		public class bListener implements MouseListener{
			public void mouseReleased(MouseEvent e){

			}
			public void mouseEntered(MouseEvent e){

			}
			public void mouseExited(MouseEvent e){

			}
			public void mouseClicked(MouseEvent e){

			}
			public void mousePressed(MouseEvent e){
				int mx = e.getX();
				int my = e.getY();
				if(my>=20 && my<=50){
					//If add Node is Clicked
					if(mx>=20 && mx<=140){
						System.out.println("Add Node Button Clicked!");
					}
					//If add Edge is Clicked
					if(mx>=150 && mx<=270){
						System.out.println("Edd Edge Button Clicked!");
					}
					if(mx>=280 && mx<=420){
						System.out.println("Save Level Button Clicked!");
					}
				}
			}
		}
}