package sabi.sample.gnu.coreutils.tty;

import static sabi.sample.gnu.coreutils.tty.TtyDax.StdinIsNotTty;

import sabi.Logic;
import sabi.Err;

public class TtyLogic implements Logic<TtyDax> {

  @Override
  public void execute(TtyDax dax) throws Err {
    TtyDax.Mode mode = null;
    try {
      mode = dax.getMode();
    } catch (Err e) {
      dax.printModeError(e);
      throw e;
    }

    switch (mode) {
    case Silent:
      dax.getStdinTtyName();
      break;

    case Normal:
      String ttyname;
      try {
        ttyname = dax.getStdinTtyName();
      } catch (Err e) {
        if (e.getReason() instanceof StdinIsNotTty) {
          dax.printNotTty(e);
          throw e;
        }
        dax.printTtyError(e);
        throw e;
      }
      dax.printTtyName(ttyname);
      break;

    case Version:
      dax.printVersion();
      break;

    default: // Help
      dax.printHelp();
      break;
    }
  }
}
