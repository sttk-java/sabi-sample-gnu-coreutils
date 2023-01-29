package sabi.sample.gnu.coreutils.tty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;
import sabi.sample.gnu.coreutils.tty.TtyDax.Mode;
import sabi.sample.gnu.coreutils.tty.TtyDax.InvalidOption;

import sabi.DaxBase;
import sabi.Err;

public class ArgDaxTest {

  class TestDax extends DaxBase implements ArgDax {}

  @Test
  void should_get_mode_Normal_when_no_arg() throws Err {
    var argv = new String[0];
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Normal);
  }  

  @Test
  void should_get_mode_if_arg_is_silent() throws Err {
    var argv = new String[] { "--silent" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Silent);
  }

  @Test
  void should_get_mode_if_arg_is_s() throws Err {
    var argv = new String[] { "-s" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Silent);
  }

  @Test
  void should_get_mode_if_arg_is_quiet() throws Err {
    var argv = new String[] { "--quiet" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Silent);
  }

  @Test
  void should_get_mode_if_arg_is_version() throws Err {
    var argv = new String[] { "--version" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Version);
  }

  @Test
  void should_get_mode_if_args_are_version_and_another() throws Err {
    var argv = new String[] { "--silent", "--version" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Version);
  }

  @Test
  void should_get_mode_if_arg_is_help() throws Err {
    var argv = new String[] { "--help" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Help);
  }

  @Test
  void should_get_mode_if_arg_is_help_and_another() throws Err {
    var argv = new String[] { "--quest", "--help" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Help);
  }

  @Test
  void should_get_mode_if_arg_is_invalid() throws Err {
    var argv = new String[] { "-x" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    try {
      dax.getMode();
      fail();
    } catch (Err e) {
      assertThat(e.getReason()).isInstanceOf(InvalidOption.class);
      var reason = InvalidOption.class.cast(e.getReason());
      assertThat(reason.option()).isEqualTo("-x");
    }
  }

  @Test
  void should_get_mode_if_args_are_invalid_and_version() throws Err {
    var argv = new String[] { "-x", "--version" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Version);
  }

  @Test
  void should_get_mode_if_args_are_invalid_and_help() throws Err {
    var argv = new String[] { "-x", "--help" };
    var ds = new CliDaxSrc(argv);

    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Help);
  }
}
