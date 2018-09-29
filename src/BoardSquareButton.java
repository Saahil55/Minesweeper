import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class BoardSquareButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int xcoordinate;  // x and y coordinates of button on board
	int ycoordinate;
	boolean mine;          // Whether the button is a mine
	boolean investigated;  // Where the button has been investigated yet
	boolean potentialmine; // Where the user has suggested the button is a potential mine
	
	public BoardSquareButton(int x, int y, int pixelheight, int pixelwidth) {
		this.setPreferredSize(new Dimension(pixelheight, pixelwidth));
		this.setMinimumSize(new Dimension(60, 60));
		this.initialise();
		xcoordinate = x;
		ycoordinate = y; 
	}
	
	public void initialise() {
		this.setText("?");
		this.setFont( new Font("Ariel", Font.BOLD, 30));
		this.setBackground(Color.GRAY);
		mine = false;
		investigated = false;
		potentialmine = false;
	}

	public void redX() {
		this.setText("X"); 
	    this.setFont( new Font("Ariel", Font.BOLD, 30));
		this.setBackground(Color.RED); 
	}
	
	public void redcolour() {
		this.setText("?");
		this.setFont( new Font("Ariel", Font.BOLD, 30));
		this.setBackground(Color.RED);
	}
	
	public void greenNum(int nearbyMines) {
		this.setText(String.valueOf(nearbyMines));
		this.setFont( new Font("Ariel", Font.BOLD, 30));
		this.setBackground(Color.GREEN);
	}
	
	public void orangeX() {
		this.setText("X");
	    this.setFont( new Font("Ariel", Font.BOLD, 30));
		this.setBackground(Color.ORANGE); 
	}
}