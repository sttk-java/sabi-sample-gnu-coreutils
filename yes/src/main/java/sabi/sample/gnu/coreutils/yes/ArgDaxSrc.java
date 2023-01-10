package sabi.sample.gnu.coreutils.yes;

import sabi.DaxSrc;
import sabi.DaxConn;
import sabi.Err;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArgDaxSrc implements DaxSrc {

  protected final String[] args;

  public ArgDaxSrc(String ...args) {
    this.args = args;
  }

  @Override
  public DaxConn createDaxConn() throws Err {
    return new ArgDaxConn(args);
  }
}
