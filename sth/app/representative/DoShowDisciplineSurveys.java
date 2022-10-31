package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import java.util.ArrayList;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.app.exception.NoSuchDisciplineException;

//FIXME import other classes if needed

/**
 * 4.6.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

  //FIXME add input fields if needed
  Input<String> _discipline;
  /**
   * @param receiver
   */
  public DoShowDisciplineSurveys(SchoolManager receiver) {
    super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
    //FIXME initialize input fields if needed
    _discipline = _form.addStringInput(Message.requestDisciplineName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
   try{
      ArrayList<String> surveys = _receiver.showDisciplineSurveys(_discipline.value());
      for(String s: surveys){
      _display.addLine(s);
      }
    _display.display();
    }
    catch(NoSuchDisciplineIdException e){
      throw new NoSuchDisciplineException(_discipline.value());
    }
    //FIXME implement command

  }

}
