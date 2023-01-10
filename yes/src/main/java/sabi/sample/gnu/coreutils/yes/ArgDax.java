package sabi.sample.gnu.coreutils.yes;

import sabi.Dax;
import sabi.Err;
import sabi.sample.gnu.coreutils.yes.YesDax.Mode;

public interface ArgDax extends Dax, YesDax {

  default ArgDaxConn getArgDaxConn() throws Err {
    return ArgDaxConn.class.cast(getDaxConn("cli-args"));
  }

  @Override
  default Mode getMode() throws Err {
    var conn = getArgDaxConn();
    var args = conn.getArgs();

    switch (args.length) {
    case 0:
      return Mode.NoWord;
    case 1:
      switch (args[0]) {
      case "--help":
        return Mode.Help;
      case "--version":
        return Mode.Version;
      default:
        ; // fallthrough
      }
    default:
      return Mode.Word;
    }
  }

  @Override
  default String getWord() throws Err {
    var conn = getArgDaxConn();
    var args = conn.getArgs();
    return args[0];
  }
}

