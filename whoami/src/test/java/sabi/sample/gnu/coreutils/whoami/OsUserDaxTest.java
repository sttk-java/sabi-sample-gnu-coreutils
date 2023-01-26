package sabi.sample.gnu.coreutils.whoami;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.lib.OsDaxSrc;

import sabi.DaxBase;
import sabi.Err;

public class OsUserDaxTest {

  class TestDax extends DaxBase implements OsUserDax {}

  @Test
  void should_get_effective_user_id() throws Err {
    var ds = new OsDaxSrc();
    var dax = new TestDax();
    dax.addLocalDaxSrc("os", ds);
    var euid = dax.getEffectiveUserId();
    try {
      Integer.parseInt(euid);
    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  void should_get_OsUser_by_user_id() throws Err {
    var ds = new OsDaxSrc();
    var dax = new TestDax();
    dax.addLocalDaxSrc("os", ds);
    var euid = dax.getEffectiveUserId();
    var userName = dax.getUserNameByUserId(euid);
    assertThat(userName).isNotEqualTo("");
  }
}
