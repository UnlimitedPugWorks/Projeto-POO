package sth.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.exception.NoSuchPersonIdException;
import sth.app.exception.NoSuchPersonException;
import java.util.ArrayList;

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {


  private Input<String> _filename;
  /**
   * @param receiver
   */
  public DoOpen(SchoolManager receiver) {
    super(Label.OPEN, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute()  throws DialogException{
    if(_receiver.getFilename() == null)
      _filename = _form.addStringInput(Message.openFile());
      _form.parse();
      _receiver.setFilename(_filename.value());
    try {
      _receiver.openAs();
    } 
    catch (FileNotFoundException fnfe) {
      _display.popup(Message.fileNotFound());
    }
    catch ( IOException e) {
      e.printStackTrace();
    }
    catch (NoSuchPersonIdException e){
      throw new NoSuchPersonException(e.getId());
    }
    if(_receiver.isLoggedUserAdministrative() == false){
      ArrayList<String> notifications = _receiver.receiveNotifications();
      if(notifications.size() > 0){
        for(String s: notifications){
          _display.addLine(s);
        }
        _display.display();
      }
    }
  }
}

