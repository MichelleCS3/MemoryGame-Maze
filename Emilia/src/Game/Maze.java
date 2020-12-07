package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class Maze extends JFrame {

	// we draw the maze with a 2D matrix where 0 represents there's no border and 1
	// there's a border
	// numbers represent a node
	public int[][] borders = { { 1, 0, 0, 2, 0, 1, 1, 3 }, { 4, 0, 1, 0, 0, 5, 1, 0 }, { 1, 0, 0, 0, 1, 0, 1, 0 },
			{ 1, 0, 6, 0, 1, 0, 0, 0 }, { 1, 0, 0, 1, 1, 7, 0, 0 }, { 0, 0, 0, 1, 1, 0, 1, 0 },
			{ 8, 1, 0, 1, 1, 0, 1, 9 }, { 0, 0, 10, 0, 0, 0, 0, 0 } };

	// We create an Adjacency Matrix representing when a node is next to the other
	public int[][] AdjacencyMatrix = { { 0, 0, 3, 2, 3, 0, 8, 0, 0 }, { 0, 0, 0, 6, 0, 5, 0, 5, 0 },
			{ 3, 0, 0, 0, 3, 0, 6, 0, 0 }, { 2, 6, 0, 0, 4, 2, 0, 6, 0 }, { 3, 0, 3, 4, 0, 0, 4, 0, 3 },
			{ 0, 5, 0, 2, 0, 0, 0, 3, 5 }, { 8, 0, 6, 0, 4, 0, 0, 0, 2 }, { 0, 5, 0, 6, 0, 3, 0, 0, 5 },
			{ 0, 0, 0, 0, 3, 5, 2, 5, 0 } };
	
	private Queue <Integer> points= new LinkedList <> ();

	// size is going to be the same for x and y (8x8)
	private int size = 8;

	// startPoint and endPoint represents the node where the player will start and
	// end respectively
	int startPoint = Menu.start;
	int endPoint = Menu.end;

	private int x;
	private int y;
	private int x1;
	private int y1;

	// actualX and actualY are going to represent the current position of the player
	public static int actualX = 0;
	public static int actualY = 0;
	public static int score;

	private JButton mainMenu;

	JLabel pickle;
	JLabel endP;

	// declare new Actions to move the player
	Action upAction;
	Action downAction;
	Action leftAction;
	Action rightAction;

	public Maze() {
		
		//set the background
		Color amarillito = new Color(239, 231, 166);
		setLayout(null);
		getContentPane().setBackground(amarillito);

		//add components
		ImageIcon down = new ImageIcon("src/images/ram.png");
		JLabel image = new JLabel(down);
		image.setBounds(0, 670, 507, 338);
		add(image);

		ImageIcon wasd= new ImageIcon("src/images/wsad.png");
		JLabel movement = new JLabel(wasd);
		movement.setBounds(550,730,164,116);
		add(movement);
		
		ImageIcon score= new ImageIcon("src/images/score.png");
		JLabel scoring = new JLabel (score);
		scoring.setBounds(450,30,149,46);
		add(scoring);
		
		//this JLabel shows the actual score
		JLabel actualScore = new JLabel(String.valueOf(Menu.score));
		actualScore.setBounds(620, 35, 100, 46);
		actualScore.setFont(new Font("Consolas", Font.BOLD, 35));
		actualScore.setForeground(Color.BLACK);
		actualScore.setBackground(amarillito);
		add(actualScore);
		
		ImageIcon main = new ImageIcon("src/images/mainMenu.png");
		mainMenu = new JButton(main);
		mainMenu.setBorderPainted(false);
		mainMenu.setBackground(amarillito);
		mainMenu.setBounds(550, 870, 170, 60);

		for (int i = 0; i < size; i++) {

			for (int j = 0; j < 8; j++) {

				int condition = borders[i][j];

				// we get the coordinates for the startPoint and endPoint
				if (condition == startPoint) {

					x = 72 * j + 110;
					y = 70 * i + 112;
				}
				if (condition == endPoint) {

					x1 = 72 * j + 110;
					y1 = 70 * i + 112;
				}
			}
		}

		// new object GenerateGraph (GenerateGraph.java)
		GenerateGraph solution = new GenerateGraph(AdjacencyMatrix, startPoint, endPoint);
		points=solution.dijskstra(AdjacencyMatrix, startPoint, endPoint);
		
		// actualX and actual Y indicate the current location of our character "Pickle"
		actualX = (x - 110) / 72;
		actualY = (y - 112) / 70;

		ImageIcon p = new ImageIcon("src/images/sqr1.png");
		pickle = new JLabel(p);
		pickle.setBounds(x, y, 72, 70);

		// with keyBindings we add action to ourJLabel
		upAction = new UpAction();
		downAction = new DownAction();
		leftAction = new LeftAction();
		rightAction = new RightAction();

		pickle.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAct");
		pickle.getActionMap().put("upAct", upAction);

		pickle.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAct");
		pickle.getActionMap().put("downAct", downAction);

		pickle.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAct");
		pickle.getActionMap().put("leftAct", leftAction);

		pickle.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAct");
		pickle.getActionMap().put("rightAct", rightAction);

		ImageIcon e = new ImageIcon("src/images/sqr2.png");
		endP = new JLabel(e);
		endP.setBounds(x1, y1, 72, 70);

		//here we create a new private action 4 to add action to mainMenu button
		Action4 action4 = new Action4();
		mainMenu.addActionListener(action4);

		//add components to the JFrame
		add(mainMenu);
		add(pickle);
		add(endP);

	}

	private class Action4 implements ActionListener {

		public void actionPerformed(ActionEvent evt) {
			
			if (evt.getSource() == mainMenu) {
				dispose();
				Menu play = new Menu();
				play.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				play.setSize(770, 1000);
				play.setLocationRelativeTo(null);
				ImageIcon menuicono = new ImageIcon("src/images/icon.jpg");
				play.setIconImage(menuicono.getImage());
				play.setUndecorated(true);
				play.setVisible(true);
				play.setResizable(false);
			}

		}
	}

	// here are the KeyBinding classes it increases and decreases by 72 in X an 70
	// in Y (which is also the size of the JLabel), since they only work for this
	// class we declared them private
	private class UpAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (pickle.getY() > 112) {
				actualY--;
				if (borders[actualY][actualX] == 1) {
					actualY++;
				} else {
					pickle.setLocation(pickle.getX(), pickle.getY() - 70);
					addPoints();
				}
			}
		}

	}

	private class DownAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {

			if (pickle.getY() < 560) {
				actualY++;
				if (borders[actualY][actualX] == 1) {
					actualY--;
				} else {
					pickle.setLocation(pickle.getX(), pickle.getY() + 70);
					addPoints();
				}
				
			}

		}

	}

	private class LeftAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (pickle.getX() > 110) {
				actualX--;
				if (borders[actualY][actualX] == 1) {
					actualX++;
				} else {
					pickle.setLocation(pickle.getX() - 72, pickle.getY());
					addPoints();
				}
				
			}

		}

	}

	private class RightAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (pickle.getX() < 576) {
				actualX++;
				if (borders[actualY][actualX] == 1) {
					actualX--;
				} else {
					pickle.setLocation(pickle.getX() + 72, pickle.getY());
					addPoints();
				}

			}

		}

	}

	
	//this method adds the points if they passed the correct nodes (Queue points)
	public void addPoints()
	{
		
		//validate if it has passed through the node (check point / brown squares)
		if(borders[actualY][actualX]==points.peek()) {
			
			points.poll();
			
				//if there's no more nodes to challenge the player then dispose this JFrame and create a new one
				if(points.isEmpty()) {
					
					dispose();
					
					//here we move the start points
					Menu.score= Menu.score + 10;	
					Menu.end--;
					
					if(Menu.end==Menu.start) {
						Menu.start++;
						Menu.end=10;
							if(Menu.start==10) {
								
								JOptionPane.showMessageDialog(null,"You've finished this game");
								dispose();

							}
					}
					
					
					//create new Maze.java
					ImageIcon laby = new ImageIcon("src/images/laby.png");
					JLabel labr = new JLabel(laby);
					labr.setBounds(110, 112, 576, 560);
					labr.setVerticalAlignment(JLabel.BOTTOM);

					Maze maze = new Maze();
					maze.add(labr);
					maze.setSize(770, 1000);
					maze.setLocationRelativeTo(null);
					ImageIcon icon = new ImageIcon("src/images/icon.jpg");
					maze.setIconImage(icon.getImage());
					maze.setUndecorated(true);
					maze.setVisible(true);
					maze.setResizable(false);
				}
		}
	}
}
