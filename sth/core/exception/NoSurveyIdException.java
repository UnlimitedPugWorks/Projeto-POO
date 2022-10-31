package sth.core.exception;

import sth.core.Project;

public class NoSurveyIdException extends SurveyException{

	public NoSurveyIdException(Project p){
		super(p);
	}
}