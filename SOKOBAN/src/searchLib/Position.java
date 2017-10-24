package searchLib;

public class Position {
	
	int col,row;

	public Position(int x,int y) {
		row=x;
		col=y;
	}
	
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	@Override
	public boolean equals(Object obj) {
		Position p  = (Position)obj;
		if(this.row == p.row && this.col == p.col)
			return true;
		return false;
	}
	

}
