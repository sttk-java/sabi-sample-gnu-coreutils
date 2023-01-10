package sabi.sample.gnu.coreutils.yes;

import sabi.DaxBase;
import sabi.Err;

public class YesApp {

  public static void main(String ...args) throws Err {
    DaxBase.addGlobalDaxSrc("cli-args", new ArgDaxSrc(args));
    new YesLogic().execute(new AppDax());
  }

  static class AppDax extends DaxBase implements ArgDax, ConsoleDax {}
}
