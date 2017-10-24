package model.data;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.object_data.Square;

public class MyTextLevelSaver implements LevelSaver {

	@Override
	public void save(String filepath, Level level) throws FileNotFoundException {
		// TODO Auto-generated method stub
		PrintWriter bf=new PrintWriter(new FileOutputStream(filepath));
		
		
		for(ArrayList<Square> squareslist:level.getLevelmap()){
			
			for(Square square:squareslist){
				bf.print(square.toString());
			}
			bf.println();
		}
		
		bf.close();
		
	}

}
