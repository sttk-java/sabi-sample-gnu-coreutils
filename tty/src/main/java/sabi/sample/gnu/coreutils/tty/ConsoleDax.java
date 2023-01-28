package sabi.sample.gnu.coreutils.tty;

import sabi.Err;

public interface ConsoleDax extends TtyDax {

  default void printVersion() {
    System.out.print(
      "tty 1.0\n" +
      "Copyright (C) 2023 sttk-go project.\n" +
      "License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>.\n" +
      "This is free software: you are free to change and redistribute it.\n" +
      "There is NO WARRANTY, to the extent permitted by law.\n" +
      "Written by Takayuki Sato.\n" +
    "");
  }

  default void printHelp() {
    System.out.print(
      "Usage: tty [OPTION]...\n" +
      "Print the file name of the terminal connected to standard input.\n" +
      "      -s, --silent, --quiet   print nothing, only return an exit status\n" +
      "      --help        display this help and exit\n" +
      "      --version     output version information and exit\n" +
    "");
  }

  default void printTtyName(String ttyname) throws Err {
    System.out.println(ttyname);
  }

  default void printNotTty(Err err) {
    System.out.println("not a tty");
  }

  default void printTtyError(Err err) {
    System.out.println("tty: fail to get ttyname");
  }

  default void printModeError(Err err) {
    System.out.println(String.format("tty: illegal option: %s\n", err.get("option")));
    printHelp();
  }
}
