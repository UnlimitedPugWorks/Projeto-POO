package sth.core.exception;

import sth.core.Student;


public class MaxDisciplineException extends Exception{
	private Student _student;

	public MaxDisciplineException(Student student){
		_student = student;
	}

	public Student getStudent(){
		return _student;
	}
}