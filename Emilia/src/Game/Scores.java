package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class Scores extends JFrame {

	// declare components
	private JTextField name1;
	
	private JButton again;
	private JButton mainMenu;
	private JButton check;
	
	//declare RGB color for background
	private Color bgr = new Color(27, 32, 38);
	
	private ArrayList<JLabel> points = new ArrayList<>();

	public Scores() {
		
		setLayout(null);

		getContentPane().setBackground(bgr);

		ImageIcon left = new ImageIcon("src/images/scores.png");
		JLabel image = new JLabel(left);
		image.setBounds(0, 250, 356, 732);
		add(image);

		ImageIcon highScore = new ImageIcon("src/images/hscores.png");
		JLabel highScores = new JLabel(highScore);
		highScores.setBounds(340, 220, 456, 141);
		add(highScores);

		name1 = new JTextField("Your Name");
		name1.setHorizontalAlignment(JTextField.CENTER);
		name1.setBounds(150, 100, 200, 50);
		name1.setFont(new Font("Consolas", Font.BOLD, 30));
		name1.setForeground(Color.WHITE);
		name1.setCaretColor(Color.WHITE);
		name1.setBackground(bgr);

		ImageIcon add = new ImageIcon("src/images/add.png");
		check = new JButton(add);
		check.setBorderPainted(false);
		check.setBackground(bgr);
		check.setBounds(600, 100, 46, 47);

		//create a JLabel to show the current score with MemoryGame obtained score
		JLabel score = new JLabel(String.valueOf(MemoryGame.score));
		score.setBounds(450, 100, 200, 50);
		score.setFont(new Font("Consolas", Font.BOLD, 30));
		score.setForeground(Color.WHITE);
		score.setBackground(bgr);
		add(score);

		ImageIcon ret = new ImageIcon("src/images/return (3).png");
		again = new JButton(ret);
		again.setBorderPainted(false);
		again.setBackground(bgr);
		again.setBounds(10, 10, 60, 60);

		ImageIcon main = new ImageIcon("src/images/mainMenu.png");
		mainMenu = new JButton(main);
		mainMenu.setBorderPainted(false);
		mainMenu.setBackground(bgr);
		mainMenu.setBounds(480, 920, 170, 60);

		//create new object Action3 to add action to the component
		Action3 action3 = new Action3();
		name1.addActionListener(action3);
		again.addActionListener(action3);
		mainMenu.addActionListener(action3);
		check.addActionListener(action3);

		//add components to the JFrame
		add(name1);
		add(again);
		add(mainMenu);
		add(check);
	}

	//create an action class only visible for this class
	private class Action3 implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			
			ImageIcon icon = new ImageIcon("src/images/icon.jpg");
			
			//if the user finished typing their name
			if (evt.getSource() == check) {
				String name = name1.getText().trim();
				name1.setEnabled(false);
				check.setEnabled(false);
				check.setVisible(false);
				int score = MemoryGame.score;
				Inicio.HighScores.add(new Node(score, name));
				Node head = new Node();
				points = new ArrayList<JLabel>();

				//we use an object Node (Node.java) and we add each component in HighScores
				for (Node m : Inicio.HighScores) {
					
					if (head.name == null) {
						
						head.data = m.data;
						head.name = m.name;
						
					} else {
						head.insert(m.data, m.name);
					}
				}
				Inicio.HighScores.removeAll(Inicio.HighScores);
				Node.printOrder(head, Inicio.HighScores);

				for (int i = 0; i < Inicio.HighScores.size(); i++) {
					String txt = Inicio.HighScores.get(i).name + ". . . . . " + Inicio.HighScores.get(i).data;
					JLabel x = new JLabel(txt);
					x.setBounds(400, 350 + (i * 70), 500, 50);
					x.setFont(new Font("Consolas", Font.BOLD, 25));
					x.setForeground(Color.WHITE);
					points.add(x);
					add(points.get(i));
				}
				repaint();

			}
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
			if (evt.getSource() == again) {
				dispose();
				Memory playMem = new Memory();
				playMem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				playMem.setSize(770, 1000);
				playMem.setLocationRelativeTo(null);
				playMem.setIconImage(icon.getImage());
				playMem.setUndecorated(true);
				playMem.setVisible(true);
				playMem.setResizable(false);
			}

		}

	}
}


