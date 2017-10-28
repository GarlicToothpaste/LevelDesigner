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
	}
}