package sabi.sample.gnu.coreutils.lib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class OsDaxTest {

  class TestDax extends DaxBase implements OsDax {}

  @Test
  void should_get_OsDaxConn_by_name() throws Err {
    var ds = new OsDaxSrc();

    var dax = new TestDax();
    dax.addLocalDaxSrc("os", ds);

    var conn = dax.getOsDaxConn("os");
    assertThat(conn).isInstanceOf(OsDaxConn.class);
  }
}
