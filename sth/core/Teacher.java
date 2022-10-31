package sth.core;
import sth.core.exception.BadEntryException;
import java.util.ArrayList;
import sth.core.exception.ProjectDuplicatedException;
import java.util.Queue;
import java.util.LinkedList;

public class Teacher extends Person implements java.io.Serializable, Observer{
  private ArrayList<Discpiline> _disciplinas;
  private Queue<Notification> _inbox;

	Teacher(Integer id, int phoneNumber, String name){
		super(id, phoneNumber, name);
		_disciplinas = new ArrayList<Discpiline>();
		_inbox = new LinkedList<Notification>();
	}

	void parseContext(String lineContext, School school) throws BadEntryException {
    	String components[] =  lineContext.split("\\|");

    	if (components.length != 2)
      		throw new BadEntryException("Invalid line context " + lineContext);

    	Course course = school.parseCourse(components[0]);
    	Discpiline discipline = course.parseDiscpline(components[1]);
      _disciplinas.add(discipline);
    	discipline.addTeacher(this);
  }
  
  Discpiline getDiscipline(String disciplineName){
    for(Discpiline d : _disciplinas){
      if (d.getName().equals(disciplineName)){
        return d;
      }
    }
    return null;
    //return _disciplinas.get(disciplineName);
  }

  void createProject(String name, Discpiline disc) throws ProjectDuplicatedException {
    Project p = new Project(name, disc);
    disc.addProject(p);
  }

  ArrayList<Discpiline> getSortedDisciplines(){
    ArrayList<Discpiline> returnList = new ArrayList<Discpiline>(_disciplinas);
    returnList.sort(Discpiline.nameComparator);
    returnList.sort(Discpiline.courseComparator);
    return returnList;
  }


  private String showHeader(){
    String s = "DOCENTE|" + this.getId() + "|" + this.getPhoneNumber() + "|" + this.getName();
    return s;
  }

  private String showDisciplines(){
    String s ="";
    ArrayList<Discpiline> disList = getSortedDisciplines();
    for(Discpiline d : disList){
      s+= '\n';
      s+= " * " + d.getCourse().getName() + " - " + d.getName();
    }
    return s;
  }

  public String show(){
    return showHeader() + showDisciplines();
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