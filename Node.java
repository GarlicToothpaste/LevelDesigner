import java.awt.*;
import java.awt.geom.*;
public class Node{
    private double x,y,radius;
    private Paint fillColor,strokeColor;
    private Stroke stroke;

    public Node(double x, double y, double r, Paint fc, Paint sc, Stroke st){
        this.x = x;
        this.y = y;
        radius = r;
        fillColor = fc;
        strokeColor = sc;
        stroke = st;
    }

    public void draw(Graphics2D g2d){
		Ellipse2D.Double circle = new Ellipse2D.Double(x,y,radius*2,radius*2);
		g2d.setPaint(fillColor);
		g2d.fill(circle);
		g2d.setPaint(strokeColor);
		g2d.setStroke(stroke);
		g2d.draw(circle);
    }

}