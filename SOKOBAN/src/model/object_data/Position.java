package model.object_data;

import java.io.Serializable;

public class Position implements Serializable {

	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position() {

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {

		Position p = (Position) obj;
		if (this.x == p.x && this.y == p.y)
			return true;

		return false;
	}

}
