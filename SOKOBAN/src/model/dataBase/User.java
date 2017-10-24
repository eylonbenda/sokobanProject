package model.dataBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author eylon ben david
 *
 */

@Entity(name="Users")
public class User implements Serializable {
	
	@Id
	@Column(name="UserName",nullable=false)
	private String userName;
	
	@Column(name="FirstName")
	private String fName;
	@Column(name="LastName")
	private String lName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Record> recordlist=new ArrayList<Record>();
	
	public User(String fname,String lname,String userName) {
		
		this.fName=fname;
		this.lName=lname;
		this.userName=userName;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Record> getRecordlist() {
		return recordlist;
	}

	public void setRecordlist(List<Record> recordlist) {
		this.recordlist = recordlist;
	}


	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	@Override
	public String toString() {
		return this.userName;
	}
	
	
	

}
