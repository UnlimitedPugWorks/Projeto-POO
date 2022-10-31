package sth.app.teaching;

import java.util.ArrayList;
import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.4.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoShowProjectSubmissions(SchoolManager receiver) {
    super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    //FIXME implement command
    //String header = _discipline.value() + " - " + _project.value();
    //_display.addLine(header);
    ArrayList<String> _submissions = _receiver.showSubmissions(_discipline.value() , _project.value());
    for(String s: _submissions){
      _display.addLine(s);
    }
    _display.display();
  }
}
