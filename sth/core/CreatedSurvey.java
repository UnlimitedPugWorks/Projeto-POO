package sth.core;

import java.util.ArrayList;

public class CreatedSurvey implements SurveyState, java.io.Serializable{
	private static final String STATENAME = " (por abrir)";
	public boolean open(Survey s){
		s.setState(new OpenSurvey());
		s.sendOpenMessages();
		return true;
	}

	public boolean finish(Survey s){
		return false;
	}

	public boolean close(Survey s){
		return false;
	}

	public ArrayList<String> showResults(Survey s){
		ArrayList<String> returnList = new ArrayList<String>();
		String str = s.getProject().getDiscipline().getName() + " - " + s.getProject().getName() + STATENAME;
		returnList.add(str);
		return returnList;
	}

	public ArrayList<String> showResultsT(Survey s){
		return showResults(s);
	}

	public ArrayList<String> showResultsR(Survey s){
		return showResults(s);
	}

	public ArrayList<String> showResultsS(Survey s){
		return showResults(s);
	}

	public boolean answerSurvey(){
		return false;
	}
}