package sabi.sample.gnu.coreutils.yes;

import sabi.DaxConn;
import sabi.Err;

import java.util.List;

public class ArgDaxConn implements DaxConn {

  protected final String[] args;

  public ArgDaxConn(String[] args) {
    this.args = args;
  }

  public String[] getArgs() {
    return args;
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
}
