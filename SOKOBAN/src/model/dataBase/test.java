package model.dataBase;

import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dbmaneger db=new Dbmaneger();
		
		List<Record> list=null;
		
		list=db.selectAllRecords();
		System.out.println(list);
		
		list=db.displayAllRecordsAccordingUserName("eylon ben david");
		System.out.println(list);;

	}

}
