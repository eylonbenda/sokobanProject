package searchLib;



public class Maze {
	
	private char[][] board;
	
	public Position playerPos(){
		
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++){
				if(board[i][j] == 'x')
					return new Position(i,j);
			}
		return null;
		
	}
	
	public Position goalPosition(){
		
		
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++){
				if(board[i][j] == 'o')
					return new Position(i,j);
			}
		return null;
		
		
	}
	
	
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public boolean isEmptyPosition(Position pos) {
		
		if(pos.getCol()>=0 && pos.getRow()>=0 && pos.getCol()<board[0].length && pos.getRow()<board.length){
		if(board[pos.getRow()][pos.getCol()] == ' ' || board[pos.getRow()][pos.getCol()] == 'o')
		{   
			System.out.println(pos.row + " " + pos.col);
			return true;
		}
	 }
		return false;
	}
	
	
	

}
