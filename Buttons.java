import java.awt.*;
import java.awt.geom.*;
public class Buttons{
	private double x,y,l,w;
	private Paint fillColor;
	String name;
	public Buttons(double x, double y, double l, double w, Paint fc, String s){
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		fillColor = fc;
		name = s;
	}
	public void draw(Graphics2D g2d){
		Rectangle2D.Double rectangle = new Rectangle2D.Double(x,y,l,w);
		g2d.setPaint(fillColor);
		g2d.fill(rectangle);

		int xPos, yPos;
		xPos = (int)x + 20;
		yPos = (int)y + 20;
		g2d.setFont(new Font("Arial", Font.PLAIN, 20));
		g2d.setPaint(Color.BLACK);
		g2d.drawString(name,xPos,yPos);
	}

}