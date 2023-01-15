package sabi.sample.gnu.coreutils.yes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.yes.YesDax.Mode;
import sabi.sample.gnu.coreutils.lib.CliDaxSrc;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class ArgDaxTest {

  class TestDax extends DaxBase implements ArgDax {}

  @Test
  void should_get_mode_NoWord_when_no_arg() throws Err {
    var args = new String[0];
    var ds = new CliDaxSrc(args);
    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.NoWord);
  }

  @Test
  void should_get_mode_Word_when_one_arg() throws Err {
    var args = new String[] { "hello" };
    var ds = new CliDaxSrc(args);
    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Word);
    assertThat(dax.getWord()).isEqualTo("hello");
  }

  @Test
  void should_get_mode_Help_when_one_arg() throws Err {
    var args = new String[] { "--help" };
    var ds = new CliDaxSrc(args);
    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Help);
  }

  @Test
  void should_get_mode_Version_when_one_arg() throws Err {
    var args = new String[] { "--version" };
    var ds = new CliDaxSrc(args);
    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Version);
  }

  @Test
  void should_get_mode_Word_when_more_than_one_args() throws Err {
    var args = new String[] { "hello", "world" };
    var ds = new CliDaxSrc(args);
    var dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Word);
    assertThat(dax.getWord()).isEqualTo("hello");

    args = new String[] { "--help", "world" };
    ds = new CliDaxSrc(args);
    dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Word);
    assertThat(dax.getWord()).isEqualTo("--help");

    args = new String[] { "--version", "world" };
    ds = new CliDaxSrc(args);
    dax = new TestDax();
    dax.addLocalDaxSrc("cli", ds);
    assertThat(dax.getMode()).isEqualTo(Mode.Word);
    assertThat(dax.getWord()).isEqualTo("--version");
  }
}
