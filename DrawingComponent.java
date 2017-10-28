import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class DrawingComponent extends JComponent{
	int width, height;
	Node node1;
	private ArrayList<Shape> shapes = new ArrayList<>();
    private Color Background;

	public DrawingComponent(int x, int y){
		width=x;
		height=y;
		Background=new Color(102, 152, 255);
		node1= new Node(400,400,10,Color.BLACK,Color.BLACK,new BasicStroke(1));
	}
	protected void paintComponent (Graphics g){
		Graphics2D g2d = (Graphics2D) g;
      	GradientPaint gp = new GradientPaint(0,0,Background,0,height+200,Color.WHITE);
       	g2d.setPaint(gp);
       	g2d.fillRect(0,0,width+300,height);

       	node1.draw(g2d);
	}
}