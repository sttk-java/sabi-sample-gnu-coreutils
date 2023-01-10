package sabi.sample.gnu.coreutils.yes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import sabi.sample.gnu.coreutils.yes.YesDax.Mode;

import sabi.DaxBase;
import sabi.Proc;
import sabi.Err;

import java.util.Map;
import java.util.HashMap;

public class YesLogicTest {

  class MapDax extends DaxBase implements YesDax {
    final Map<String, String> map = new HashMap<>();

    @Override
    public Mode getMode() {
      return Mode.valueOf(map.get("mode"));
    }

    @Override
    public String getWord() {
      return map.get("word");
    }

    @Override
    public void printYes() {
      map.put("print", "y");
    }

    @Override
    public void printWord(final String word) {
      map.put("print", word);
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
  void should_print_y_if_mode_is_noWord() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.NoWord.name());

    var proc = new Proc<YesDax>(dax);
    proc.runTxn(new YesLogic());

    assertThat(dax.map.get("print")).isEqualTo("y");
  }

  @Test
  void should_print_word_if_mode_is_word() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Word.name());
    dax.map.put("word", "hello");

    var proc = new Proc<YesDax>(dax);
    proc.runTxn(new YesLogic());

    assertThat(dax.map.get("print")).isEqualTo("hello");
  }

  @Test
  void should_print_version_if_mode_is_version() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Version.name());

    var proc = new Proc<YesDax>(dax);
    proc.runTxn(new YesLogic());

    assertThat(dax.map.get("print")).isEqualTo("VERSION");
  }

  @Test
  void should_print_help_if_mode_is_help() throws Err {
    var dax = new MapDax();
    dax.map.put("mode", Mode.Help.name());

    var proc = new Proc<YesDax>(dax);
    proc.runTxn(new YesLogic());

    assertThat(dax.map.get("print")).isEqualTo("HELP");
  }
}
