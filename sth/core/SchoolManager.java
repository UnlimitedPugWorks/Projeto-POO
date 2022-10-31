package sth.core;

/*import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.ProjectDuplicatedException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.SurveyFinishedIdException;
import sth.core.exception.SurveyException;
import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.ClosingSurveyIdException;*/
import sth.core.exception.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
//FIXME import other classes if needed
/**
 * The façade class. A alma do negocio
 */
public class SchoolManager {

  private School _school;
  private Person _loggedUser;
  private String _filename;

  public SchoolManager(){
    _school = new School();
  }
  
  /**
   * @param datafile
   * @throws ImportFileException
   * @throws InvalidCourseSelectionException
   */

    public void importFile(String datafile) throws ImportFileException{
        try{
          _school.importFile(datafile);
        }
        catch(IOException | BadEntryException e){
          throw new ImportFileException(e);
        }
    }

  /**
   * Do the login of the user with the given identifier.

   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no uers with the given identifier
   */
  public void login(int id) throws NoSuchPersonIdException {
    _loggedUser =_school.getPerson(id);
    if(_loggedUser == null){
      throw new NoSuchPersonIdException(id);
    }
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    return _loggedUser instanceof Employee;
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {
    return _loggedUser instanceof Teacher;
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {
    //FIXME implement predicate
    return _loggedUser instanceof Student;
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative(){
    if (_loggedUser instanceof Student){
        Student s = (Student) _loggedUser;
      return s.isRepresentative();
    }
    return false;
  }

  public Person getPerson(Integer id){
    return _school.getAllUsers().get(id);
  }

  public ArrayList<Person> getAllUsers(){
    ArrayList<Person> _users = new ArrayList<Person>(_school.getAllUsers().values());
    return _users;
  } 

  public Person getLoggedUser(){
    return _loggedUser;
  }

  public void closeProject(String disciplineName, String projectName) throws NoSuchDisciplineIdException, NoSuchProjectIdException{
    Teacher teacher = (Teacher) _loggedUser;
    Discpiline d = teacher.getDiscipline(disciplineName);
    if (d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    if(!p.isClosed()){
      p.close();
    }
  }

  public void createProject(String disciplineName, String projectName) throws ProjectDuplicatedException, NoSuchDisciplineIdException {
    Teacher teacher = (Teacher) _loggedUser;
    Discpiline d = teacher.getDiscipline(disciplineName);
    if (d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    teacher.createProject(projectName, d);
  }

   public ArrayList<String> showDisciplineStudents(String disciplineName) throws NoSuchDisciplineIdException {
    Teacher t = (Teacher) _loggedUser;
    Discpiline d = t.getDiscipline(disciplineName);
    if (d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    ArrayList<Student> studentList = d.getStudents();
    studentList.sort(Person.idComparator);
    ArrayList<String> returnList = new ArrayList<String>();
    for(Student s : studentList){
      returnList.add(s.show());
    }
    return returnList;
  }

  public void saveAs() throws IOException{
    try(ObjectOutputStream obOut = new ObjectOutputStream(new FileOutputStream(_filename))){
      obOut.writeObject(_school);
    }
  }

  public void openAs() throws IOException, NoSuchPersonIdException{
    School temp;
    try(ObjectInputStream obIn = new ObjectInputStream(new FileInputStream(_filename))){
      temp = (School) obIn.readObject();
      if (temp.getPerson(_loggedUser.getId()) == null){
        throw new NoSuchPersonIdException(_loggedUser.getId());
      }
      _school = temp;
      _loggedUser = temp.getPerson(_loggedUser.getId());
    }
    catch(ClassNotFoundException e){
      throw new IOException();
    }
  }

  public ArrayList<String> showAllPersons(){
    ArrayList<Person> users = getAllUsers();
    users.sort(Person.idComparator);
    ArrayList<String> returnList = new ArrayList<String>();
    for(Person p: users){
      returnList.add(p.show());
    }
    return returnList;
  }

  public ArrayList<String> searchPerson(String search){
    ArrayList<Person> _users = getAllUsers();
    _users.sort(Person.nameComparator);
    ArrayList<String> returnList = new ArrayList<String>();
    for(Person p : _users){
      if(p.getName().contains(search) == true){
        returnList.add(p.show());
      }
    }
    return returnList;
  }


  public void setFilename(String filename){
    _filename = filename;
  }

  public String getFilename(){
    return _filename;
  }

  public void changeUserPhoneNumber(int phoneNumber){
    _loggedUser.setPhoneNumber(phoneNumber);
  }

  public void submitProject(String disciplineName, String projectName, String submission) throws NoSuchDisciplineIdException, NoSuchProjectIdException{
    Student s = (Student) _loggedUser;
    Discpiline d = s.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    if (p.isClosed()){
      throw new NoSuchProjectIdException(projectName);
    }
    else{
      p.addSubmission(s,submission);
    }
  }

  public ArrayList<String> receiveNotifications(){
    Observer o = (Observer) _loggedUser;
    return o.readInbox();
  }

  public ArrayList<String> showSubmissions(String disciplineName, String projectName) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
    Teacher t = (Teacher) _loggedUser;
    Discpiline d = t.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
      ArrayList<Submission> submissionList = new ArrayList<Submission>(p.getSubmission().values());
      submissionList.sort(Submission.idComparator);
      ArrayList<String>  returnList = new ArrayList<String>();
      returnList.add(d.getName() + " - " + p.getName());
      for(Submission s: submissionList){
        returnList.add(s.showSubmission());
      }
      return returnList;
  }

  public void createSurvey(String disciplineName, String projectName) throws NoSuchDisciplineIdException, NoSuchProjectIdException, DuplicateSurveyIdException{
    Student s = (Student) _loggedUser;
    Course c  = s.getCourse();
    Discpiline d = c.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if((p == null) || p.isClosed()){
      throw new NoSuchProjectIdException(projectName);
    }
    p.createSurvey();
  }

  public ArrayList<String> showResults(String disciplineName, String projectName) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException{
    Discpiline d = null;
    if(isLoggedUserProfessor()){
      Teacher t = (Teacher) _loggedUser;
      d = t.getDiscipline(disciplineName);
    }
    else if(isLoggedUserStudent()){
      Student s = (Student) _loggedUser;
      d = s.getDiscipline(disciplineName);
    }
    if( d == null){
       throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    Survey s = p.getSurvey();
    if(s == null){
      throw new NoSurveyIdException(p);
    }
    ArrayList<String> returnList = new ArrayList<String>();
    if(isLoggedUserProfessor()) returnList = (p.getSurvey().showResultsT());
    else if(isLoggedUserStudent())returnList = (p.getSurvey().showResultsS());
    return returnList;
  }

  public void answerSurvey(String disciplineName, String projectName, int hours, String comment) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException{
    Student s = (Student) _loggedUser;
    Discpiline d = s.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    p.answerSurvey(hours, comment, s);
  }

  public void cancelSurvey(String disciplineName, String projectName) throws NoSuchProjectIdException, NoSuchDisciplineIdException,SurveyFinishedIdException,NonEmptySurveyIdException,NoSurveyIdException{
    Student s = (Student) _loggedUser;
    Course c  = s.getCourse();
    Discpiline d = c.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    p.cancelSurvey();
  }

  public void finishSurvey(String disciplineName, String projectName) throws FinishingSurveyIdException,NoSurveyIdException,NoSuchProjectIdException, NoSuchDisciplineIdException{
    Student s = (Student) _loggedUser;
    Course c = s.getCourse();
    Discpiline d = c.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    Survey sur = p.getSurvey();
    if(sur == null){
      throw new NoSurveyIdException(p);
    }
    sur.finish();
  }

  public void openSurvey(String disciplineName, String projectName) throws OpeningSurveyIdException,NoSurveyIdException,NoSuchProjectIdException, NoSuchDisciplineIdException{
    Student s = (Student) _loggedUser;
    Course c = s.getCourse();
    Discpiline d = c.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    Survey sur = p.getSurvey();
    if(sur == null){
      throw new NoSurveyIdException(p);
    }
    sur.open();
  }

  public void closeSurvey(String disciplineName, String projectName) throws ClosingSurveyIdException,NoSurveyIdException,NoSuchProjectIdException, NoSuchDisciplineIdException{
    Student s = (Student) _loggedUser;
    Course c = s.getCourse();
    Discpiline d = c.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    Project p = d.getProject(projectName);
    if(p == null){
      throw new NoSuchProjectIdException(projectName);
    }
    Survey sur = p.getSurvey();
    if(sur == null){
      throw new NoSurveyIdException(p);
    }
    sur.close();
  }

  public ArrayList<String> showDisciplineSurveys(String disciplineName) throws NoSuchDisciplineIdException{
    Student s = (Student) _loggedUser;
    Course c = s.getCourse();
    Discpiline d = c.getDiscipline(disciplineName);
    if(d == null){
      throw new NoSuchDisciplineIdException(disciplineName);
    }
    ArrayList<Project> projects = d.getProjects();
    projects.sort(Project.nameComparator);
    ArrayList<String> returnList = new ArrayList<String>();
    for(Project p : projects){
      if(p.getSurvey() != null){
        returnList.addAll(p.getSurvey().showResultsR());
      }
    }
    return returnList;
  }

}
