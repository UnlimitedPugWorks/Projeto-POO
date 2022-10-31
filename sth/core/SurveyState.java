package sth.core;

import java.util.ArrayList;
import sth.core.exception.SurveyException;

public interface SurveyState{
	public boolean open(Survey s);
	public boolean finish(Survey s);
	public boolean close(Survey s);
	public ArrayList<String> showResults(Survey s);
	public ArrayList<String> showResultsT(Survey s);
	public ArrayList<String> showResultsS(Survey s);
	public ArrayList<String> showResultsR(Survey s);
	public boolean answerSurvey();
}