package sabi.sample.gnu.coreutils.yes;

import sabi.Logic;
import sabi.Err;

public class YesLogic implements Logic<YesDax> {

  @Override
  public void execute(YesDax dax) throws Err {
    switch (dax.getMode()) {
    case NoWord:
      dax.printYes();
      break;
    case Word:
      dax.printWord(dax.getWord());
      break;
    case Version:
      dax.printVersion();
      break;
    default:
      dax.printHelp();
      break;
    }
  }
}
