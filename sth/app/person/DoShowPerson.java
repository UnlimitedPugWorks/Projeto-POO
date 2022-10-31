package sth.app.person;

import pt.tecnico.po.ui.Command;
import sth.core.SchoolManager;


//FIXME import other classes if needed

/**
 * 4.2.1. Show person.
 */
public class DoShowPerson extends Command<SchoolManager> {

  //FIXME add input fields if needed

  /**
   * @param receiver
   */
  public DoShowPerson(SchoolManager receiver) {
    super(Label.SHOW_PERSON, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    //_receiver.showPerson();
    _display.addLine(_receiver.getLoggedUser().show());
    _display.display();
  }

}
