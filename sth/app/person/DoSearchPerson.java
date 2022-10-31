package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import java.util.ArrayList;

//FIXME import other classes if needed

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {
  Input<String> _string;
  //FIXME add input fields if needed
  
  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
    _string = _form.addStringInput(Message.requestPersonName());
    //FIXME initialize input fields if needed
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    ArrayList<String> users = _receiver.searchPerson(_string.value());
    for(String s: users){
        _display.addLine(s);
      }
      _display.display();
    }
  }

