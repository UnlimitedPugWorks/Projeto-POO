package sth.core;

import sth.core.exception.BadEntryException;
import java.util.List;
import java.util.ArrayList;
import sth.core.exception.MaxRepresentativeException;
import sth.core.exception.MaxDisciplineException;
import java.util.Queue;
import java.util.LinkedList;

public class Student extends Person implements java.io.Serializable, Observer{
  private static final int MAXDISCIPLINE = 6;
	private boolean _isRepresentative;
	private Course _course;
	private int _enrolledDiscplines;
	private List<Discpiline> _discplines;
  private Queue<Notification> _inbox;

	Student(Integer id, int phoneNumber, String name, Boolean isRepresentative){
		super(id, phoneNumber, name);
		_isRepresentative = isRepresentative;
		_enrolledDiscplines = 0;
		_discplines = new ArrayList<Discpiline>();
    _inbox = new LinkedList<Notification>();
	}

	Boolean isRepresentative(){
		return _isRepresentative;
	}

	void setRepresentative(boolean isRepresentative){
		_isRepresentative = isRepresentative;
	}

	void setCourse(Course course){
		_course = course;
	}

	void addDiscpiline(Discpiline d) throws MaxDisciplineException{
		_discplines.add(d);
		_enrolledDiscplines++;
		if(_enrolledDiscplines > MAXDISCIPLINE){
			throw new MaxDisciplineException(this);
		}
	}

	void parseContext(String lineContext, School school) throws BadEntryException{
    	String components[] =  lineContext.split("\\|");
    	if (components.length != 2){
      		throw new BadEntryException("Invalid line context " + lineContext);
    	}
    	try{
    		if (_course == null) {
      			_course = school.parseCourse(components[0]);
      			_course.addStudent(this);
    		}
    	}
    	catch(MaxRepresentativeException e){
    		throw new BadEntryException("Numero de delegados do curso" + e.getCourse().getName() + "foi excedido");
    	}
    	try{
   			Discpiline dis = _course.parseDiscpline(components[1]);
    		dis.enrollStudent(this);
    	}
    	catch(MaxDisciplineException e){
    		throw new BadEntryException("Numero de cursos do aluno" + e.getStudent().getName() + "foi excedido");
    	}
  }

  private String showHeader(){
  	String s = (_isRepresentative)?"DELEGADO|":"ALUNO|";
  	s += this.getId() + "|" + this.getPhoneNumber() + "|" + this.getName();
    return s;
  }

  private String showDisciplines(){
  	_discplines.sort(Discpiline.nameComparator);
    String s = "";
  	for(Discpiline d: _discplines){
  		s += '\n';
      s += '*' +_course.getName() + "-" + d.getName();
  	} 
  	return s;
  }

  public String show(){
    return showHeader() + showDisciplines();
  }

  Discpiline getDiscipline(String disciplineName){
    for(Discpiline d: _discplines){
      if(d.getName().equals(disciplineName)){
        return d;
      }
    }
    return null;
  }

  Course getCourse(){
    return _course;
  }

  public void update(Notification n){
    _inbox.add(n);
  }

  public ArrayList<String> readInbox(){
    ArrayList<String> returnList = new ArrayList<String>();
    Notification n;
    while(_inbox.size() > 0){
      n = _inbox.remove();
      returnList.add(n.toString());
    }
    return returnList;
  }
  
}