package model.object_data;

import java.io.Serializable;

public abstract class GameObject implements Serializable {

	private int type;
	
	public GameObject(){
		
	}
	
	public abstract String toString();

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
		
	
	
	
	

}
