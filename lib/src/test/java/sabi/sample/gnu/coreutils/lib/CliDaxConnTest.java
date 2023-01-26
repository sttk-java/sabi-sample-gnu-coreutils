package sabi.sample.gnu.coreutils.lib;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import sabi.DaxSrc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class CliDaxConnTest {

  @Test
  void should_create_a_DaxConn() throws Err {
    var ds = new CliDaxSrc(new String[] { "abc", "def" });
    var conn = CliDaxConn.class.cast(ds.createDaxConn());
    assertThat(conn.argv.length).isEqualTo(2);
    assertThat(conn.argv[0]).isEqualTo("abc");
    assertThat(conn.argv[1]).isEqualTo("def");
  }

  @Test
  void should_commit() throws Err {
    var ds = new CliDaxSrc(new String[] { "abc", "def" });
    var conn = CliDaxConn.class.cast(ds.createDaxConn());
    conn.commit();
  }

  @Test
  void should_rollback() throws Err {
    var ds = new CliDaxSrc(new String[] { "abc", "def" });
    var conn = CliDaxConn.class.cast(ds.createDaxConn());
    conn.rollback();
  }

  @Test
  void should_close() throws Err {
    var ds = new CliDaxSrc(new String[] { "abc", "def" });
    var conn = CliDaxConn.class.cast(ds.createDaxConn());
    conn.close();
  }

  @Test
  void should_get_arguments() throws Err {
    var ds = new CliDaxSrc(new String[] { "abc", "def" });
    var conn = CliDaxConn.class.cast(ds.createDaxConn());
    var argv = conn.getArgv();
    assertThat(argv.length).isEqualTo(2);
    assertThat(argv[0]).isEqualTo("abc");
    assertThat(argv[1]).isEqualTo("def");
  }
}
