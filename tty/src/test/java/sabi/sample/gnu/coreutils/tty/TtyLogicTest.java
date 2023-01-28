package sabi.sample.gnu.coreutils.tty;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.lib.CliDaxSrc;
import sabi.sample.gnu.coreutils.tty.TtyDax.Mode;;

import sabi.Proc;
import sabi.DaxBase;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class TtyLogicTest {

  record TtyError() {}

  class MapDax extends DaxBase implements TtyDax {
    final Map<String, Object> map = new HashMap<>();

    @Override
    public Mode getMode() throws Err {
      try {
        return Mode.class.cast(map.get("mode"));
      } catch (Exception e) {
        throw new Err(new TtyDax.InvalidOption("--opt"));
      }
    }

    @Override
    public String getStdinTtyName() throws Err {
      var error = String.class.cast(map.get("error"));
      if ("notty".equals(error)) {
        throw new Err(new TtyDax.StdinIsNotTty());
      } else if ("ttyError".equals(error)) {
        throw new Err(new TtyError());
      } else {
        return String.class.cast(map.get("ttyname"));
      }
    }

    @Override
    public void printTtyName(String ttyname) throws Err {
      var error = String.class.cast(map.get("error"));
      if ("failToPrint".equals(error)) {
        throw new Err(new TtyDax.FailToPrint());
      }
      map.put("print", ttyname);
    }

    @Override
    public void printNotTty(Err err) {
      map.put("print", err);
    }

    @Override
    public void printTtyError(Err err) {
      map.put("print", err);
    }

    @Override
    public void printModeError(Err err) {
      map.put("print", err);
    }

    @Override
    public void printVersion() {
      map.put("print", "VERSION");
    }

    @Override
    public void printHelp() {
      map.put("print", "HELP");
    }
  }

  @Test
  void should_print_version_if_mode_is_version() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Version);

    var proc = new Proc<TtyDax>(dax);
    proc.runTxn(new TtyLogic());

    assertThat(dax.map.get("print")).isEqualTo("VERSION");
  }

  @Test
  void should_print_help_if_mode_is_help() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Help);

    var proc = new Proc<TtyDax>(dax);
    proc.runTxn(new TtyLogic());

    assertThat(dax.map.get("print")).isEqualTo("HELP");
  }

  @Test
  void should_print_ttyname_if_mode_is_normal() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Normal);
    dax.map.put("ttyname", "/dev/ttys001");

    var proc = new Proc<TtyDax>(dax);
    proc.runTxn(new TtyLogic());

    assertThat(dax.map.get("print")).isEqualTo("/dev/ttys001");
  }

  @Test
  void should_print_nothing_if_mode_is_silent() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Silent);
    dax.map.put("ttyname", "/dev/ttys001");

    var proc = new Proc<TtyDax>(dax);
    proc.runTxn(new TtyLogic());

    assertThat(dax.map.get("print")).isNull();
  }

  @Test
  void should_print_and_throw_an_error_if_mode_is_error() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", "ERROR");

    var proc = new Proc<TtyDax>(dax);
    try {
      proc.runTxn(new TtyLogic());
      fail();
    } catch (Err err) {
      assertThat(dax.map.get("print")).isEqualTo(err);
      if (err.getReason() instanceof TtyDax.InvalidOption) {
        assertThat(err.get("option")).isEqualTo("--opt");
        var reason = TtyDax.InvalidOption.class.cast(err.getReason());
        assertThat(reason.option()).isEqualTo("--opt");
      } else {
        fail(err);
      }
    }
  }

  @Test
  void should_print_and_throw_an_error_if_mode_is_normal_but_nottty() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Normal);
    dax.map.put("error", "notty");

    var proc = new Proc<TtyDax>(dax);
    try {
      proc.runTxn(new TtyLogic());
      fail();
    } catch (Err err) {
      assertThat(dax.map.get("print")).isEqualTo(err);
      assertThat(err.getReason()).isInstanceOf(TtyDax.StdinIsNotTty.class);
    }
  }

  @Test
  void should_print_and_throw_an_error_if_mode_is_silent_but_nottty() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Silent);
    dax.map.put("error", "notty");

    var proc = new Proc<TtyDax>(dax);
    try {
      proc.runTxn(new TtyLogic());
      fail();
    } catch (Err err) {
      assertThat(dax.map.get("print")).isNull();
      assertThat(err.getReason()).isInstanceOf(TtyDax.StdinIsNotTty.class);
    }
  }

  @Test
  void should_print_and_throw_an_error_if_mode_is_normal_but_tty_error() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Normal);
    dax.map.put("error", "ttyError");

    var proc = new Proc<TtyDax>(dax);
    try {
      proc.runTxn(new TtyLogic());
      fail();
    } catch (Err err) {
      assertThat(dax.map.get("print")).isEqualTo(err);
      assertThat(err.getReason()).isInstanceOf(TtyError.class);
    }
  }

  @Test
  void should_print_and_throw_an_error_if_mode_is_silent_but_tty_error() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Silent);
    dax.map.put("error", "ttyError");

    var proc = new Proc<TtyDax>(dax);
    try {
      proc.runTxn(new TtyLogic());
      fail();
    } catch (Err err) {
      assertThat(dax.map.get("print")).isNull();
      assertThat(err.getReason()).isInstanceOf(TtyError.class);
    }
  }

  @Test
  void should_throw_an_error_if_mode_is_normal_but_fail_to_write() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Normal);
    dax.map.put("error", "failToPrint");

    var proc = new Proc<TtyDax>(dax);
    try {
      proc.runTxn(new TtyLogic());
      fail();
    } catch (Err err) {
      assertThat(dax.map.get("print")).isNull();
      assertThat(err.getReason()).isInstanceOf(TtyDax.FailToPrint.class);
    }
  }
}
