package sth.core;

public class Answer implements java.io.Serializable{
	private int _hours;
	private String _comment;

	public Answer(int hours, String comment){
		_hours = hours;
		_comment = comment;
	}

	int getHours(){
		return _hours;
	}
}