package sabi.sample.gnu.coreutils.whoami;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;
import sabi.sample.gnu.coreutils.whoami.WhoamiDax.Mode;;

import sabi.Proc;
import sabi.DaxBase;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class WhoamiLogicTest {

  class MapDax extends DaxBase implements WhoamiDax {
    final Map<String, Object> map = new HashMap<>();

    @Override
    public Mode getMode() throws Err {
      return Mode.valueOf(String.class.cast(map.get("mode")));
    }

    @Override
    public String getEffectiveUserId() throws Err {
      return String.valueOf(map.get("euid"));
    }

    @Override
    public String getUserNameByUserId(final String uid) throws Err {
      @SuppressWarnings("unchecked")
      var m = (Map<String, String>) map.get("users");
      return m.get(uid);
    }

    @Override
    public void printUserName(final String userName) {
      map.put("print", userName);
    }

    @Override
    public void printHelp() {
      map.put("print", "HELP");
    }

    @Override
    public void printVersion() {
      map.put("print", "VERSION");
    }
  }

  @Test
  void should_print_version_if_mode_is_version() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Version.name());

    var proc = new Proc<WhoamiDax>(dax);
    proc.runTxn(new WhoamiLogic());

    assertThat(dax.map.get("print")).isEqualTo("VERSION");
  }

  @Test
  void should_print_help_if_mode_is_help() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Help.name());

    var proc = new Proc<WhoamiDax>(dax);
    proc.runTxn(new WhoamiLogic());

    assertThat(dax.map.get("print")).isEqualTo("HELP");
  }

  @Test
  void should_print_username_if_mode_is_normal() throws Err {
    var users = new HashMap<String, String>();
    users.put("111", "foo");
    users.put("123", "bar");

    var dax = new MapDax();
    dax.map.put("mode", Mode.Normal.name());
    dax.map.put("euid", "123");
    dax.map.put("users", users);

    var proc = new Proc<WhoamiDax>(dax);
    proc.runTxn(new WhoamiLogic());

    assertThat(dax.map.get("print")).isEqualTo("bar");
  }
}
