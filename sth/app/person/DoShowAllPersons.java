package sth.app.person;

import pt.tecnico.po.ui.Command;
import sth.core.SchoolManager;
import java.util.ArrayList;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {

  /**
   * @param receiver
   */
  public DoShowAllPersons(SchoolManager receiver) {
    super(Label.SHOW_ALL_PERSONS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    ArrayList<String> users = _receiver.showAllPersons();
    for(String s : users){
      _display.addLine(s);
    }
    _display.display();
  }

}
