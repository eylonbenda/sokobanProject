package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.object_data.Box;
import model.object_data.Player;
import model.object_data.Position;
import model.object_data.Square;
import model.object_data.SquareTarget;
import model.object_data.Wall;

/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class MyTextLevelLoader implements LevelLoader {
	
	

	@Override
	public Level loadlevel(InputStream in) throws IOException {
		
		int rows=0;
		int colums=0;
		//String line;
		
		
		
		BufferedReader input=new BufferedReader(new InputStreamReader(in));
		ArrayList<ArrayList<Square>> maplevel=new ArrayList<>();
		maplevel.add(new ArrayList<Square>());
		
		Level lvl=new Level(maplevel);
		int count=lvl.getNumoftargets();
		
		int c;
		//while((line=input.readLine())!=null){
		while((c=input.read())!=-1){
				
			char character=(char)c;
			switch(character){
			
			case '#':
				maplevel.get(rows).add(colums, new Square(new Position(rows, colums), new Wall()));
				colums++;
				break;
			case '@':
				maplevel.get(rows).add(colums, new Square(new Position(rows, colums), new Box()));
				colums++;
				break;
			case 'A':
				maplevel.get(rows).add(colums, new Square(new Position(rows, colums), new Player()));
				colums++;
				break;
			case 'o':
				maplevel.get(rows).add(colums, new SquareTarget(new Position(rows, colums)));
				colums++;
				count++;
				break;
			case ' ':
				maplevel.get(rows).add(colums, new Square(new Position(rows, colums)));
				colums++;
				break;
			case '\n':
				rows++;
				colums=0;
				maplevel.add(new ArrayList<Square>());
				break;
			}
				
			
		}
		
		lvl.setNumoftargets(count);
		
		return lvl;
	}
}




