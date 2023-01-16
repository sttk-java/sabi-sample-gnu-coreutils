package sabi.sample.gnu.coreutils.lib;

import sabi.DaxSrc;
import sabi.DaxConn;
import sabi.Err;

public class CliDaxSrc implements DaxSrc {

  protected final String[] argv;

  public CliDaxSrc(final String[] argv) {
    this.argv = argv;
  }

  @Override
  public DaxConn createDaxConn() throws Err {
    return new CliDaxConn(argv);
  }
}
