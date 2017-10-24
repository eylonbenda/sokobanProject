package model.data;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.object_data.Box;
import model.object_data.Player;
import model.object_data.Position;
import model.object_data.Square;
import model.object_data.SquareTarget;

/* contain data of level object */

/**
 * <p> SokoBan Level class</p>
 * @author eylon ben david
 * 
 *
 */

public class Level implements Serializable {

	private ArrayList<ArrayList<Square>> levelmap = new ArrayList<>();
	private Square player;
	private int levelId;
	private String levelName;
	private ArrayList<SquareTarget> st = new ArrayList<>();
	private int numoftargets = 0;
	private boolean isWin = false;
	private int countSteps = 0;
	private ArrayList<Square> listBox; // list of boxes
	
	

	// methods
	public Level(ArrayList<ArrayList<Square>> levelmap) {
		this.levelmap = levelmap;
		listBox = new ArrayList<>();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public Level(Level level){
		
		
		for (int i = 0; i < level.getLevelmap().size(); i++) {
			
			this.levelmap.add(new ArrayList<Square>());
			
			for (int j = 0; j < level.getLevelmap().get(i).size(); j++) {

				Square square = level.getLevelmap().get(i).get(j);
				this.levelmap.get(i).add(j, new Square(square));
			}

		}
		
		
		//this.levelmap = (ArrayList<ArrayList<Square>>) level.getLevelmap().clone();
		this.setListBox(level.getListBox());
		this.setSt(level.getSt());
	}
	
	public Level() {
		
		this.addBoxesToArray();
		this.addTargets();
		
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public ArrayList<ArrayList<Square>> getLevelmap() {
		return levelmap;
	}

	public void setLevelmap(ArrayList<ArrayList<Square>> levelmap) {
		this.levelmap = levelmap;
	}

	public String toString() {

		String result = "";
		for (int i = 0; i < levelmap.size(); i++) {
			for (int j = 0; j < levelmap.get(i).size(); j++) {
				result += levelmap.get(i).get(j);
			}
			result += "\n";
		}
		return result;
	}

	public Square getPlayer() {
		return player;
	}

	public void setPlayer(Square player) {
		this.player = player;
	}

	public Square positionPlayer() {
		for (int i = 0; i < levelmap.size(); i++) {
			for (int j = 0; j < levelmap.get(i).size(); j++) {
				if (levelmap.get(i).get(j).getGameObject() != null) {
					if (levelmap.get(i).get(j).getGameObject().getClass() == new Player().getClass()){
						player = levelmap.get(i).get(j);
						return player;
					}
				}
			}
		}
		return null;
	}

	public boolean checkIfWin() {
		for (SquareTarget l : this.st) {
			if (l.getGameObject() == null)
				return false;
			if (l.toString() != "@") {
				return false;
			}
		}
		return true;
	}

	public int getNumoftargets() {
		return numoftargets;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public void setNumoftargets(int numoftargets) {
		this.numoftargets = numoftargets;
	}

	// add square target to the list of targetSquare
	public void addTargets() {
		for (int i = 0; i < levelmap.size(); i++) {
			for (int j = 0; j < levelmap.get(i).size(); j++) {
				if (levelmap.get(i).get(j).toString() == "o")
					st.add((SquareTarget) this.levelmap.get(i).get(j));
			}
		}
	}

	public void addBoxesToArray() {

		for (int i = 0; i < levelmap.size(); i++) {
			for (int j = 0; j < levelmap.get(i).size(); j++) {
				if (levelmap.get(i).get(j).toString() == "@")
					listBox.add(levelmap.get(i).get(j));

			}
		}

	}

	public void countSteps() {

		this.countSteps++;
	}

	public int getCountSteps() {
		return countSteps;
	}

	public ArrayList<SquareTarget> getSt() {
		return st;
	}

	public void setSt(ArrayList<SquareTarget> st) {
		this.st = st;
	}

	public ArrayList<Square> getListBox() {
		return listBox;
	}

	public void setListBox(ArrayList<Square> listBox) {
		this.listBox = listBox;
	}

	
	
	
}
