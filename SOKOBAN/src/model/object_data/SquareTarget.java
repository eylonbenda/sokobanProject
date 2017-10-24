package model.object_data;

public class SquareTarget extends Square {

	public SquareTarget(Position position) {
		super(position);

	}
	
	public SquareTarget(){
	}
	
	public String toString(){
		if(this.getGameObject()!=null)
			return this.getGameObject().toString();
		return "o";
	}


}
