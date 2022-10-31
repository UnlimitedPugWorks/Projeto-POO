package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;
import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.NoSurveyIdException;
import sth.app.exception.FinishingSurveyException;
import sth.app.exception.NoSurveyException;

//FIXME import other classes if needed

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.6.5. Finish survey.
 */
public class DoFinishSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoFinishSurvey(SchoolManager receiver) {
    super(Label.FINISH_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    //FIXME implement command
    try{
    _receiver.finishSurvey(_discipline.value(), _project.value());
    }
    catch(FinishingSurveyIdException e){
      throw new FinishingSurveyException(_discipline.value(), _project.value());
    }
    catch(NoSurveyIdException e){
      throw new NoSurveyException(_discipline.value(), _project.value());
    }
  }

}
