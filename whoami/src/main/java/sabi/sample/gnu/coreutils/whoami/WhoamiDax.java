package sabi.sample.gnu.coreutils.whoami;

import sabi.Err;

public interface WhoamiDax {

  enum Mode {
    Normal,
    Help,
    Version,
  }

  default Mode getMode() throws Err { return Mode.Help; }
  default String getEffectiveUserId() throws Err { return ""; }
  default String getUserNameByUserId(String uid) throws Err { return ""; }
  default void printUserName(String userName) {}
  default void printHelp() {}
  default void printVersion() {}
}
