package application;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.data.Level;
import model.object_data.Square;


/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class SokobanDisplayer extends Canvas {


	private Level level;
	private StringProperty wallimagename;


	public void setLevel(Level level) {
		this.level = level;
	}


	public String getWallimagename() {
		return wallimagename.get();
	}


	public void setWallimagename(String wallimagename) {
		this.wallimagename.set(wallimagename);
	}

	public void redraw(){

		ArrayList<ArrayList<Square>> board=level.getLevelmap();
		if(board!=null){

			GraphicsContext gc=getGraphicsContext2D();
			double H=this.getHeight();
			double W=this.getWidth();
			double h=H/board.size();
			double w=W/board.get(0).size();

			Image wall=null;
			Image box=null;
			Image target=null;
			Image floor=null;
			Image player=null;

			try {
				wall=new Image(new FileInputStream("./resorces/wall.jpg"));
				box=new Image(new FileInputStream("./resorces/box.jpg"));
				player=new Image(new FileInputStream("./resorces/player.jpg"));
				target=new Image(new FileInputStream("./resorces/target.jpg"));

			} catch (FileNotFoundException e) {
		
				e.printStackTrace();
			}

			for(int i=0;i<board.size();i++){
				for(int j=0;j<board.get(i).size();j++){

					if(board.get(i).get(j).toString()=="#")
						gc.drawImage(wall, j*w, i*h, w, h);
					if(board.get(i).get(j).toString()=="@")
						gc.drawImage(box, j*w, i*h, w, h);
					if(board.get(i).get(j).toString()=="o")
						gc.drawImage(target, j*w, i*h, w, h);
					if(board.get(i).get(j).toString()==" ")
					{
						gc.setFill(Color.WHITE);	
						gc.fillRect(j*w, i*h, w, h);
					}
					if(board.get(i).get(j).toString()=="A")
						gc.drawImage(player, j*w, i*h, w, h);

				}
			}
		}
	}

	public void drawLevelCompleted(){
	GraphicsContext gc=getGraphicsContext2D();
	Image win = null;
	try {
		win = new Image(new FileInputStream("./resorces/levelcom.jpg"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	gc.drawImage(win, 0, 0, this.getWidth(), this.getHeight());
	}

}
