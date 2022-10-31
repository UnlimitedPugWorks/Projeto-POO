package sth.core.exception;

import sth.core.Project;

public class FinishingSurveyIdException extends SurveyException{

	public FinishingSurveyIdException(Project p){
		super(p);
	}
}