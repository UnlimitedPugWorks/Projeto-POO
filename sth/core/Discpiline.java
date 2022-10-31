package sth.core;

import java.util.HashMap;
import java.util.Comparator;
import java.util.ArrayList;
import sth.core.exception.MaxDisciplineException;
import sth.core.exception.ProjectDuplicatedException;


public class Discpiline implements java.io.Serializable{
	private static final long serialVersionUID = 201810051538L;
	private int _capacidade;
	private String _name;
	private Course _course;
	private HashMap<String, Teacher> _teachers;
	private HashMap<String, Project> _projects;
	private HashMap<Integer, Student> _students;

	Discpiline(String name) {
		_name = name;
		_teachers = new HashMap<String, Teacher>();
		_projects = new HashMap<String, Project>();
		_students = new HashMap<Integer, Student>();
	}

	void setCourse(Course course){
		_course = course;

	}

	void addTeacher(Teacher teacher){
		_teachers.put(teacher.getName(), teacher);
	}

	String getName(){
		return _name;
	}	

	void enrollStudent(Student student) throws MaxDisciplineException{
		_students.put(student.getId(), student);
		student.addDiscpiline(this);
	}

	Project getProject(String name){
		return _projects.get(name);
	}


	void addProject(Project p) throws ProjectDuplicatedException{
		if(!_projects.containsKey(p.getName())){
			_projects.put(p.getName(), p);
		}
		else{
			throw new ProjectDuplicatedException(p);
		}
	}

	public Course getCourse(){
		return _course;
	}

	//public HashMap<Integer,Student> getStudents(){
	//	return _students;
	//}

	public ArrayList<Project> getProjects(){
		ArrayList<Project> _projectsList = new ArrayList<Project>(_projects.values());
		_projectsList.sort(Project.nameComparator);
		return new ArrayList<Project>(_projects.values());
	}

	public ArrayList<Student> getStudents(){
		return new ArrayList<Student>(_students.values());
	}

	public ArrayList<Teacher> getTeachers(){
		return new ArrayList<Teacher>(_teachers.values());
	}

	public static Comparator<Discpiline> nameComparator = new Comparator<Discpiline>(){
    	public int compare(Discpiline p1, Discpiline p2){
      		return p1.getName().compareTo(p2.getName());
    	}
 	};

 	public static Comparator<Discpiline> courseComparator = new Comparator<Discpiline>(){
 		public int compare(Discpiline p1, Discpiline p2){
 			return p1.getCourse().getName().compareTo(p2.getCourse().getName());
 		}
 	};

 	public boolean equals(Discpiline d){
 		return ((this._name == d.getName()) && (this._course == d.getCourse()));
 	}
}