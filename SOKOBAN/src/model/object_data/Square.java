package model.object_data;

import java.io.Serializable;

public class Square implements Serializable {

	private Position position;
	private GameObject gameObject=null;
	

	public Square(Position position, GameObject gameObject) {
		this.position = position;
		this.gameObject = gameObject;
	}
	
	public Square(){
	}
	
	public Square(Square square){
		
		this.position = square.getPosition();
		this.gameObject = square.getGameObject();
	}
	
	
	public Square(Position position){
		
		this.position=position;
	}
	
	
	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	

	
	public Position getPosition() {
		return position;
	}
	
	public Position getposition2(int x,int y){
		return new Position(x,y);
		
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	
	
	public String toString(){
		if(this.getGameObject()!=null)
		return this.getGameObject().toString();
		else
			return " ";
	}


}
