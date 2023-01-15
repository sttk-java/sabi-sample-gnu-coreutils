package sabi.sample.gnu.coreutils.yes;

import sabi.Dax;
import sabi.Err;
import sabi.sample.gnu.coreutils.yes.YesDax.Mode;
import sabi.sample.gnu.coreutils.lib.CliDax;

public interface ArgDax extends CliDax, YesDax {

  @Override
  default Mode getMode() throws Err {
    var conn = getCliDaxConn("cli");
    var argv = conn.getArgv();

    switch (argv.length) {
    case 0:
      return Mode.NoWord;
    case 1:
      switch (argv[0]) {
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
    var conn = getCliDaxConn("cli");
    var argv = conn.getArgv();
    return argv[0];
  }
}

