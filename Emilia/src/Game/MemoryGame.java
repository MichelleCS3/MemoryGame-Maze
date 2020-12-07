package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class MemoryGame extends JFrame {

	// declares objects
	private ArrayList<ArrayList<MemoryObj>> cards = new ArrayList<>();

	private ArrayList<MemoryObj> deck = new ArrayList<>();

	private JButton back;
	private JButton die;

	private JLabel triesNum;

	public MemoryGame() {

		setLayout(null);

		Color indigo = new Color(47, 36, 52);
		getContentPane().setBackground(indigo);

		// create new object Action 2 to add actions to the component previously added
		Action2 action2 = new Action2();

		// get a deck of cards by sending it to private method drawCards
		drawCards(deck);

		// We shuffle it to make the game random
		Collections.shuffle(deck);

		// We paint the cards in the JFrame at the same time as adding it to our ArrayList deck
		paintAndAddCards(cards,action2);

		//Add components with the images in the 'images' package
		ImageIcon goBack = new ImageIcon("src/images/back.png"); 
		back = new JButton(goBack);
		back.setBorderPainted(false);
		back.setBackground(indigo);
		back.setBounds(10, 910, 170, 60);
		back.addActionListener(action2);

		ImageIcon end = new ImageIcon("src/images/end.png");
		die = new JButton(end);
		die.setBorderPainted(false);
		die.setBackground(indigo);
		die.setBounds(590, 910, 170, 60);
		die.addActionListener(action2);

		ImageIcon t = new ImageIcon("src/images/tries.png");
		JLabel tries = new JLabel(t);
		tries.setBackground(indigo);
		tries.setBounds(250, 910, 172, 60);
		add(tries);

		triesNum = new JLabel(String.valueOf(0));
		triesNum.setBounds(420, 920, 170, 35);
		triesNum.setFont(new Font("Consolas", Font.BOLD, 30));
		triesNum.setForeground(Color.WHITE);
		triesNum.setBackground(indigo);

		add(triesNum);
		add(back);
		add(die);
		add(triesNum);

	}

	private void paintAndAddCards(ArrayList<ArrayList<MemoryObj>> cards2, ActionListener action) {
		int cardCounter = 0;
		for (int i = 0; i < Memory.cardNumberV; i++) {
			ArrayList<MemoryObj> row = new ArrayList<>();
			for (int j = 0; j < Memory.cardNumberH; j++) {
				deck.get(cardCounter).cardNum = cardCounter;
				deck.get(cardCounter).click.setBounds(50 + (j * 190), 50 + (i * 200), 112, 180);
				deck.get(cardCounter).click.addActionListener(action);
				add(deck.get(cardCounter).click);
				cardCounter++;
			}
			cards.add(row);
		}
		
	}

	private void drawCards(ArrayList<MemoryObj> deck) {

		// Initiate a ImageIcon Stack with the images we will use for the memory game
		// (images available in package 'MemoryG')
		Stack<ImageIcon> images = new Stack<>();
		images.add(new ImageIcon("src/MemoryG/c1.jpg"));
		images.add(new ImageIcon("src/MemoryG/c2.jpg"));
		images.add(new ImageIcon("src/MemoryG/c3.jpg"));
		images.add(new ImageIcon("src/MemoryG/c4.png"));
		images.add(new ImageIcon("src/MemoryG/c5.jpg"));
		images.add(new ImageIcon("src/MemoryG/c6.jpg"));
		images.add(new ImageIcon("src/MemoryG/c7.jpg"));
		images.add(new ImageIcon("src/MemoryG/c8.jpg"));

		// create and initiate an integer identity
		int ident = 0;

		// create a deck of cards
		for (int c = 0; c < ((Memory.cardNumberH * Memory.cardNumberV) / 2); c++) {
			ImageIcon image = images.peek();

			// add image and identity 2 times to create pairs using MemoryObj constructor
			MemoryObj temp = new MemoryObj(image, ident);
			deck.add(temp);

			MemoryObj temp1 = new MemoryObj(image, ident);
			deck.add(temp1);

			ident++;

			// make sure the image is not going to be repeated by popping it out of the
			// Stack
			images.pop();

		}
	}

	
	//This is another class, but in this case private and its only visible for the class MemoryGame.java
	
	//We declare the objects and structures that are going to be used in this class
	static int count = 0;
	static int triesCounter;
	static int pairs = 0;
	static int score = 0;
	
	static MemoryObj voltear = null;
	static MemoryObj voltear1 = null;
	
	//'repeated' is going to validate our JButton (card) is not repeated
	static Queue<Integer> repeated = new PriorityQueue<Integer>();
	
	//'check' is going to check if its a pair or not
	static ArrayList<MemoryObj> check = new ArrayList<MemoryObj>();

	
	private class Action2 implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			
			//if 'back' is pressed we are create a new Memory object and dispose the current JFrame
			if (evt.getSource() == back) {
				
				dispose();
				
				Memory playMem = new Memory();
				playMem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				playMem.setSize(770, 1000);
				playMem.setLocationRelativeTo(null);
				ImageIcon icon = new ImageIcon("src/images/icon.jpg");
				playMem.setIconImage(icon.getImage());
				playMem.setUndecorated(true);
				playMem.setVisible(true);

				playMem.setResizable(false);
			}

			//if 'die' is pressed we exit the 'MemoryGame' JFrame and create object Scores, sending the score obtained
			if (evt.getSource() == die) {
				
				//if the player didn't try then his score its 0
				if(triesCounter==0)
				{
					score=0;
				}
				//this score is calculated according to the following variables value 
				else
				{
					score = (pairs * 300) - (triesCounter * 10) + (((Memory.cardNumberH * Memory.cardNumberV) / 2) * 10);
					triesCounter = 0;
					count = 0;
				}
				
				//exit MemoryGame JFrame since the game is over
				dispose();
				
				//create new JFrame Scores
				Scores scores = new Scores();
				scores.setSize(770, 1000);
				scores.setLocationRelativeTo(null);
				ImageIcon icon = new ImageIcon("src/images/icon.jpg");
				scores.setIconImage(icon.getImage());
				scores.setUndecorated(true);
				scores.setVisible(true);
				scores.setResizable(false);
			}

			//We validate if one of the JButtons is pressed and if its valid
			for (int c = 0; c < deck.size(); c++) {

				if (evt.getSource() == deck.get(c).click) {
					
					if (!(repeated.contains(deck.get(c).cardNum)) && count < 3) {
						
						//if its valid then we add it to 'repeated' so it can't be chosen again
						repeated.add(deck.get(c).cardNum);
						
						//we added to check for future validation
						check.add(deck.get(c));
						
						//change the image icon for the MemoryObject, ImageIcon image of the object
						ImageIcon m = deck.get(c).image;
						deck.get(c).setIcon(m);
						count++;
					}
				}
			}
			
			//we declare a flag to declare that we don't have a pair until we validate
			boolean isPair = false;
			
			if (count == 2) {
				if (check.get(0).number == check.get(1).number) {
					
					//once we validate its a pair via number then we set the JButtons as disables
					check.get(0).click.setEnabled(false);
					check.get(1).click.setEnabled(false);
					
					//there's a pair so we change the flag and also increase the number of pairs
					isPair = true;
					pairs++;
				
					count = 0;
					
					if (pairs == ((Memory.cardNumberH * Memory.cardNumberV) / 2)) {
						
						//if all the pairs are formed then we can end the game so we dispose it and display Scores
						score = (pairs * 300) - (triesCounter * 5) + (((Memory.cardNumberH * Memory.cardNumberV) / 2) * 10);
						triesCounter = 0;
						
						dispose();
						
						Scores scores = new Scores();
						scores.setSize(770, 1000);
						scores.setLocationRelativeTo(null);
						ImageIcon icon = new ImageIcon("src/images/icon.jpg");
						scores.setIconImage(icon.getImage());
						scores.setUndecorated(true);
						scores.setVisible(true);
						scores.setResizable(false);
					}
				}
				
				// if its not pair we still show the images for the user to visualize it
				if (!isPair) {
					voltear = check.get(0);
					voltear1 = check.get(1);
				}

				//we remove the objects from the structures in order to use the same structures again
				check.removeAll(check);
				repeated.clear();
				triesCounter++;
				triesNum.setText(String.valueOf(triesCounter));
				
			}
			

			//if it wasn't pair then we turn the cards around again
			if (count == 3 && !isPair && voltear != null && voltear1 != null) {
				ImageIcon m = check.get(0).cardBack;
				voltear.setIcon(m);
				voltear1.setIcon(m);
				count = 1;
			}

		}

	}
}
