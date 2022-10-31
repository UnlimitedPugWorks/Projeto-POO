package sth.core;

public class Employee extends Person implements java.io.Serializable{
	public Employee(Integer id, int phoneNumber, String name){
		super(id, phoneNumber, name);
	}

	private String showHeader(){
		return "FUNCIONÁRIO|" + this.getId() +"|" +this.getPhoneNumber() + "|" + this.getName();
	}

	public String show(){
		return showHeader();
	}
}