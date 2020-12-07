package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Memory extends JFrame {
	
	//initializes JButtons that will be used ('d#' represents difficulty and level)
	private JButton d1;
	private JButton d2;
	private JButton d3;
	private JButton mainMenu;

	public Memory() {
		
		setLayout(null);
		
		//creates new RGB color for background
		Color indigo = new Color(47, 36, 52);
		getContentPane().setBackground(indigo);

		ImageIcon down = new ImageIcon("src/images/Cat.jpg");
		JLabel image = new JLabel(down);
		image.setBounds(0, -30, 800, 540);
		add(image);

		ImageIcon dimensions = new ImageIcon("src/images/dimensions.png");
		JLabel select = new JLabel(dimensions);
		select.setBounds(5, 550, 800, 140);
		add(select);

		ImageIcon b1 = new ImageIcon("src/images/1.jpg");
		d1 = new JButton(b1);
		d1.setBorderPainted(false);
		d1.setBackground(indigo);
		d1.setBounds(100, 700, 93, 136);

		ImageIcon b2 = new ImageIcon("src/images/2.jpg");
		d2 = new JButton(b2);
		d2.setBorderPainted(false);
		d2.setBackground(indigo);
		d2.setBounds(350, 700, 92, 134);

		ImageIcon b3 = new ImageIcon("src/images/3.jpg");
		d3 = new JButton(b3);
		d3.setBorderPainted(false);
		d3.setBackground(indigo);
		d3.setBounds(600, 700, 92, 136);

		ImageIcon main = new ImageIcon("src/images/mainMenu.png");
		mainMenu = new JButton(main);
		mainMenu.setBorderPainted(false);
		mainMenu.setBackground(indigo);
		mainMenu.setBounds(10, 920, 170, 60);

		//creates a new object action (Action1.java) (Line 80)
		Action1 action1 = new Action1();
		d1.addActionListener(action1);
		d2.addActionListener(action1);
		d3.addActionListener(action1);
		mainMenu.addActionListener(action1);

		//add JButtons to the JFrame
		add(d1);
		add(d2);
		add(d3);
		add(mainMenu);

	}

	//initializes an Integer to know the dimensions of the game
	static int cardNumberV = 0;
	static int cardNumberH = 0;

	//Action 1: implements an action to each added component previously in the object created (only works for Memory.java)
	private class Action1 implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			//creates the icon that will be used in all instances
			ImageIcon icon = new ImageIcon("src/images/icon.jpg");
			
			if (evt.getSource() == mainMenu) {
				//disposes the JFrame Memory
				dispose();
				
				//creates a new object Menu to return to Menu.java
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
			
			//Difficulty 1 was clicked
			if (evt.getSource() == d1) {
				//sets the desired dimensions
				cardNumberV = 2;
				cardNumberH = 4;
				
				dispose();
				
				//creates new object MemoryGame (Memory.java)
				MemoryGame memory = new MemoryGame();
				memory.setSize(770, 1000);
				memory.setLocationRelativeTo(null);
				memory.setIconImage(icon.getImage());
				memory.setUndecorated(true);
				memory.setVisible(true);
				memory.setResizable(false);
			}
			
			//Difficulty 2 was clicked
			if (evt.getSource() == d2) {
				
				cardNumberV = 3;
				cardNumberH = 4;
				
				dispose();
				
				MemoryGame memory = new MemoryGame();
				memory.setSize(770, 1000);
				memory.setLocationRelativeTo(null);
				memory.setIconImage(icon.getImage());
				memory.setUndecorated(true);
				memory.setVisible(true);
				memory.setResizable(false);
			}
			
			//Difficulty 3 was clicked
			if (evt.getSource() == d3) {
				
				cardNumberV = 4;
				cardNumberH = 4;
				
				dispose();
				
				MemoryGame memory = new MemoryGame();
				memory.setSize(770, 1000);
				memory.setLocationRelativeTo(null);
				memory.setIconImage(icon.getImage());
				memory.setUndecorated(true);
				memory.setVisible(true);
				memory.setResizable(false);
			}
		}
	}
}
