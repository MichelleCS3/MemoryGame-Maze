package Game;

import java.util.ArrayList;

class Node {

	//declare components
	// name: player's name/ data=:player's score/ Node left and Node right: are for constructing binary tree
	String name;
	int data;
	Node left;
	Node right;

	//constructor with name and data
	Node(int data, String name) {
		this.data = data;
		this.name = name;
	}

	//empty constructor
	Node() {

	}

	//insert a new node into the tree structure
	public void insert(int data, String name) {

		if (data < this.data) {
			if (this.left != null) {
				this.left.insert(data, name);
			} else {
				this.left = new Node(data, name);
			}

		}

		else {
			if (this.right != null) {
				this.right.insert(data, name);
			} else {
				this.right = new Node(data, name);
			}

		}
	}

	//print in order from biggest to smallest value (data)
	public static void printOrder(Node n, ArrayList<Node> order) {
		
		if (n.right != null) {
			printOrder(n.right, order);
		}

		order.add(n);

		if (n.left != null) {
			printOrder(n.left, order);
		}
		
	}
}