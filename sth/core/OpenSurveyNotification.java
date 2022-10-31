package sth.core;


public class OpenSurveyNotification extends Notification{
	public OpenSurveyNotification(Project p){
		//String message = "Pode preencher inquérito do projecto" + p.getName() + "da disciplina" + p.getDiscipline().getName();
		super("Pode preencher inquérito do projecto " + p.getName() + " da disciplina " + p.getDiscipline().getName());
	}
}