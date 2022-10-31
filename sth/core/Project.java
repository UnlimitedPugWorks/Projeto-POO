package sth.core;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.SurveyFinishedIdException;
import sth.core.exception.DuplicateSurveyIdException;
import sth.core.exception.NoSuchProjectIdException;


public class Project  implements java.io.Serializable{
	private static final long serialVersionUID = 201810051538L;
	private String _name;
	private boolean _closed;
	private Survey _survey;
	private Discpiline _discpiline;
	private HashMap<Student, Submission> _submissions;
	private ArrayList<Student> _hasAnswered;

	Project(String name, Discpiline discpiline){
		_name = name;
		_discpiline = discpiline;
		_closed = false;
		_submissions = new HashMap<Student, Submission>();
		_hasAnswered = new ArrayList<Student>();
	}

	public String getName(){
		return _name;
	}

	void close(){
		_closed = true;
		if(_survey != null){
			_survey.setState(new OpenSurvey());
			_survey.sendOpenMessages();
		}
	}

	Survey getSurvey(){
		return _survey;
	}

	void createSurvey() throws DuplicateSurveyIdException{
		if(_survey == null){
			_survey = new Survey(this, getSubscribers());
		}
		else{
			throw new DuplicateSurveyIdException(this);
		}
	}

	void addSubmission(Student st, String message){
		Submission s = new Submission(message, st);
		_submissions.put(st,s);
		//_submissions.subscribe(st);
	}

	
	HashMap<Student, Submission> getSubmission(){
		return _submissions;
	}

	Discpiline getDiscipline(){
		return _discpiline;
	}

	int getNumOfSubs(){
		return _submissions.size();
	}

	boolean isClosed(){
		return _closed;
	}

	void cancelSurvey() throws NoSurveyIdException, NonEmptySurveyIdException, SurveyFinishedIdException{
		if(_survey == null){
			throw new NoSurveyIdException(this);
		}
		else if(_survey.getState() instanceof FinishedSurvey){
			throw new SurveyFinishedIdException(this);
		}
		else if(_survey.getState() instanceof ClosedSurvey){
			_survey.setState(new OpenSurvey());
			_survey.sendOpenMessages();
		}
		else if(_survey.getNumOfAnswers() == 0){
			_survey = null;
		}
		else{
			throw new NonEmptySurveyIdException(this);
		}
	}

	public static Comparator<Project> nameComparator = new Comparator<Project>(){
 		public int compare(Project p1, Project p2){
 			return p1.getName().compareTo(p2.getName());
 		}
 	};

 	HashSet<Observer> getSubscribers(){
 		HashSet<Observer> observers = new HashSet<Observer>();
 		observers.addAll(_discpiline.getStudents());
 		observers.addAll(_discpiline.getCourse().getRepresentatives());
 		observers.addAll(_discpiline.getTeachers());
		return observers;
 	}

 	void answerSurvey(int hours, String comment,Student student) throws NoSurveyIdException,NoSuchProjectIdException{
 		Answer a = new Answer(hours, comment);
    	Survey sur = getSurvey();
    	if(sur == null){
      		throw new NoSurveyIdException(this);
    	}
    	ArrayList<Student> students = new ArrayList<Student>(_submissions.keySet());
    	if(students.contains(student) == false){
    		throw new NoSuchProjectIdException(_name);
    	}
    	if(_hasAnswered.contains(student) == false){
    		sur.answerSurvey(a);
    		_hasAnswered.add(student);
    	}
 	}
}