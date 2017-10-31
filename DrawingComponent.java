import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class DrawingComponent extends JComponent{
	int width, height;
	Node node1;
	Buttons addNode,addEdge,saveLevel;
	ArrayList<Node> nodes = new ArrayList<>();
    private Paint Background;
    double mx,my;

	public DrawingComponent(int x, int y){
		width=x;
		height=y;
		Background= Color.BLACK;
		node1= new Node(400,400,40,Color.WHITE);
		addNode =new Buttons(20,20,120,30,Color.WHITE,"Add Node");
		addEdge =new Buttons(150,20,120,30,Color.WHITE,"Add Edge");
		saveLevel = new Buttons(280,20,140,30,Color.WHITE,"Save Level");
		//node = new Node(200,200,40,Color.WHITE);
       	this.addMouseListener(new bListener());
       	this.addMouseMotionListener(new bListener());
	}
	public void paintComponent (Graphics g){
		Graphics2D g2d = (Graphics2D) g;
       	g2d.setPaint(Background);
       	g2d.fillRect(0,0,width,height);

       	g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
       	
       	//node1.draw(g2d);
       	addNode.draw(g2d);
       	addEdge.draw(g2d);
       	saveLevel.draw(g2d);
       	drawNodes(g2d);
       	repaint();
	}
		public class bListener extends MouseAdapter{
			
			public void mouseClicked(MouseEvent e){

			}
			public void mouseDragged(MouseEvent e){
				if(checkNodes(mx,my)==true){
					int selectedNode = getCurrentNode(mx,my);
					//Gets the original Coordinates of the selected node
					double prevX = (nodes.get(selectedNode).getX());
					double prevY = (nodes.get(selectedNode).getY());
					//Gets the difference of the new coordinates to the old mouse coordinates
					double diffX = e.getX()-mx;
					double diffY = e.getY()-my;
					//Adds the of the old coordiantes to the diffX and diffY
					double newX = prevX+diffX;
					double newY = prevY+diffY;
					//Updates the drawing
					nodes.get(selectedNode).updateX(newX);
					nodes.get(selectedNode).updateY(newY);
					//Updates mx and my
					mx = e.getX();
					my = e.getY();
					//System.out.println("mx: " + mx+ "my" + my);
					//repaint();
					System.out.println(getCurrentNode(mx,my));

				}
			}
			public void mouseEntered(MouseEvent e){

			}
			public void mouseExited(MouseEvent e){

			}
			public void mouseMoved(MouseEvent e){

			}
			public void mousePressed(MouseEvent e){

				mx = e.getX();
				my = e.getY();
				//If the mouse is clicked inside the buttons
				if(my>=20 && my<=50){
					//If add Node is Clicked
					if(mx>=20 && mx<=140){
						addNode();
						repaint();
						System.out.println("Add Node Button Clicked!");
					}
					//If add Edge is Clicked
					if(mx>=150 && mx<=270){
						System.out.println("Add Edge Button Clicked!");
					}
					//If Save Level is Clicked!
					if(mx>=280 && mx<=420){
						System.out.println("Save Level Button Clicked!");
					}
				}


				//If the mouse is clicked inside the node
				// if(node1.isInsideNode(mx,my) == true){
				// 	System.out.println("Mouse Inside node!");
				// }

				//If the mouse is clicked, it checks if the mouse is in a node.
				if(checkNodes(mx,my)==true){
					System.out.println("Mouse Inside Node!");
				}
			}
			public void mouseReleased(MouseEvent e){

			}
			public void mouseWheelMoved(MouseEvent e){

			}
		}
		//Draws the nodes in the arraylist
		public void drawNodes (Graphics2D g2d){
			for (int i=0; i<=(nodes.size()-1); i++) {
				(nodes.get(i)).draw(g2d);
			}
		}
		//Adds a node to the arraylist.
		public void addNode (){
			Node node;
			int indexNumber = nodes.size();
			node = new Node(200,200,40,Color.WHITE);
			nodes.add(indexNumber,node);
			System.out.println(nodes.size());
		}
		//Checks if mous is inside node.
		public boolean checkNodes(double xPos, double yPos){
			boolean var = false;
			for (int i=0; i<=(nodes.size()-1); i++ ){
				if((nodes.get(i)).isInsideNode(xPos,yPos)== true){
					var = true;
					break;
				}

			}
			return var;
		}
		public int getCurrentNode(double xPos, double yPos){
			boolean var = false;
			int currNode = -1;
			for (int i=0; i<=(nodes.size()-1); i++ ){
				if((nodes.get(i)).isInsideNode(xPos,yPos)== true){
					var = true;
					if (var==true){
						currNode = i;
					}
					break;
				}

			}
			return currNode;
		}
}