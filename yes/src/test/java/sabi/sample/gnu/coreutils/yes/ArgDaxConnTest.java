package sabi.sample.gnu.coreutils.yes;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import sabi.DaxSrc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class ArgDaxConnTest {

  @Test
  void should_create_a_DaxConn() throws Err {
    var ds = new ArgDaxSrc(new String[] { "abc", "def" });
    var conn = ArgDaxConn.class.cast(ds.createDaxConn());
    assertThat(conn.args.length).isEqualTo(2);
    assertThat(conn.args[0]).isEqualTo("abc");
    assertThat(conn.args[1]).isEqualTo("def");
  }

  @Test
  void should_commit() throws Err {
    var ds = new ArgDaxSrc(new String[] { "abc", "def" });
    var conn = ArgDaxConn.class.cast(ds.createDaxConn());
    conn.commit();
  }

  @Test
  void should_rollback() throws Err {
    var ds = new ArgDaxSrc(new String[] { "abc", "def" });
    var conn = ArgDaxConn.class.cast(ds.createDaxConn());
    conn.rollback();
  }

  @Test
  void should_close() throws Err {
    var ds = new ArgDaxSrc(new String[] { "abc", "def" });
    var conn = ArgDaxConn.class.cast(ds.createDaxConn());
    conn.close();
  }
}
