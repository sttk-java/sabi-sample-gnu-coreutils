package sabi.sample.gnu.coreutils.whoami;

import sabi.Dax;
import sabi.Err;

import sabi.sample.gnu.coreutils.whoami.WhoamiDax.Mode;
import sabi.sample.gnu.coreutils.lib.CliDax;

public interface ArgDax extends CliDax, WhoamiDax {

  default Mode getMode() throws Err {
    var conn = getCliDaxConn("cli");
    var argv = conn.getArgv();
    for (var arg : argv) {
      switch (arg) {
      case "--version":
        return Mode.Version;
      case "--help":
        return Mode.Help;
      }
    }
    return Mode.Normal;
  }

}
