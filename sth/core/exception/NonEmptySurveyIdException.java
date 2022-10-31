package sth.core.exception;

import sth.core.Project;

public class NonEmptySurveyIdException extends SurveyException{

	public NonEmptySurveyIdException(Project p){
		super(p);
	}
}