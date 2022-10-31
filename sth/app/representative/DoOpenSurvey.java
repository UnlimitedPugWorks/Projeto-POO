package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;
import sth.core.exception.OpeningSurveyIdException;
import sth.app.exception.OpeningSurveyException;
import sth.core.exception.NoSurveyIdException;
import sth.app.exception.NoSurveyException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.6.3. Open survey.
 */
public class DoOpenSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoOpenSurvey(SchoolManager receiver) {
    super(Label.OPEN_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    //FIXME implement command
    try{
      _receiver.openSurvey(_discipline.value(), _project.value());
    }
    catch(NoSurveyIdException e){
      throw new NoSurveyException(_discipline.value(), _project.value());
    }
    catch(OpeningSurveyIdException e){
      throw new OpeningSurveyException(_discipline.value(), _project.value());
    }
  }

}
