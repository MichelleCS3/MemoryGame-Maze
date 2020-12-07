package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Menu extends JFrame {

	private JButton exit;
	private JButton memory;
	private JButton maze;
	private JButton chat;

	public Menu() {
		setLayout(null);
		Color negrito = new Color(15, 8, 16);
		getContentPane().setBackground(negrito);

		ImageIcon back = new ImageIcon("src/images/arcade.jpg");
		JLabel image = new JLabel(back);
		image.setBounds(0, 450, 771, 550);
		add(image);

		ImageIcon close = new ImageIcon("src/images/close.png");
		exit = new JButton(close);
		exit.setBorderPainted(false);
		exit.setBackground(negrito);
		exit.setBounds(700, 10, 59, 59);

		ImageIcon letters = new ImageIcon("src/images/letters.png");
		JLabel title = new JLabel(letters);
		title.setBackground(negrito);
		title.setBounds(0, 20, 780, 300);
		add(title);

		ImageIcon memgame = new ImageIcon("src/images/memgame.png");
		memory = new JButton(memgame);
		memory.setBorderPainted(false);
		memory.setBackground(negrito);
		memory.setBounds(180, 250, 131, 200);

		ImageIcon lab = new ImageIcon("src/images/maze.png");
		maze = new JButton(lab);
		maze.setBorderPainted(false);
		maze.setBackground(negrito);
		maze.setBounds(450, 255, 129, 186);
		
		//creates a new object action (Action.java) (Line 74)
		Action action = new Action();
		exit.addActionListener(action);
		memory.addActionListener(action);
		maze.addActionListener(action);

		//add the JButtons to the JFrame
		add(exit);
		add(memory);
		add(maze);
	}

	static int start = 2;
	static int end = 10;
	static int score=0;
	// Action.java: implements an action to each JButton of the class (this action is only
	// useful for this class)
	private class Action implements ActionListener {
	
		public void actionPerformed(ActionEvent evt) {
			
			ImageIcon icon = new ImageIcon("src/images/icon.jpg");
			
			if (evt.getSource() == exit) {
				//deletes the JFrame
				dispose();
			}
			
			if (evt.getSource() == memory) {
				//deletes the JFrame and creates a new JFrame with the memory game menu
				dispose();
				
				Memory playMem = new Memory();
				playMem.setSize(770, 1000);
				playMem.setLocationRelativeTo(null);
				playMem.setIconImage(icon.getImage());
				playMem.setUndecorated(true);
				playMem.setVisible(true);
				playMem.setResizable(false);
			}
			
			if (evt.getSource() == maze) {
				//deletes the JFrame and creates a new JFrame with the maze
				dispose();
				
				//Creates a new JLabel with the image that will be used in the maze
				ImageIcon laby = new ImageIcon("src/images/laby.png");
				JLabel labr = new JLabel(laby);
				labr.setBounds(110, 112, 576, 560);
				labr.setVerticalAlignment(JLabel.BOTTOM);

				Maze maze = new Maze();
				maze.add(labr);
				maze.setSize(770, 1000);
				maze.setLocationRelativeTo(null);
				maze.setIconImage(icon.getImage());
				maze.setUndecorated(true);
				maze.setVisible(true);
				maze.setResizable(false);

			}

		}
	}
	
}
