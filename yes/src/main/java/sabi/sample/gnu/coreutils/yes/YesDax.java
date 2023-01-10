package sabi.sample.gnu.coreutils.yes;

import sabi.Err;

public interface YesDax {

  default Mode getMode() throws Err { return Mode.Help; }
  default String getWord() throws Err { return ""; }
  default void printYes() {}
  default void printWord(String word) {}
  default void printVersion() {}
  default void printHelp() {}

  enum Mode {
    NoWord,
    Word,
    Help,
    Version,
  }
}
