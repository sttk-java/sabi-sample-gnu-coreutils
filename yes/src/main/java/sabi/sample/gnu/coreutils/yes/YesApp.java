package sabi.sample.gnu.coreutils.yes;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;

public class YesApp {

  public static void main(String ...args) throws Err {
    DaxBase.addGlobalDaxSrc("cli", new CliDaxSrc(args));
    new Proc<YesDax>(new AppDax()).runTxn(new YesLogic());
  }

  static class AppDax extends DaxBase implements ArgDax, ConsoleDax {}
}
