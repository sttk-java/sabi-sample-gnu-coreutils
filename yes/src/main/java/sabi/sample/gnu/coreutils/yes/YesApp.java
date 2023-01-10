package sabi.sample.gnu.coreutils.yes;

import sabi.DaxBase;
import sabi.Err;

public class YesApp {

  public static void main(String ...args) throws Err {
    var dax = new YesAppDax();
    dax.addLocalDaxSrc("cli-args", new ArgDaxSrc(args));
    new YesLogic().execute(dax);
  }

  static class YesAppDax extends DaxBase implements ArgDax, ConsoleDax {}
}
