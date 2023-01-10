package sabi.sample.gnu.coreutils.yes;

import sabi.Dax;

public interface ConsoleDax extends Dax, YesDax {

  @Override
  default void printYes() {
    for (;;) {
      System.out.println("y");
    }
  }

  @Override
  default void printWord(String word) {
    for (;;) {
      System.out.println(word);
    }
  }

  @Override
  default void printVersion() {
    System.out.print("Usage: yes [STRING]...\n" +
      "  or:  yes OPTION\n" +
      "Repeatedly output a line with all specified STRING(s), or 'y'\n" +
      "\n" +
      "      --help        display this help and exit.\n" +
      "      --version     output version information and exit.\n" +
      "");
  }

  @Override
  default void printHelp() {
    System.out.print("yes 1.0\n" +
      "Copyright (C) 2023 sttk-java project.\n" +
      "License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>.\n" +
      "This is free software: you are free to change and redistribute it.\n" +
      "There is NO WARRANTY, to the extent permitted by law.\n" +
      "\n" +
      "Written by Takayuki Sato.\n" +
      "");
  }
}
