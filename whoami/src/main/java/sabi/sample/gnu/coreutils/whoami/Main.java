package sabi.sample.gnu.coreutils.whoami;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;
import sabi.sample.gnu.coreutils.lib.OsDaxSrc;

public class Main {

  public static void main(final String ...args) throws Err {
    DaxBase.addGlobalDaxSrc("cli", new CliDaxSrc(args));
    DaxBase.addGlobalDaxSrc("os", new OsDaxSrc());
    var proc = new Proc<WhoamiDax>(new MainDax());
    proc.runTxn(new WhoamiLogic());
  }

  static class MainDax extends DaxBase
    implements ArgDax, OsUserDax, ConsoleDax {}
}
