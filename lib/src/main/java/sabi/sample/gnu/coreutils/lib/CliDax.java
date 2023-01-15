package sabi.sample.gnu.coreutils.lib;

import sabi.Dax;
import sabi.Err;

public interface CliDax extends Dax {

  default CliDaxConn getCliDaxConn(String name) throws Err {
    return CliDaxConn.class.cast(getDaxConn(name));
  }
}
