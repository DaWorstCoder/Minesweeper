package Minesweeper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**All the actually graphical stuff**/
public class Paint {
	
	BufferedImage flagPic; 
	BufferedImage winScreen; //TODO: The win screen kinda sucks
	BufferedImage mine;
	
	BufferedImage one;
	BufferedImage two;
	BufferedImage three;
	BufferedImage four;
	BufferedImage five;
	BufferedImage six;
	
	void board(Graphics g) {
		try {
			one = ImageIO.read(new File("/Users/23005744/Downloads/one.png"));
			one = resize(one, (Main.width / Game.board.length), (Main.height / Game.board[0].length));
			two = ImageIO.read(new File("/Users/23005744/Downloads/two.png"));
			two = resize(two, (Main.width / Game.board.length), (Main.height / Game.board[0].length));
			three = ImageIO.read(new File("/Users/23005744/Downloads/three.png"));
			three = resize(three, (Main.width / Game.board.length), (Main.height / Game.board[0].length));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int row = 0; row < Game.board.length; row++) {
			for (int col = 0; col < Game.board[0].length; col++) {
				//Use to test where mines are located
//				if (Game.board[row][col].mine) {
//					g.setColor(new Color (207, 157, 157));
//				} else {
//					g.setColor(new Color(20, 20, 20));
//				}
				
				if (Game.board[row][col].visible) {
					g.setColor(new Color (40, 40, 40));
					g.fillRect(col * (Main.width / Game.board.length), row * (Main.height / Game.board[0].length), (Main.width / Game.board.length), (Main.height / Game.board[0].length));
					g.setColor(new Color (200, 200, 200));
					g.drawRect(col * (Main.width / Game.board.length), row * (Main.height / Game.board[0].length), (Main.width / Game.board.length), (Main.height / Game.board[0].length));
					
					try {
						/*Was contemplating the computational power required to read resize 4, 5, and 6 images every iteration when
						they wouldn't really have any widespread use so I put them in the switch case. It might make more sense to 
						initalize all the pictures in main, including 1, 2, and 3 but I'm lazy.*/
						switch (Game.board[row][col].numOfMines) {
							case 0:
								break;
							case 1:
								g.drawImage(one, (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null); 
								break;
							case 2:
								g.drawImage(two, (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null); 
								break;
							case 3:
								g.drawImage(three, (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null); 
								break;	
							case 4: 
								four = ImageIO.read(new File("/Users/23005744/Downloads/four.png"));
								four = resize(four, (Main.width / Game.board.length), (Main.height / Game.board[0].length));
								g.drawImage(four, (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null);
								break;
							case 5: 
								five = ImageIO.read(new File("/Users/23005744/Downloads/five.png"));
								five = resize(five, (Main.width / Game.board.length), (Main.height / Game.board[0].length));
								g.drawImage(five, (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null);
								break;
							case 6: 
								six = ImageIO.read(new File("/Users/23005744/Downloads/six.png"));
								six = resize(six, (Main.width / Game.board.length), (Main.height / Game.board[0].length));
								g.drawImage(six, (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null);
								break;
							default:
								g.setColor(new Color (200, 200, 200));
								g.drawString("" + Game.board[row][col].numOfMines, col * (Main.width / Game.board.length), (row + 1) * (Main.height / Game.board[0].length));
								break;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				} else {
					if (Game.lose) {
						g.setColor((Color.red));
					} else {
						g.setColor(new Color(20, 20, 20));
					}
					g.fillRect(col * (Main.width / Game.board.length), row * (Main.height / Game.board[0].length), (Main.width / Game.board.length), (Main.height / Game.board[0].length));
					g.setColor(new Color (200, 200, 200));
					g.drawRect(col * (Main.width / Game.board.length), row * (Main.height / Game.board[0].length), (Main.width / Game.board.length), (Main.height / Game.board[0].length));
					
					
					if (Game.board[row][col].flagged) {
						
						try {
							flagPic = ImageIO.read(new File("/Users/23005744/Downloads/flag.png"));
							g.drawImage(resize(flagPic, (Main.width / Game.board.length), (Main.height / Game.board[0].length)), (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null); 
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		}
	}
	
	void win(Graphics g) {
		try {
			winScreen = ImageIO.read(new File("/Users/23005744/Downloads/pixel-art-8bit-you-win-text-with-winner-golden-cup-vector-33826631-removebg-preview.png"));
			g.drawImage(winScreen, 0, 0, null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void lose(Graphics g) {
		try {
			mine = ImageIO.read(new File("/Users/23005744/Downloads/mine.png"));
			mine = resize(mine, (Main.width / Game.board.length), (Main.height / Game.board[0].length));
			
			for(int row = 0; row < Game.board.length; row++) {
				for (int col = 0; col < Game.board[0].length; col++) {
					if (Game.board[row][col].mine) {
						if (Game.board[row][col].flagged) {
							Game.board[row][col].flagged = false;
						}
						
						g.drawImage(mine, (int) (col * (Main.width / Game.board.length)), (int) ((row) * (Main.height / Game.board[0].length)), null); 
						
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Resizes flag images **/
	private BufferedImage resize(BufferedImage img, int newW, int newH) {  
	    int w = img.getWidth();  
	    int h = img.getHeight();  
	    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
	    Graphics2D g = dimg.createGraphics();  
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	    g.dispose();  
	    return dimg;  
	}  
	
	
	public Paint(Graphics g) {
		board(g);
		if (Game.Win()) {
			win(g);
		} 
		if (Game.lose) {
			lose(g);
		}
	}
}
