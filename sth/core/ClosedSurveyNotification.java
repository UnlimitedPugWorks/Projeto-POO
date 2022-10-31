package sth.core;

public class ClosedSurveyNotification extends Notification{
	public ClosedSurveyNotification(Project p){
		super("Resultados do inquérito do projecto " + p.getName() + " da disciplina " + p.getDiscipline().getName());
	} 
}