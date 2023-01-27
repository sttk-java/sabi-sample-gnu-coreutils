package sabi.sample.gnu.coreutils.yes;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;

public class Main {

  public static void main(String ...args) throws Err {
    DaxBase.addGlobalDaxSrc("cli", new CliDaxSrc(args));
    var proc = new Proc<YesDax>(new MainDax());
    proc.runTxn(new YesLogic());
  }

  static class MainDax extends DaxBase implements ArgDax, ConsoleDax {}
}
