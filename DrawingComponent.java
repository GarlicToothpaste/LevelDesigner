import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;

public class DrawingComponent extends JComponent{
	int width, height;
	Node node1;
	Buttons addNode,addEdge,saveLevel, stopAdding;
	ArrayList<Node> nodes = new ArrayList<Node>();
	ArrayList<Integer> connectedNodesColumn1 = new ArrayList<Integer>();
	ArrayList<Integer> connectedNodesColumn2 = new ArrayList<Integer>();

    private Paint Background;
    double mx,my;
    PrintWriter level;
    boolean add;

    int count;
    int firstNode;
    int secondNode;
	public DrawingComponent(int x, int y){
		width=x;
		height=y;
		Background=Color.BLACK;
		node1 = new Node(400,400,40,Color.WHITE);
		addNode = new Buttons(20,20,120,30,Color.WHITE,"Add Node");
		addEdge = new Buttons(150,20,120,30,Color.WHITE,"Add Edge");
		stopAdding = new Buttons(150,20,120,30,Color.WHITE,"Stop");
		saveLevel = new Buttons(280,20,140,30,Color.WHITE,"Save Level");
				//node = new Node(200,200,40,Color.WHITE);
		add=false;
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
       	
       	saveLevel.draw(g2d);
       	if (add==false) {
       		addEdge.draw(g2d);
       	}
       	if (add==true) {
       		stopAdding.draw(g2d);
       	}
       	
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
						//System.out.println("Add Node Button Clicked!");
					}
					//If add Edge is Clicked
					if(mx>=150 && mx<=270){
						System.out.println("Add Edge Button Clicked!");
						
						if(add==false){
							add = true;
						}
						else{
							add = false;
						}
						System.out.println(add);
						repaint();
					}
					//If Save Level is Clicked!
					if(mx>=280 && mx<=420){
						saveLevel();
						System.out.println("Save Level Button Clicked!");
					}
				}

				//CODE BELOW UNFINISHED
				//This should be able to select the 2 nodes connected
				if(add == true){
					boolean loop = true;
					if(checkNodes(mx,my)==true){
						while(loop != false){
							count = 0;
							if (count ==0){
								double rx = e.getX();
								double ry = e.getY();
 								firstNode = getCurrentNode(rx,ry);
								try{
									Thread.sleep(10);
									count = 1;
								}
								catch(InterruptedException ex){}
								double tx = e.getX();
								double ty = e.getY();
								if (count == 1){
									secondNode = getCurrentNode(tx,ty);
									break;
								}
							}
						}

						System.out.println(firstNode + " " + secondNode);
						selectConnectedNodes(firstNode,secondNode);
						count =0;
						loop = true;
					}
				}
				//Note: The comments below are only for test.
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
		/*
			@param	xPos: the x coordinate of the mouse
					yPos: the y coordiante of the mouse
					returns if the mouse is currently in the node
		*/
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
		/*
			@param 	xPos: the x coordinate of the mouse
					yPos: the y coordinate of the mouse
					returns the current node selected from the ArrayList
		*/
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
		public void selectConnectedNodes(int x, int y){
			int arrCol1 = x;
			int arrCol2 = y;
			connectedNodesColumn1.add(arrCol1);
			connectedNodesColumn2.add(arrCol2);
		}
		public void drawEdge(){

		}
		/*
			Method responsible for saving the level
		*/
		public void saveLevel(){
			try{
				PrintWriter level = new PrintWriter("level.txt");
				level.println(nodes.size());
				levxl.println(connectedNodesColumn1.size());
				// //Add number of edges later on

				for (int i=0; i<=(nodes.size()-1); i++ ){
					level.println((nodes.get(i)).getX() + " " + (nodes.get(i)).getY());

				}
				for(int i=0; i<=(connectedNodesColumn1.size()-1); i++){
					level.println(connectedNodesColumn1.get(i) + " " + connectedNodesColumn2.get(i));
				}
				level.close();
			}
			catch(IOException e){}
		}
	}
