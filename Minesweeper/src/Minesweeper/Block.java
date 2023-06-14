package Minesweeper;

/**An object that defines each block in the board**/
public class Block {
	int xPos;
	int yPos;
	int numOfMines;
	boolean mine;
	boolean flagged = false;
	boolean visible = false;
	
	boolean leftEdgePiece = false;
	boolean topEdgePiece = false;
	boolean rightEdgePiece = false;
	boolean bottomEdgePiece = false;
	
	public Block(int xPos, int yPos, boolean mine) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.mine = mine;
		
		if (xPos == 0) {
			leftEdgePiece = true;
		}
		if (xPos == Game.board[0].length - 1) {
			rightEdgePiece = true;
		}
		if (yPos == 0) {
			topEdgePiece = true;
		}
		if (yPos == Game.board.length - 1) {
			bottomEdgePiece = true;
		}
	}
	
}
