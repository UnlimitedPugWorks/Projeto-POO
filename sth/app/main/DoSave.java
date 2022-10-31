package sth.app.main;

import java.io.IOException;
import java.io.*;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import java.io.IOException;

//FIXME import other classes if needed

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
  //FIXME add input fields if needed
  Input<String> _filename;


  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
  }
  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute(){
    if(_receiver.getFilename() == null){
      _filename = _form.addStringInput(Message.newSaveAs());
      _form.parse();
      _receiver.setFilename(_filename.value());
    }
    try{
      //_receiver.saveAs(_filename.value());
      _receiver.saveAs();
    }
    catch(IOException e){
      e.printStackTrace();
    }
    //FIXME implement command
  }

}
