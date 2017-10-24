package model.policy;

import model.data.Level;
import model.object_data.GameObject;
import model.object_data.Square;

/**
 * 
 * @author eylon ben david
 *
 */
public class MySokobanPolicy implements Policy {
	
	private Level lvl;
	private Square playersquare;
	private int x;
	private int y;
	private boolean flag;
	
	public MySokobanPolicy(Level lvl) {
		this.lvl=lvl;
		
		
		playersquare=lvl.positionPlayer();
		  x=playersquare.getPosition().getX();
		  y=playersquare.getPosition().getY();
		  
		  lvl.addTargets();
		  
	}
	
	
	 
	 public void moveUp(){
		 
		if(isMoveAbleUp(lvl)){
				Square upsquare=lvl.getLevelmap().get(x-1).get(y);
				if((upsquare.toString()==" ")||(upsquare.toString()=="o")){
					swapSquare1(playersquare, upsquare);
			     	playersquare=lvl.positionPlayer();
			    	x=playersquare.getPosition().getX();
			    	y=playersquare.getPosition().getY();
			    	lvl.countSteps();
				}
					
				else{
					Square upboxsquare=lvl.getLevelmap().get(x-2).get(y);
					if(upboxsquare.toString()==" "){
						
						swapSquare1(upboxsquare, upsquare);
						swapSquare1(upsquare, playersquare);
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
						
						
					}
				    
					if(upboxsquare.toString()=="o"){
						
						swapSquare1(upboxsquare, upsquare);
						swapSquare1(upsquare, playersquare);
							
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
					}	
				}
		}
		
		
	 }
			
	 public  void moveDown(){
		 
		 if(isMoveAbleDowm(lvl)){
				Square downsquare=lvl.getLevelmap().get(x+1).get(y);
				if((downsquare.toString()==" ")||(downsquare.toString()=="o")){
					
					swapSquare1(playersquare, downsquare);
				     playersquare=lvl.positionPlayer();
				    x=playersquare.getPosition().getX();
		        	y=playersquare.getPosition().getY();
		        	lvl.countSteps();
				}
				else{
					Square downboxsquare=lvl.getLevelmap().get(x+2).get(y);
					if(downboxsquare.toString()==" "){
						
						swapSquare1(downboxsquare, downsquare);
						swapSquare1(downsquare, playersquare);
						
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
						
					}
					if(downboxsquare.toString()=="o"){
						
						swapSquare1(downboxsquare, downsquare);
						swapSquare1(downsquare, playersquare);
		
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
					}	
				}
		 }
		
	}
	 
	 public void moveRight(){
		 
		 if(isMoveAbleRight(lvl)){
				Square rightsquare=lvl.getLevelmap().get(x).get(y+1);
				if((rightsquare.toString()==" ")||(rightsquare.toString()=="o")){
					swapSquare1(rightsquare, playersquare);
					playersquare=lvl.positionPlayer();
					x=playersquare.getPosition().getX();
					y=playersquare.getPosition().getY();
					lvl.countSteps();
				}
				else{
					Square rightboxsquare=lvl.getLevelmap().get(x).get(y+2);
					if(rightboxsquare.toString()==" "){
						swapSquare1(rightboxsquare, rightsquare);
						swapSquare1(rightsquare, playersquare);
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
					}
					if(rightboxsquare.toString()=="o"){
						swapSquare1(rightboxsquare, rightsquare);
						swapSquare1(rightsquare, playersquare);
						
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
					}
				}
		 }
		 
		
	 }
	 
	 public void moveLeft(){
				
		 if(isMoveAbleLeft(lvl)){
				Square leftsquare=lvl.getLevelmap().get(x).get(y-1);
				if((leftsquare.toString()==" ")||(leftsquare.toString()=="o")){
					swapSquare1(leftsquare, playersquare);
					playersquare=lvl.positionPlayer();
					x=playersquare.getPosition().getX();
					y=playersquare.getPosition().getY();
					lvl.countSteps();
				}
				else{
					Square leftboxsquare=lvl.getLevelmap().get(x).get(y-2);
					if(leftboxsquare.toString()==" "){
						swapSquare1(leftboxsquare, leftsquare);
						swapSquare1(leftsquare, playersquare);
						
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
					}
					if(leftboxsquare.toString()=="o"){
						swapSquare1(leftboxsquare, leftsquare);
						swapSquare1(leftsquare, playersquare);
						
						playersquare=lvl.positionPlayer();
						x=playersquare.getPosition().getX();
						y=playersquare.getPosition().getY();
						lvl.countSteps();
					}
				}
			}
		
				
	 }	

	public boolean isMoveAbleUp(Level lvl){
		
		if(lvl.getLevelmap().get(x-1).get(y).toString()=="#")
			return false;
		
		if(lvl.getLevelmap().get(x-1).get(y).toString()=="@"){
			if(lvl.getLevelmap().get(x-2).get(y).toString()=="#")
				return false;
			if(lvl.getLevelmap().get(x-2).get(y).toString()=="@")
				return false;
		}
			return true;
	}
	
	 public boolean isMoveAbleDowm(Level lvl){
	
		if(lvl.getLevelmap().get(x+1).get(y).toString()=="#")
			return false;
		
		if(lvl.getLevelmap().get(x+1).get(y).toString()=="@"){
			if(lvl.getLevelmap().get(x+2).get(y).toString()=="#")
				return false;
			if(lvl.getLevelmap().get(x+2).get(y).toString()=="@")
				return false;
		}
			return true;
	 }

	 
	 public boolean isMoveAbleLeft(Level lvl){
		
		if(lvl.getLevelmap().get(x).get(y-1).toString()=="#")
			return false;
		
		if(lvl.getLevelmap().get(x).get(y-1).toString()=="@"){
			if(lvl.getLevelmap().get(x).get(y-2).toString()=="#")
				return false;
			if(lvl.getLevelmap().get(x).get(y-2).toString()=="@")
				return false;
		}
			return true;
	 
	 }
	 
	 public boolean isMoveAbleRight(Level lvl){
		
		if(lvl.getLevelmap().get(x).get(y+1).toString()=="#")
			return false;
		
		if(lvl.getLevelmap().get(x).get(y+1).toString()=="@"){
			if(lvl.getLevelmap().get(x).get(y+2).toString()=="#")
				return false;
			if(lvl.getLevelmap().get(x).get(y+2).toString()=="@")
				return false;
		}
			return true;
	 }
	 
	 
	 public void swapSquare(GameObject g1,GameObject g2){
		  GameObject temp=g1;
		  g1=g2;
		  g2=temp;
	  }
	 
	 public void swapSquare1(Square s1,Square s2){
		 
		 GameObject s1o=s1.getGameObject();
		 GameObject s20=s2.getGameObject();
		 s1.setGameObject(s20);
		 s2.setGameObject(s1o);
	 }
	 
	 public boolean cheackAllBoxOnTargets(int boxontargets,Level lvl){
		 if(boxontargets==lvl.getNumoftargets())
			 return true;
		 return false;
		 
	 }

	
	 public boolean isBoxOnTarget(Square s){
		 if(s.toString()=="@")
			 return true;
		 return false;
	 }
	 
	 
	 
}
