package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import sth.core.SchoolManager;



import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.DuplicateSurveyIdException;
import sth.app.exception.DuplicateSurveyException;
/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCreateSurvey(SchoolManager receiver) {
    super(Label.CREATE_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    //FIXME implement command
    try{
      _receiver.createSurvey(_discipline.value(), _project.value());
    }
    catch(DuplicateSurveyIdException e){
      throw new DuplicateSurveyException(_discipline.value(), _project.value());
    }
  }

}
