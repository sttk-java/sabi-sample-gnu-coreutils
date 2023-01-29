package sabi.sample.gnu.coreutils.tty;

import sabi.Err;

public interface TtyDax {

  enum Mode {
    Normal,
    Silent,
    Help,
    Version,
  }

  public record InvalidOption(String option) {};
  public record StdinIsNotTty() {}
  public record FailToPrint() {}

  default Mode getMode() throws Err { return Mode.Help; }
  default String getStdinTtyName() throws Err { return ""; }
  default void printTtyName(String ttyname) throws Err {}
  default void printNotTty(Err err) {}
  default void printTtyError(Err err) {}
  default void printModeError(Err err) {}
  default void printVersion() {}
  default void printHelp() {}
}

