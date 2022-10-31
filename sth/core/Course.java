package sth.core;

import java.util.Map;
import java.util.HashMap;
import sth.core.exception.MaxRepresentativeException;
import java.util.ArrayList;

public class Course implements java.io.Serializable {
	private static final long serialVersionUID = 201810051538L;
	private static final int MAXREPRESENTATIVE = 7;
	private String _name;
	private int _numRepresentative;
	private Map<String, Discpiline> _discpilines;
	private Map<Integer, Student> _students;
	private Map<Integer, Student> _representatives;
	public Course(String name){
		_name = name;
		_numRepresentative = 0;
		_discpilines = new HashMap<String, Discpiline>();
		_students = new HashMap<Integer, Student>();
		_representatives = new HashMap<Integer, Student>();
	}

	public String getName(){
		return _name;
	}


	void addStudent(Student student) throws MaxRepresentativeException{
		_students.put(student.getId(), student);
		if(student.isRepresentative()){
			_numRepresentative++;
			_representatives.put(student.getId(), student);
		}
		if(_numRepresentative > MAXREPRESENTATIVE){
			throw new MaxRepresentativeException(this);
		}
		student.setCourse(this);
	}

	void addDiscpiline(Discpiline discpiline){
		_discpilines.put(discpiline.getName(), discpiline);
		discpiline.setCourse(this);
	}

	void addRepresentative(Student student) throws MaxRepresentativeException{
		if(_representatives.size() == MAXREPRESENTATIVE){
			throw new MaxRepresentativeException(this);
		}
		_numRepresentative++;
		student.setRepresentative(true);
		_representatives.put(student.getId(), student);
	}

	void removeRepresentative(Student student){
		_numRepresentative--;
		student.setRepresentative(false);
		_representatives.remove(student);
	}
	Discpiline parseDiscpline(String name){
		Discpiline d = _discpilines.get(name);
		if (d == null){
			d = new Discpiline(name);
			addDiscpiline(d);
		}
		d.setCourse(this);
		return d;
	}

	Discpiline getDiscipline(String discpilineName){
		return _discpilines.get(discpilineName);
	}

	ArrayList<Student> getRepresentatives(){
		return new ArrayList<Student>(_representatives.values());
	}
}