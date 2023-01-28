package sabi.sample.gnu.coreutils.tty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.lib.OsDaxSrc;
import sabi.sample.gnu.coreutils.tty.TtyDax.StdinIsNotTty;

import sabi.Err;
import sabi.DaxBase;

public class TtyNameDaxTest {

  class TestDax extends DaxBase implements TtyNameDax {}

  // On JUnit, OsDaxConn#getTtyName always raises a StdinIsNotTty error.
  @Test
  void should_get_ttyname_of_stdin() throws Err {
    var ds = new OsDaxSrc();

    var dax = new TestDax();
    dax.addLocalDaxSrc("os", ds);
    try {
      var ttyname = dax.getStdinTtyName();
      System.out.println("ttyname = " + ttyname);
    } catch (Err err) {
      assertThat(err.getReason()).isInstanceOf(StdinIsNotTty.class);
    }
  }
}
