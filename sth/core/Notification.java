package sth.core;

public abstract class Notification implements java.io.Serializable{
	private String _message;

	public Notification(String message){
		_message = message;
	}

	public String toString(){
		return _message;
	}

}