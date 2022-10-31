package sth.core.exception;

import sth.core.Course;


public class MaxRepresentativeException extends Exception{
	private Course _course;

	public MaxRepresentativeException(Course course){
		_course = course;
	}

	public Course getCourse(){
		return _course;
	}
}