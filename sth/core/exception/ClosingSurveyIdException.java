package sth.core.exception;

import sth.core.Project;



public class ClosingSurveyIdException extends SurveyException{

	public ClosingSurveyIdException(Project p){
		super(p);
	}
}