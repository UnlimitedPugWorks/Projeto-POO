package sth.app.student;

import sth.core.SchoolManager;

import java.util.ArrayList;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.app.exception.NoSurveyException;
import sth.core.exception.NoSurveyIdException;

/**
 * 4.5.3. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, NoSurveyException {
    //FIXME implement command
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
