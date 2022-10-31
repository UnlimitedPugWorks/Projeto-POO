package sth.core.exception;

import sth.core.Project;


public class SurveyException extends Exception{
	private Project _project;

	public SurveyException(Project project){
		_project = project;
	}

	public Project getProject(){
		return _project;
	}
}