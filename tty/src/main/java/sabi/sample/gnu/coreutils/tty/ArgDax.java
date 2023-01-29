package sabi.sample.gnu.coreutils.tty;

import static sabi.sample.gnu.coreutils.tty.TtyDax.InvalidOption;

import sabi.Dax;
import sabi.Err;

import sabi.sample.gnu.coreutils.tty.TtyDax.Mode;
import sabi.sample.gnu.coreutils.lib.CliDax;

public interface ArgDax extends CliDax, TtyDax {

  default Mode getMode() throws Err {
    var mode = Mode.Normal;
    var errArg = "";

    var conn = getCliDaxConn("cli");
    var argv = conn.getArgv();
    for (var arg : argv) {
      switch (arg) {
      case "--version":
        return Mode.Version;

      case "--help":
        return Mode.Help;

      case "-s":
      case "--silent":
      case "--quiet":
        mode = Mode.Silent;
        break;

      default:
        mode = null;
        errArg = arg;
        break;
      }
    }

    if (mode == null) {
      throw new Err(new InvalidOption(errArg));
    }

    return mode;
  }
}
