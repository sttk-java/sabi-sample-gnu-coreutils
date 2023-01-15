package sabi.sample.gnu.coreutils.lib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class CliDaxTest {

  class TestDax extends DaxBase implements CliDax {}

  @Test
  void should_get_CliDaxConn_by_name() throws Err {
    var argv = new String[]{ "abc", "def" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);

    var conn = dax.getCliDaxConn("cli");
    assertThat(conn.getArgv()).isEqualTo(new String[]{ "abc", "def" });
  }
}
