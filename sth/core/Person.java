package sth.core;

import sth.core.exception.BadEntryException;

import java.util.Comparator;

public abstract class Person implements java.io.Serializable, Show{
	private String _name;
	private Integer _id;
	private int _phoneNumber;

	public Person(Integer id, int phoneNumber, String name){
		_name = name;
		_id = id;
		_phoneNumber = phoneNumber;
	}

	void parseContext(String context, School school) throws BadEntryException {
    	throw new BadEntryException("Should not have extra context: " + context);
  	}

	String getName(){
		return _name;
	}
	
	Integer getId(){
		return _id;
	}

	static Comparator<Person> idComparator = new Comparator<Person>(){
    	public int compare(Person p1, Person p2){
      		return (int) (p1.getId() - p2.getId());
    	}
 	};

 	static Comparator<Person> nameComparator = new Comparator<Person>(){
 		public int compare(Person p1, Person p2){
 			return p1.getName().compareTo(p2.getName());
 		}
 	};

 	void setPhoneNumber(int phoneNumber){
 		_phoneNumber = phoneNumber;
 	}

 	int getPhoneNumber(){
 		return _phoneNumber;
 	}

 	public abstract String show();
}