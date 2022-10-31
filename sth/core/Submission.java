package sth.core;

import java.util.Comparator;

public class Submission implements java.io.Serializable{
	private String _message;
	private Student _student;

	Submission(String message, Student student){
		_message = message;
		_student = student;
	}

	Integer getStudentId(){
		return _student.getId();
	}
	public String showSubmission(){
		return "* " + this.getStudentId() + " - " + _message;
	}

	static Comparator<Submission> idComparator = new Comparator<Submission>(){
    	public int compare(Submission p1, Submission p2){
      		return (int) (p1.getStudentId() - p2.getStudentId());
    	}
 	};

}