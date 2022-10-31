package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.NoSurveyIdException;
import sth.app.exception.ClosingSurveyException;
import sth.app.exception.NoSurveyException;

/**
 * 4.5.4. Close survey.
 */
public class DoCloseSurvey extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoCloseSurvey(SchoolManager receiver) {
    super(Label.CLOSE_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    //FIXME implement command
    try{
      _receiver.closeSurvey(_discipline.value(), _project.value());
    }
    catch(NoSurveyIdException e){
      throw new NoSurveyException(_discipline.value(), _project.value());
    }
    catch(ClosingSurveyIdException e){
      throw new ClosingSurveyException(_discipline.value(), _project.value());
    }
  }

}
