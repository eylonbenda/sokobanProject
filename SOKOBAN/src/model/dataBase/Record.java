package model.dataBase;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;


/**
 * 
 * @author eylon ben david
 *
 */
@Entity(name="Record")
public class Record implements Serializable {
	
	@Column(name="Steps")
	private int steps;
	
	@Column(name="Time")
	private float time;
	
	@Column(name="LevelName")
	private String levelName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable = false)
	private int recordId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="UserName",nullable=false)
	private User user;
	
	public Record(int steps,String levelName,float time,User user) {
		// TODO Auto-generated constructor stub
		this.levelName=levelName;
		this.steps=steps;
		this.time=time;
		this.user=user;
		
	}
	
	public Record() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public double getTime() {
		return time;
	}
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}




	public String getLevelName() {
		return levelName;
	}


	

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}



	public void setTime(float time) {
		this.time = time;
	}




	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Record [steps=" + steps + ", time=" + time + ", levelName=" + levelName + ", recordId=" + recordId
				+ ", user=" + user.getfName()+ " " + user.getlName() + "]";
	}
	
	
}
