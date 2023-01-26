package sabi.sample.gnu.coreutils.whoami;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;
import sabi.sample.gnu.coreutils.whoami.WhoamiDax.Mode;;

import sabi.DaxBase;
import sabi.Err;

public class ArgDaxTest {

  class TestDax extends DaxBase implements ArgDax {}

  @Test
  void should_get_mode_Version_when_no_arg() throws Err {
    var argv = new String[0];
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Normal);
  }

  @Test
  void should_get_mode_Version_when_one_arg() throws Err {
    var argv = new String[] { "--version" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Version);
  }

  @Test
  void should_get_mode_Help_when_one_arg() throws Err {
    var argv = new String[] { "--help" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Help);
  }

  @Test
  void should_get_mode_Normal_when_one_arg() throws Err {
    var argv = new String[] { "abc" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Normal);
  }

  @Test
  void should_get_mode_Vesion_when_two_args() throws Err {
    var argv = new String[] { "abc", "--version" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Version);
  }

  @Test
  void should_get_mode_Help_when_two_args() throws Err {
    var argv = new String[] { "abc", "--help" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Help);
  }

  @Test
  void should_get_mode_Normal_when_two_args() throws Err {
    var argv = new String[] { "-abc", "def" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Normal);
  }
}
