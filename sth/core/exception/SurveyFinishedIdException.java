package sth.core.exception;

import sth.core.Project;

public class SurveyFinishedIdException extends SurveyException{

	public SurveyFinishedIdException(Project p){
		super(p);
	}
}