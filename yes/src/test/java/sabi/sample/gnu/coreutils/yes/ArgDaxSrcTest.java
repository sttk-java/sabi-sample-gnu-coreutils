package sabi.sample.gnu.coreutils.yes;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import sabi.DaxSrc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class ArgDaxSrcTest {

  @Test
  void should_create_an_instance() {
    var ds = new ArgDaxSrc(new String[] { "abc", "def" });
    assertThat(ds.args.length).isEqualTo(2);
    assertThat(ds.args[0]).isEqualTo("abc");
    assertThat(ds.args[1]).isEqualTo("def");
  }

  @Test
  void should_create_a_DaxConn() throws Err {
    var ds = new ArgDaxSrc(new String[] { "abc", "def" });
    var conn = ArgDaxConn.class.cast(ds.createDaxConn());
    assertThat(conn.args.length).isEqualTo(2);
    assertThat(conn.args[0]).isEqualTo("abc");
    assertThat(conn.args[1]).isEqualTo("def");
  }
}
