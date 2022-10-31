package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.app.exception.NoSuchDisciplineException;
import java.util.ArrayList;


//FIXME import other classes if needed

/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

  Input<String> _disciplina;

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
    _disciplina = _form.addStringInput(Message.requestDisciplineName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    String d = _disciplina.value();
    try{
      //ArrayList<Student> students = _receiver.showDisciplineStudents(d); 
      ArrayList<String> students = _receiver.showDisciplineStudents(d);
      //for(Student s : students){
      for(String s: students){
        //_display.addLine(s.toString());
        _display.addLine(s);
      }
      _display.display();
    }
    catch(NoSuchDisciplineIdException e){
      throw new NoSuchDisciplineException(d);
    }
  }

}
