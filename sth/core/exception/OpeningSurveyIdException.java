package sth.core.exception;

import sth.core.Project;



public class OpeningSurveyIdException extends SurveyException{

	public OpeningSurveyIdException(Project p){
		super(p);
	}
}