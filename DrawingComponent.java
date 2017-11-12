import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

public class DrawingComponent extends JComponent{
	private Paint Background;
	int width, height, count;
	double mx, my;
	boolean addEdgeState;
	PrintWriter level;
	double scale, saveScale;
	Integer[] selectedNodes;
	Buttons addNode,addEdge,saveLevel, stopAdding;
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Line2D> edges = new ArrayList<Line2D>();
	//ArrayList<Integer[]> connectedNodes = new ArrayList<Integer[]>();
	ArrayList<Integer> conNodes = new ArrayList<Integer>();
	public DrawingComponent(int x, int y){
		width=x;
		height=y;
		System.out.println(height);
		scale = (double)width/2000;
		saveScale = 2000/width;
		//System.out.println(scale);
		Background=Color.BLACK;
		addNode = new Buttons(20,20,120,30,Color.WHITE,"Add Node");
		addEdge = new Buttons(150,20,120,30,Color.WHITE,"Add Edge");
		stopAdding = new Buttons(150,20,120,30,Color.WHITE,"Stop");
		saveLevel = new Buttons(280,20,140,30,Color.WHITE,"Save Level");
		addEdgeState = false;
		selectedNodes = new Integer[2];
		this.addMouseListener(new bListener());
		this.addMouseMotionListener(new bListener());
	}

	public void paintComponent (Graphics g){
		//window stuff
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setPaint(Background);
		g2d.fillRect(0,0,width,height);
		g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON));

		//buttons
		addNode.draw(g2d);
		saveLevel.draw(g2d);
		if (!addEdgeState) addEdge.draw(g2d);
		else stopAdding.draw(g2d);

		//objects
		drawNodesAndEdges(g2d);
		repaint();
	}

	public void drawNodesAndEdges(Graphics2D g2d){
		for (int i=0; i<=(nodes.size()-1); i++) {
			(nodes.get(i)).draw(g2d);
		}
		updateEdges();
		//System.out.println(edges.size());
		for (int i=0; i <= (edges.size()-1); i++){
			g2d.draw(edges.get(i));
		}
	}

	public void addNode(){
		Node node;
		double rescaled = scale * 150;
		//System.out.println(rescaled);
		node = new Node(200,200,rescaled,Color.WHITE);
		nodes.add(node);
		System.out.println(nodes.size());
	}

	public void addEdge(int a, int b){
		Line2D line = new Line2D.Double(
			nodes.get(a).getCenterX(), 
			nodes.get(a).getCenterY(), 
			nodes.get(b).getCenterX(), 
			nodes.get(b).getCenterY());
		System.out.println("Edge added!");
		edges.add(line);
	}

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

	public void updateEdges(){
		for (int i = 0; i <= (conNodes.size()-1); i=i+2){
			edges.get(i/2).setLine(
				nodes.get(conNodes.get(i)).getCenterX(), 
				nodes.get(conNodes.get(i)).getCenterY(), 
				nodes.get(conNodes.get(i+1)).getCenterX(), 
				nodes.get(conNodes.get(i+1)).getCenterY());
		}
	}

	public void saveLevel(){
		try{
			PrintWriter level = new PrintWriter("level.txt");
			level.println(nodes.size());
			level.println((conNodes.size())/2);
			for (int i=0; i<=(nodes.size()-1); i++ ){
				level.println(((nodes.get(i)).getX()*saveScale) + " " + (((nodes.get(i)).getY()*saveScale)));
			}
			for (int i=0; i<= (conNodes.size()-1); i=i+2){
			 	level.println(conNodes.get(i) + " " + conNodes.get(i+1));
			 }

			level.close();
		}
		catch(IOException e){}
	}

	public class bListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			mx = e.getX();
			my = e.getY();

			//If the mouse is clicked inside the buttons
			if(my>=20 && my<=50){
				//add node
				if(mx>=20 && mx<=140){
					addNode();
					repaint();
				}
				//save level
				else if(mx>=280 && mx<=420){
					saveLevel();
					System.out.println("Save Level Button Clicked!");
				}
				//add edge
				else if(mx>=150 && mx<=270){
					addEdgeState = !addEdgeState;
					repaint();
				}
			}

			//check if addstate is on and pointer is on a node


		}
		public void mouseDragged(MouseEvent e){
			if(checkNodes(mx, my) == true && addEdgeState == false){
				int selectedNode = getCurrentNode(mx,my);
					//Gets the original Coordinates of the selected node
				double prevX = (nodes.get(selectedNode).getX());
				double prevY = (nodes.get(selectedNode).getY());
					//Gets the difference of the new coordinates to the old mouse coordinates
				double diffX = e.getX() - mx;
				double diffY = e.getY() - my;
					//Adds the of the old coordiantes to the diffX and diffY
				double newX = prevX + diffX;
				double newY = prevY + diffY;
					//Updates the drawing
				nodes.get(selectedNode).updateX(newX);
				nodes.get(selectedNode).updateY(newY);
					//Updates mx and my
				mx = e.getX();
				my = e.getY();

				System.out.println(getCurrentNode(mx,my));
			}
		}
		public void mousePressed(MouseEvent e){
			mx = e.getX();
			my = e.getY();
		}
		public void mouseReleased(MouseEvent e){
			mx = e.getX();
			my = e.getY();
			if(addEdgeState == true && checkNodes(mx, my) == true ){
				if (selectedNodes[0] == null){
					selectedNodes[0] = getCurrentNode(mx, my);
					System.out.println("selected " + selectedNodes[0]);
				}
				else {
					selectedNodes[1] = getCurrentNode(mx, my);
					//connectedNodes.add(selectedNodes);
					conNodes.add(selectedNodes[0]);
					conNodes.add(selectedNodes[1]);
					System.out.println("selected " + selectedNodes[1]);
					addEdge(selectedNodes[0], selectedNodes[1]);
					
					//reset stuff
					selectedNodes[0] = null;
					selectedNodes[1] = null;
					addEdgeState = false;
				}
			}
		}
	}
}
