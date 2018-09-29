import java.util.Random;

public class Board {
	final static int width = Psyss9Main.width;  
	final static int height = Psyss9Main.height; 
	BoardSquareButton [] [] buttonArray = new BoardSquareButton [height] [width];
	
	public BoardSquareButton getButton(int x, int y) {
		BoardSquareButton button = buttonArray [y][x];
		return button;
	}
	
	public void storeButton( int x, int y, BoardSquareButton button1){
		buttonArray [y] [x] = button1;
	}
	
	public void initialiseAll() {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				buttonArray[i][j].initialise();
			}
		}
	}
	
	public void createMines(int num_mines){
		Random rand = new Random();
		
		for(int i=0; i <num_mines;i++) {
			int rand_y =rand.nextInt((height));
			int rand_x =rand.nextInt((width));
			BoardSquareButton button= getButton(rand_x,rand_y);
			
			while(button.mine != false){	
				rand_y =rand.nextInt((height));
				rand_x =rand.nextInt((width));
				button = getButton(rand_x,rand_y);
			}
			
			button.mine = true;
		}
	}
	
	public void finished() {
		
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				BoardSquareButton button1 = buttonArray [i] [j];
						
				if(button1.mine == true) {
					button1.redX();
				}
				
				if(button1.potentialmine == false && button1.mine == true) {
					button1.orangeX();
				}
				
				if (button1.mine != true) {
					int nearbyMines = countSurrounding(j,i);
					button1.greenNum(nearbyMines);
				}
			}
		}
	}
		
	public boolean hasWon() {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				BoardSquareButton button1 = buttonArray [i] [j];
				
				if(button1.mine == false && button1.investigated == false) {
					return false; 
				}
			}
		}	
		return true;
	}
	
	public int countSurrounding(int x, int y) {

		int lowerX = x - 1;
        lowerX = lowerX < 0 ? 0 : lowerX;
        int highX = x + 2;
        highX = highX > (width) ? (width) : highX;

        int lowerY = y - 1;
        lowerY = lowerY < 0 ? 0 : lowerY;
        int highY = y + 2;
        highY = highY > (height) ? (height) : highY;

        int count = 0;
        for (int i = lowerX; i < highX; i++) {
            for (int j = lowerY; j < highY; j++) {
                if (i != x || j != y) {
                	BoardSquareButton button = buttonArray [j] [i];
                    if (button.mine == true) {
                        count++;
                    }
                }
            }
        }
        return count;
	}
	
	
	public void itterate(int x, int y) {
		int lowerX = x - 1;
		lowerX = lowerX < 0 ? 0 : lowerX;
		int highX = x + 2;
		highX = highX > (width) ? (width) : highX;

		int lowerY = y - 1;
		lowerY = lowerY < 0 ? 0 : lowerY;
		int highY = y + 2;
		highY = highY > (height) ? (height) : highY;

		for (int i = lowerX; i < highX; i++) {
			for (int j = lowerY; j < highY; j++) {
				BoardSquareButton button2 =getButton(i,j);
				if (countSurrounding(i,j)== 0 && button2.investigated == false && getButton(i,j) != getButton(x,y)) {
					itterate(i,j);
				}
				
				int nearbyMines = countSurrounding(i,j);
				button2.greenNum(nearbyMines);
				button2.investigated = true;
				
			}
		}	
	}
}
     
