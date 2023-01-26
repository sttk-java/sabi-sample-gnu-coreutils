package sabi.sample.gnu.coreutils.whoami;

import sabi.Logic;
import sabi.Err;

public class WhoamiLogic implements Logic<WhoamiDax> {

  @Override
  public void execute(WhoamiDax dax) throws Err {
    switch (dax.getMode()) {
    case Version:
      dax.printVersion();
      break;
    case Help:
      dax.printHelp();
      break;
    default:
      runWhoami(dax);
      break;
    }
  }

  protected void runWhoami(final WhoamiDax dax) throws Err {
    var euid = dax.getEffectiveUserId();
    var userName = dax.getUserNameByUserId(euid);
    dax.printUserName(userName);
  }
}
