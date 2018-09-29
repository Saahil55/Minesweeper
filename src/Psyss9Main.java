import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Psyss9Main extends MouseAdapter {
	final static int width = 10;  // Width of the Board
	final static int height = 10;  // Height of the Board
	final static int num_mines = 9; // Number of mines on Board
	final static int pixelheight = 100;
	final static int pixelwidth = 100;
	
	JFrame guiFrame = new JFrame();
	Board board = new Board();

	public static void main(String[] args) {
		new Psyss9Main();
	}
	
	public Psyss9Main(){
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiFrame.setTitle("Minesweeper | Saahil Shah");
		guiFrame.setLayout(new BorderLayout(10,10)); 
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(height,width));
		
		for(int i=0; i< height; i++) {
			for(int j=0; j< width; j++) {
				board.storeButton(j, i,new BoardSquareButton(j,i,pixelheight,pixelwidth));
				board.getButton(j, i).addMouseListener(this);
				panel1.add(board.getButton(j, i));
			}
		}
		
		board.initialiseAll();
		board.createMines(num_mines);
		
		guiFrame.add(panel1,BorderLayout.CENTER);
		guiFrame.pack();
		guiFrame.setVisible(true);
	}
	
	public void mousePressed(MouseEvent e) {
		for(int i=0; i< height; i++) {
			for(int j=0; j< width; j++) {
				BoardSquareButton button1 = board.getButton(j, i);
				if (e.getSource() == button1) {
					if (SwingUtilities.isLeftMouseButton(e)){
						this.leftclick(button1,j,i);
					}
				
					if (SwingUtilities.isRightMouseButton(e)) {
						this.rightclick(button1);
					}
				}
			}
		}
	}

	public void leftclick(BoardSquareButton button1, int x, int y) {
		if (button1.mine == true) {
			board.finished();
			guiFrame.repaint();
			JFrame lost = new JFrame();
			JOptionPane.showMessageDialog(lost, "You Lost");
			board.initialiseAll();
			guiFrame.repaint();
			board.createMines(num_mines);
		}
		else {
			int nearbyMines = board.countSurrounding(x, y);
			
			button1.greenNum(nearbyMines);
			button1.investigated =true;
			guiFrame.repaint();
			
			if (nearbyMines == 0) {
    			board.itterate(x,y);
    			guiFrame.repaint();
			}
			
			if(board.hasWon() == true) {
				board.finished();
				guiFrame.repaint();
				JFrame won = new JFrame();
				JOptionPane.showMessageDialog(won, "You Won!");
				board.initialiseAll();
				guiFrame.repaint();
				board.createMines(num_mines);
			}
		}	
	}	
		
	public void rightclick(BoardSquareButton button1) {
		
		if(button1.potentialmine == true) {
			button1.initialise();
			guiFrame.repaint();
		}
		else {
			button1.potentialmine = true;
			button1.redcolour();
			guiFrame.repaint();
		}
	}
	
}