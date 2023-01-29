package sabi.sample.gnu.coreutils.lib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import sabi.DaxSrc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class OsDaxConnTest {

  @Test
  void should_create_a_DaxConn() throws Err {
    var ds = new OsDaxSrc();
    var conn = OsDaxConn.class.cast(ds.createDaxConn());
    assertThat(conn).isNotNull();
  }

  @Test
  void should_commit() throws Err {
    var ds = new OsDaxSrc();
    var conn = OsDaxConn.class.cast(ds.createDaxConn());
    conn.commit();
  }

  @Test
  void should_rollback() throws Err {
    var ds = new OsDaxSrc();
    var conn = OsDaxConn.class.cast(ds.createDaxConn());
    conn.rollback();
  }

  @Test
  void should_close() throws Err {
    var ds = new OsDaxSrc();
    var conn = OsDaxConn.class.cast(ds.createDaxConn());
    conn.close();
  }

  @Nested
  class getEuid {
    @Test
    void should_get_euid() throws Err {
      var ds = new OsDaxSrc();
      var conn = OsDaxConn.class.cast(ds.createDaxConn());
      System.out.println("euid = " + conn.getEuid());
      assertThat(conn.getEuid()).isGreaterThan(0);
    }
  }

  @Nested
  class getLookupId {
    @Test
    void should_get_pwd_by_uid() throws Err {
      var ds = new OsDaxSrc();
      var conn = OsDaxConn.class.cast(ds.createDaxConn());
      var uid = conn.getEuid();
      var osUser = conn.getLookupId(uid);
      System.out.println("osUser = " + osUser);
      assertThat(osUser.uid()).isNotNull();
      assertThat(osUser.gid()).isNotNull();
      assertThat(osUser.userName()).isNotNull();
      assertThat(osUser.name()).isNotNull();
      assertThat(osUser.homeDir()).isNotNull();
    }

    @Test
    void should_throw_an_error() throws Err {
      var ds = new OsDaxSrc();
      var conn = OsDaxConn.class.cast(ds.createDaxConn());
      try {
        conn.getLookupId(-1);
        fail();
      } catch (Err e) {
        assertThat(e.getReason()).isInstanceOf(OsDaxConn.FailToGetPasswd.class);
        var reason = OsDaxConn.FailToGetPasswd.class.cast(e.getReason());
        assertThat(reason.errno()).isEqualTo(2);  // ENOENT
      }
    }
  }

  @Nested
  class getTtyName {
    /* Always raises an Err{errno=25} on JUnit.
    @Test
    void should_get_ttyname() throws Err {
      var ds = new OsDaxSrc();
      var conn = OsDaxConn.class.cast(ds.createDaxConn());
      var ttyname = conn.getTtyName(0);
      assertThat(ttyname).startsWith("/dev/ttys");
    }
    */

    @Test
    void should_throw_an_error() throws Err {
      var ds = new OsDaxSrc();
      var conn = OsDaxConn.class.cast(ds.createDaxConn());
      try {
        conn.getTtyName(0);
        fail();
      } catch (Err e) {
        assertThat(e.getReason()).isInstanceOf(OsDaxConn.FailToGetTtyname.class);
        var reason = OsDaxConn.FailToGetTtyname.class.cast(e.getReason());
        assertThat(reason.errno()).isEqualTo(Errno.ENOTTY);
      }
    }
  }
}
