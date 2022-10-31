package sth.core;

import java.util.ArrayList;
public class FinishedSurvey implements SurveyState,java.io.Serializable{
	public boolean open(Survey s){
		return false;
	}

	public boolean finish(Survey s){
		return true;
	}

	public boolean close(Survey s){
		return false;
	}

    public ArrayList<String> showResults(Survey s){
    	ArrayList<String> returnList = new ArrayList<String>();
		String str = s.getProject().getDiscipline().getName() + " - " + s.getProject().getName();
		returnList.add(str);
		return returnList;
    }


	public ArrayList<String> showResultsT(Survey s){
		ArrayList<String> returnList = showResults(s);
		String subs = " * Número de submissões: "+ s.getProject().getNumOfSubs();
		returnList.add(subs);
		String answers = " * Número de respostas: " + s.getNumOfAnswers();
		returnList.add(answers);
		String data = " * Tempos de resolução (horas) (mínimo, médio, máximo): "+s.getMin() + ", " +s.getAverage() + ", " + s.getMax();
		returnList.add(data);
		return returnList;
	}

	public ArrayList<String> showResultsS(Survey s){
		ArrayList<String> returnList = showResults(s);
		String answers = " * Número de respostas: " + s.getNumOfAnswers();
		returnList.add(answers);
		String average = " * Tempo médio (horas): " + s.getAverage();
		returnList.add(average);
		return returnList;
	}

	public ArrayList<String> showResultsR(Survey s){
		ArrayList<String> returnList = new ArrayList<String>();
		returnList.add(showResults(s).get(0) + " - " + s.getNumOfAnswers() + " respostas - "+ s.getAverage()+ " horas");
		return returnList;
	}

	public boolean answerSurvey(){
		return false;
	}
}