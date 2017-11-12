import java.awt.*;
import java.awt.geom.*;
import javax.swing.*; 

public class Run {
	public static void main(String []args)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w=(int)screenSize.getHeight()*3/4;
		int h=(int)screenSize.getHeight()*3/4;

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