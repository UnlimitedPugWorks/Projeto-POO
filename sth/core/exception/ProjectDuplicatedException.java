package sth.core.exception;

import sth.core.Project;

public class ProjectDuplicatedException extends Exception{
	private Project _project;

	public ProjectDuplicatedException(Project project){
		_project = project;
	}

	public Project getProject(){
		return _project;
	}
}