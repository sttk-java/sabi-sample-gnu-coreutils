package sabi.sample.gnu.coreutils.lib;

import sabi.Dax;
import sabi.Err;

public interface OsDax extends Dax {

  default OsDaxConn getOsDaxConn(final String name) throws Err {
    return OsDaxConn.class.cast(getDaxConn(name));
  }
}
