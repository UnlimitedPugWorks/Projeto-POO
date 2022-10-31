package sth.core.exception;

import sth.core.Project;



public class DuplicateSurveyIdException extends SurveyException{

	public DuplicateSurveyIdException(Project p){
		super(p);
	}
}