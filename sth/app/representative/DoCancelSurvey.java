package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;

import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.SurveyFinishedIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.app.exception.SurveyFinishedException;
import sth.app.exception.NonEmptySurveyException;
import sth.app.exception.NoSurveyException;

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCancelSurvey(SchoolManager receiver) {
    super(Label.CANCEL_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    //FIXME implement command
    try{
     _receiver.cancelSurvey(_discipline.value(), _project.value());
    }
    catch(SurveyFinishedIdException e){
      throw new SurveyFinishedException(_discipline.value(), _project.value());
    }
    catch(NoSurveyIdException e){
      throw new NoSurveyException(_discipline.value(), _project.value());
    }
    catch(NonEmptySurveyIdException e){
      throw new NonEmptySurveyException(_discipline.value(), _project.value());
    }

  }

}
