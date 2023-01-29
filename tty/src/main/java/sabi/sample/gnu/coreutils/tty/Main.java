package sabi.sample.gnu.coreutils.tty;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;
import sabi.sample.gnu.coreutils.lib.OsDaxSrc;
import sabi.sample.gnu.coreutils.tty.TtyDax.InvalidOption;
import sabi.sample.gnu.coreutils.tty.TtyDax.StdinIsNotTty;
import sabi.sample.gnu.coreutils.tty.TtyDax.FailToPrint;

public class Main {

  public static void main(final String ...args) throws Err {
    DaxBase.addGlobalDaxSrc("cli", new CliDaxSrc(args));
    DaxBase.addGlobalDaxSrc("os", new OsDaxSrc());
    var proc = new Proc<TtyDax>(new MainDax());
    try {
      proc.runTxn(new TtyLogic());
    } catch (Err e) {
      if (e.getReason() instanceof InvalidOption) {
        System.exit(2);
      } else if (e.getReason() instanceof StdinIsNotTty) {
        System.exit(1);
      } else if (e.getReason() instanceof FailToPrint) {
        System.exit(3);
      } else {
        e.printStackTrace(System.err);
        System.exit(9);
      }
    }
  }

  static class MainDax extends DaxBase
    implements ArgDax, TtyNameDax, ConsoleDax {}
}
