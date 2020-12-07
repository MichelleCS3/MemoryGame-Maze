package Game;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class MemoryObj {
	
	//We declare the front image in variable 'image' and the back image as 'cardBack' (cardBack is the same for every card so its declared with the object)
	ImageIcon image;
	ImageIcon cardBack = new ImageIcon("src/MemoryG/card.jpg");
	
	//declare components
	JButton click = new JButton(cardBack);
	
	int number;
	int cardNum;

	//constructor to set a Memory Obj with image and number
	MemoryObj(ImageIcon image, int number) {
		this.image = image;
		this.number = number;
	}

	//contructor to only change the icon
	public void setIcon(ImageIcon m) {
		click.setIcon(m);
	}

}
