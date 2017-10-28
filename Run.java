import java.awt.*;
import java.awt.geom.*;
import javax.swing.*; 

public class Run {
	public static void main(String []args)
	{
		int w=800;
		int h=600;

		JFrame f = new JFrame();
		DrawingComponent dc = new DrawingComponent (w,h);

		f.getContentPane().setPreferredSize(new Dimension(w,h));
		f.setResizable(false);
		f.setTitle("Level Designer Tool");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(dc);
		f.pack();
		f.revalidate();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}