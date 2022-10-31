package sth.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import sth.core.exception.SurveyException;
import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.NoSurveyIdException;

public class Survey extends Subject implements java.io.Serializable{
	private SurveyState _state;
	private Project _project;
	private ArrayList<Answer> _answers;
	private LinkedList<Student> _students;
	Survey(Project p, HashSet<Observer> subscribers){
		super(subscribers);
		_project = p;
		_answers = new ArrayList<Answer>();
		_state = new CreatedSurvey();
	}
	
	void open() throws OpeningSurveyIdException{
		boolean valid =_state.open(this);
		if(valid == false){
			throw new OpeningSurveyIdException(_project);
		}
	}

	void close() throws ClosingSurveyIdException{
		boolean valid = _state.close(this);
		if(valid == false){
			throw new ClosingSurveyIdException(_project);
		}
	}

	void finish() throws FinishingSurveyIdException{
		boolean valid = _state.finish(this);
		if(valid == false){
			throw new FinishingSurveyIdException(_project);
		}
	}

	ArrayList<String> showResultsT(){
		return _state.showResultsT(this);
	}

	ArrayList<String> showResultsS(){
		return _state.showResultsS(this);
	}

	ArrayList<String> showResultsR(){
		return _state.showResultsR(this);
	}

	void setState(SurveyState state){
		_state = state;
	}

	Project getProject(){
		return _project;
	}

	int getAverage(){
		int average=0;
		for(Answer a : _answers){
			average+= a.getHours();
		}
		return (_answers.size() == 0)? average : average/_answers.size();
	}

	int getMax(){
		int max = 0;
		for(Answer a : _answers){
			if(max < a.getHours()){
				max = a.getHours();
			}
		}
		return max;
	}

	int getMin(){
		int min = (_answers.size() == 0)? 0 :_answers.get(0).getHours();
		for(Answer a : _answers){
			if(min > a.getHours()){
				min = a.getHours();
			}
		}
		return min;
	}

	int getNumOfAnswers(){
		return _answers.size();
	}

	void addPool(LinkedList<Student> students){
		_students = students;
	}

	SurveyState getState(){
		return _state;
	}

	void answerSurvey(Answer a)throws NoSurveyIdException{
		boolean valid =	_state.answerSurvey();
		if(valid){
			_answers.add(a);
		}
		else{
			throw new NoSurveyIdException(_project);
		}
	}

	void sendOpenMessages(){
		update(new OpenSurveyNotification(_project));
 	}

 	void sendCloseMessages(){
 		update(new ClosedSurveyNotification(_project));
 	}

}