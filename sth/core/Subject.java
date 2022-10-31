package sth.core;

import java.util.HashSet;

public abstract class Subject implements java.io.Serializable{
	HashSet<Observer> _observers;
	public Subject(){
		_observers = new HashSet<Observer>();
	}

	public Subject(HashSet<Observer> observers){
		_observers = observers;
	}

	public void update(Notification n){
		for(Observer o : _observers){
			o.update(n);
		}
	}
	

	public void subscribe(Observer observer){
		if (_observers.contains(observer) == false){
			System.out.println(observer);
			_observers.add(observer);
		}
	}	
}