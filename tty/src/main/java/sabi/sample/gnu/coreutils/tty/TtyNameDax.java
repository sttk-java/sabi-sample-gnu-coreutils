package sabi.sample.gnu.coreutils.tty;

import sabi.sample.gnu.coreutils.lib.OsDax;
import sabi.sample.gnu.coreutils.lib.OsDaxConn.FailToGetTtyname;
import sabi.sample.gnu.coreutils.tty.TtyDax.StdinIsNotTty;

import sabi.Err;

public interface TtyNameDax extends OsDax, TtyDax {

  default String getStdinTtyName() throws Err {
    var conn = getOsDaxConn("os");
    var fd = 0;
    try {
      return conn.getTtyName(fd);
    } catch (Err e) {
      if (e.getReason() instanceof FailToGetTtyname) {
        throw new Err(new StdinIsNotTty());
      }
      throw e;
    }
  }
}
