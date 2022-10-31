package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;
import sth.app.exception.NoSurveyException;
import java.util.ArrayList;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSurveyIdException;

/**
 * 4.4.5. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    ArrayList<String> results;
    try{
      results = _receiver.showResults(_discipline.value(), _project.value());
    }
    catch(NoSurveyIdException e){
      throw new NoSurveyException(_discipline.value(), _project.value());
    }
    for(String s : results){
      _display.addLine(s);
    }
    _display.display();
  }
}
