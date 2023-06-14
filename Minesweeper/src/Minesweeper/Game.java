package Minesweeper;

import java.util.ArrayList;

/**Everything related to game mechanics**/
public class Game {

	public static int rows = 7;
	public static int cols = 7;
	public static Block[][] board = new Block[rows][cols];
	
	static boolean over = false;
	static boolean lose = false;
	
	private static int totalNumOfMines = 0;
			
	public static void start() {
		//Initalizing the board
		for(int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				double x = Math.random();
				if (x < .3) { //percentage of mines
					totalNumOfMines++;
					board[row][col] = new Block(col, row, true);
				} else {
					board[row][col] = new Block(col, row, false);
				}
				
			}
		}
		
		//Counting the number of mines, ignoring indexOutOfBoundsExceptions to deal with edge cases
		for(int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (!board[row][col].mine) {
					try {
						if (board[row + 1][col].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
					try {
						if (board[row - 1][col].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
					try {
						if (board[row][col + 1].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
					try {
						if (board[row][col - 1].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
					
					
					try {
						if (board[row + 1][col + 1].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
					try {
						if (board[row + 1][col - 1].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
					try {
						if (board[row - 1][col - 1].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
					try {
						if (board[row - 1][col + 1].mine) {
							board[row][col].numOfMines++;
						}	
					} catch(ArrayIndexOutOfBoundsException E) {}
				}
				
			}
		}
	}
	
	/**Detects whether or not the player has won based if each mine has been flagged.
	 * Only issue is that if the player flags all the blocks, they technically win, but whatever.**/
	static boolean Win() {
		int flagTot = 0;
		for(int row = 0; row < Game.board.length; row++) {
			for (int col = 0; col < Game.board[0].length; col++) {
				if (Game.board[row][col].mine) {
					if (Game.board[row][col].flagged) {
						flagTot++;
					}
				}
			}
		}
		
		if (flagTot == totalNumOfMines) {
			over = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**What to do when a block has been clicked**/
	static void blockClicked(Block block) {
		if (block.mine) {
			lose = true;;
		} else {
			checkSurroundingBlocks(block);
			
		}
	}
	
	//**List of all blocks that have already been checked**/
	private static ArrayList<Block> checkedBoxes = new ArrayList<>();
	
	/**A recursive algorithm to check the blocks around the input**/
	private static void checkSurroundingBlocks(Block block) {
		block.visible = true;
		checkedBoxes.add(block);
		
		int col = block.xPos;
		int row = block.yPos;

		if (!block.rightEdgePiece) {
			if (!block.topEdgePiece) {
				if (!checkedBoxes.contains(board[row - 1][col + 1])) {
					if ((board[row - 1][col + 1].numOfMines == 0 || touchEmptyBlock(board[row - 1][col + 1])) && !board[row - 1][col + 1].mine) {
						checkSurroundingBlocks(board[row - 1][col + 1]);
					}	
				}
			}
			
			
		}
		
		if (!block.leftEdgePiece) {
			if (!block.topEdgePiece) {
				if (!checkedBoxes.contains(board[row - 1][col - 1])) {
					if ((board[row - 1][col - 1].numOfMines == 0 || touchEmptyBlock(board[row - 1][col - 1])) && !board[row - 1][col - 1].mine) {
						checkSurroundingBlocks(board[row - 1][col - 1]);
					}	
				}
			}
		}
		
		if(!block.rightEdgePiece) {
			if (!block.bottomEdgePiece) {
				if (!checkedBoxes.contains(board[row + 1][col + 1])) {
					if ((board[row + 1][col + 1].numOfMines == 0 || touchEmptyBlock(board[row + 1][col + 1])) && !board[row + 1][col + 1].mine) {
						checkSurroundingBlocks(board[row + 1][col + 1]);
					}
				}
			}
		}
		
		if(!block.leftEdgePiece) {
			if (!block.bottomEdgePiece) {
				if (!checkedBoxes.contains(board[row + 1][col - 1])) {
					if ((board[row + 1][col - 1].numOfMines == 0 || touchEmptyBlock(board[row + 1][col - 1])) && !board[row + 1][col - 1].mine) {
						checkSurroundingBlocks(board[row + 1][col - 1]);
					}
				}
			}
			
		}
		
		//
		if (!block.rightEdgePiece) {
			if (!checkedBoxes.contains(board[row][col + 1])) {
				if ((board[row][col + 1].numOfMines == 0 || touchEmptyBlock(board[row][col + 1])) && !board[row][col + 1].mine) {
					checkSurroundingBlocks(board[row][col + 1]);
				}	
			}
			
		}
		
		if (!block.leftEdgePiece) {
			if (!checkedBoxes.contains(board[row][col - 1])) {
				if ((board[row][col - 1].numOfMines == 0 || touchEmptyBlock(board[row][col - 1])) && !board[row][col - 1].mine) {
					checkSurroundingBlocks(board[row][col - 1]);
				}	
			}
			
		}
		
		if(!block.bottomEdgePiece) {
			if (!checkedBoxes.contains(board[row + 1][col])) {
				if ((board[row + 1][col].numOfMines == 0 || touchEmptyBlock(board[row + 1][col])) && !board[row + 1][col].mine) {
					checkSurroundingBlocks(board[row + 1][col]);
				}
			}
			
		}
		
		if(!block.topEdgePiece) {
			if (!checkedBoxes.contains(board[row - 1][col])) {
				if ((board[row - 1][col].numOfMines == 0 || touchEmptyBlock(board[row - 1][col])) && !board[row - 1][col].mine) {
					checkSurroundingBlocks(board[row - 1][col]);
				}
			}
			
		}
		
	}
	
	/**Checking whether not a block touches an empty block**/
	private static boolean touchEmptyBlock(Block block) {
		int col = block.xPos;
		int row = block.yPos;
		
		if(!block.rightEdgePiece) {
			if (board[row][col + 1].numOfMines == 0  && !board[row][col + 1].mine) {
				return true;
			}
		}
		
		if(!block.leftEdgePiece) {
			if (board[row][col - 1].numOfMines == 0 && !board[row][col - 1].mine) {
				return true;
			}	
		}
				
		if(!block.bottomEdgePiece) {
			if (board[row + 1][col].numOfMines == 0 && !board[row + 1][col].mine) {
				return true;
			}	
		}
			
		if(!block.topEdgePiece) {
			if (board[row - 1][col].numOfMines == 0 && !board[row + 1][col].mine) {
				return true;
			}
		}
		
		if (!block.rightEdgePiece) {
			if (!block.topEdgePiece) {
				if (board[row - 1][col + 1].numOfMines == 0 && !board[row - 1][col + 1].mine) {
					return true;
				}	
			}
		}
		
		if (!block.leftEdgePiece) {
			if (!block.topEdgePiece) {
				if (board[row - 1][col - 1].numOfMines == 0 && !board[row - 1][col - 1].mine) {
					return true;
				}	
			}
		}
		
		if(!block.rightEdgePiece) {
			if (!block.bottomEdgePiece) {
				if (board[row + 1][col + 1].numOfMines == 0 && !board[row + 1][col + 1].mine) {
					return true;
				}
			}
		}
		
		if(!block.leftEdgePiece) {
			if (!block.bottomEdgePiece) {
				if (board[row + 1][col - 1].numOfMines == 0 && !board[row + 1][col - 1].mine) {
					return true;
				}
			}
		}
			
		return false;
	}
	
	

}
