package sth.core;

import sth.core.exception.BadEntryException;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author      Group 55
 * @version     1.0              
 */

/**
 * Classe "School":
 * 	Representa uma universidade contendo cursos e alunos.
 * 	Implementa a interface Serializable.
 *	@see sth.app.main.DoOpen
 * 	@see sth.app.main.DoSave
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;
  private HashMap<Integer, Person> _users;
  private HashMap<String, Course> _courses;

  /**
   * Construtor da classe "School":
   * 	Inicializa os HashMaps para as pessoas e os cursos.
   */
  public School(){
    _users = new HashMap<Integer, Person>();
    _courses = new HashMap<String, Course>();
  }
  
  
  /**
   * Metodo que faz parse do ficheiro com o nome da 
   * string recebida como argumento.
   * 
   * @param filename	nome do ficheiro a importar
   * @throws BadEntryException	erro no nome do ficheiro
   * @throws IOException		erro no input ou output
   */
 void importFile(String filename) throws IOException, BadEntryException{
    MyParser parser = new MyParser(this);
    parser.parseFile(filename);
  }
  
  
  /**
   * Metodo "getter" que recebe um ID e 
   * retorna a pessoa associada a esse ID.
   * 
   * @param pid		ID do objeto da classe Pessoa no HashMap
   * @return 		Pessoa com o ID introduzido
   */
  Person getPerson(int pid){
    return _users.get(pid);

  }
  
  /**
   * Metodo que adiciona uma pessoa ao HashMap de pessoas.
   * 
   * @param person		Pessoa a ser adicionada
   */
  void addPerson(Person person){
    _users.put(person.getId(), person);
  }
  
  /**
   * Metodo que adiciona um curso ao HashMap de cursos.
   * 
   * @param course		Curso a ser adicionado
   */
  void addCourse(Course course){
      _courses.put(course.getName(), course);
  }
  
  /**
   * Metodo que retorna todas as pessoas da propria classe.
   *  
   * @return	HashMap de pessoas
   */
  HashMap<Integer, Person> getAllUsers(){
    return _users;
  }
  
  /**
   * Metodo que retorna o curso com 
   * o nome da string passado como argumento.
   * Se o curso nao existir, é criado um novo curso com esse nome.
   * 
   * @param name	Nome do curso
   * @return		Curso com o nome "name"
   */
  Course parseCourse(String name){
    Course c = _courses.get(name);
    if (c == null){
      c = new Course(name);
      addCourse(c);
    }
    return c;
  }
}
