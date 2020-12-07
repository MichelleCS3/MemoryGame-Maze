package Game;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Inicio {
	
	//Global array to sort scores in memory game (Scores.java)
	protected static ArrayList <Node> HighScores= new ArrayList <> (); 
	
	public static void main(String[] args) {
		//Creates new object Menu (Menu.java) so the user can choose which game to play
		Menu play= new Menu();
		play.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		play.setSize(770,1000);
		play.setLocationRelativeTo(null);
		ImageIcon menuicono = new ImageIcon("src/images/icon.jpg");
		play.setIconImage(menuicono.getImage());
		play.setUndecorated(true);
		play.setVisible(true);
		play.setResizable(false);
	}

}


