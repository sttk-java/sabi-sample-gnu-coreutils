package sabi.sample.gnu.coreutils.lib;

import sabi.DaxConn;
import sabi.Err;

public class CliDaxConn implements DaxConn {

  protected final String[] argv;

  public CliDaxConn(String[] argv) {
    this.argv = argv;
  }

  @Override
  public void commit() throws Err {
  }

  @Override
  public void rollback() {
  }

  @Override
  public void close() {
  }

  public String[] getArgv() {
    return argv;
  }
}
