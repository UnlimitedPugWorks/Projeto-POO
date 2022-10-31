package sth.core;
import java.util.ArrayList;
public class ClosedSurvey implements SurveyState, java.io.Serializable{
	public final static String STATENAME = " (fechado)";
	public boolean open(Survey s){
		s.setState(new OpenSurvey());
		s.sendOpenMessages();
		return true;
	}

	public boolean finish(Survey s){
		s.setState(new FinishedSurvey());
		s.sendCloseMessages();
		return true;
	}

	public boolean close(Survey s){
		return true;
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