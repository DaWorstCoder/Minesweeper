package Minesweeper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Input implements MouseListener {

	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mouseClicked(MouseEvent e) {
		int boardMouseCol = (int) ((e.getX() / (double) Main.width) * Game.cols); //TODO: replace with block size
		int boardMouseRow = (int) (((e.getY() -Main.topBanner) / (double) Main.height) * Game.rows);

		if (!Game.over) {
			if(e.getButton() == MouseEvent.BUTTON3) { //right click

				if (Game.board[boardMouseRow][boardMouseCol].flagged) {
					Game.board[boardMouseRow][boardMouseCol].flagged = false;
				} else {
					Game.board[boardMouseRow][boardMouseCol].flagged = true;
				}

			}
			if(e.getButton() == MouseEvent.BUTTON1) { //left click
				Game.blockClicked(Game.board[boardMouseRow][boardMouseCol]);
				
			}
		}
		
	}


}