package Minesweeper;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Main extends JPanel implements ActionListener {
	static final int height = 500;
	static final int width = 500;
	Timer timer;

	//TODO: add a GUI?
	Main() {
		JFrame Frame = new JFrame("Minesweeper"); // Insert Name here
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(true);
		Frame.pack();
		Frame.setVisible(true);
		Frame.setSize(width, height);
		Frame.add(this);
		Frame.addMouseListener(new Input());

		//this.setPreferredSize(new Dimension(width + 100, height + 100));
		this.setBackground(new Color(5,5,5)); // Use this to change the background color
		this.setFocusable(true);

		/** This can be commented out if a static image is to be produced. **/
		timer = new Timer(1, this);
		timer.start();
		
	}

	/** Anything that you want to draw goes in here **/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		new Paint(g);
	}

	/** Anything that you want to happened repeatedly goes in here **/
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public static void main(String[] args) {
		Game.start();
		new Main();
		
		
		
	}
}