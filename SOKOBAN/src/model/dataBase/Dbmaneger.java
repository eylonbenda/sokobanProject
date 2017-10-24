package model.dataBase;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * 
 * @author eylon ben david
 *
 */
public class Dbmaneger {
	
	private SessionFactory sessionFactory;
	//private Record record;
	
	public Dbmaneger() {
		// initialize 
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); 
		Configuration config = new Configuration();
		config.configure();
		sessionFactory = config.buildSessionFactory();
		
	}
	
	//save records to dataBase
	public void addRecord(Record record){
		
		Transaction transaction=null;
		Session session=sessionFactory.openSession();
		
		try {
			transaction = session.beginTransaction();
			session.save(record);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
	}
	
	//save the user to dataBase
	public void addUser(User user){
		
		
		Transaction transaction=null;
		Session session=sessionFactory.openSession();
		
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
	}
	
	//check if the user exists in dataBase.
	public User checkIfUserExist(String userName){
		
		Session session=sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		Query<User> query=session.createQuery("FROM Users");
		List<User> list=query.list();
		User checkUser=null;
		
		for(User user:list){
			if(userName.compareTo(user.getUserName())==0){
				checkUser=user;
				return checkUser;
			}
		}
		return checkUser;
	}

	// return all records lists from database
	public List<Record> selectAllRecords(){
		
		
		Session session=sessionFactory.openSession();
		
		
		@SuppressWarnings("unchecked")
		Query<Record> query=session.createQuery("FROM Record");
		List<Record> list=query.list();
		
		
		return list;
		
	}
	
	public List<Record> displayAllRecordsAccordingUserName(String username){

		Session session=sessionFactory.openSession();
		
		Query<Record> query=session.createQuery("FROM Record");
		List<Record> listu=query.list();
		int i=0;
		int len=listu.size();
		
		while(i<len){
			User user=listu.get(i).getUser();
			if(user.getUserName().toLowerCase().startsWith(username)==false){
				listu.remove(i);
				len--;
			}
			else
				i++;
			
		}

		session.close();
		return listu;

	}
	
	
	public List<Record> displayRecordsAccordingLevelName(String levelName){
		
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		Query<Record> query=session.createQuery("FROM Record");
		List<Record> list=query.list();
		int i=0;
		int size=list.size();
		
		while(i<size){
			Record record=list.get(i);
			System.out.println(record);
			if(record.getLevelName().toLowerCase().startsWith(levelName)==false){
				list.remove(i);
				size--;
			}
			else
				i++;	
		}
		
		session.close();
		return list;
	}
	
}
