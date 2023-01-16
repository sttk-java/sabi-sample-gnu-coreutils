package sabi.sample.gnu.coreutils.lib;

import sabi.DaxSrc;
import sabi.DaxConn;
import sabi.Err;

public class OsDaxSrc implements DaxSrc {

  @Override
  public DaxConn createDaxConn() throws Err {
    return new OsDaxConn();
  }
}
