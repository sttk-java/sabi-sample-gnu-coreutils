package sabi.sample.gnu.coreutils.lib;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import sabi.DaxSrc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class CliDaxSrcTest {

  @Test
  void should_create_an_instance() {
    var ds = new CliDaxSrc(new String[] { "abc", "def" });
    assertThat(ds.argv.length).isEqualTo(2);
    assertThat(ds.argv[0]).isEqualTo("abc");
    assertThat(ds.argv[1]).isEqualTo("def");
  }

  @Test
  void should_create_a_DaxConn() throws Err {
    var ds = new CliDaxSrc(new String[] { "abc", "def" });
    var conn = CliDaxConn.class.cast(ds.createDaxConn());
    assertThat(conn.argv.length).isEqualTo(2);
    assertThat(conn.argv[0]).isEqualTo("abc");
    assertThat(conn.argv[1]).isEqualTo("def");
  }
}
