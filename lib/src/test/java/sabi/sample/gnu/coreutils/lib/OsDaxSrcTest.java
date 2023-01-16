package sabi.sample.gnu.coreutils.lib;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import sabi.DaxSrc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class OsDaxSrcTest {

  @Test
  void should_create_an_instance() {
    var ds = new OsDaxSrc();
    assertThat(ds).isNotNull();
  }

  @Test
  void should_create_a_DaxConn() throws Err {
    var ds = new OsDaxSrc();
    var conn = OsDaxConn.class.cast(ds.createDaxConn());
    assertThat(conn).isNotNull();
  }
}
