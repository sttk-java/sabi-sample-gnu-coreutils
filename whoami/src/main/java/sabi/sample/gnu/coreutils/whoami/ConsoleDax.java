package sabi.sample.gnu.coreutils.whoami;

import sabi.Dax;

public interface ConsoleDax extends Dax, WhoamiDax {

  @Override
  default void printUserName(final String userName) {
    System.out.println(userName);
  }

  @Override
  default void printVersion() {
    System.out.print("whoami 1.0\n" +
      "Copyright (C) 2023 sttk-java project.\n" +
      "License GPLv3+: GNU GPL version 3 or later <https://gnu.org/licenses/gpl.html>.\n" +
      "This is free software: you are free to change and redistribute it.\n" +
      "There is NO WARRANTY, to the extent permitted by law.\n" +
      "\n" +
      "Written by Takayuki Sato.\n" +
    "");
  }

  @Override
  default void printHelp() {
    System.out.print("Usage: ./whoami [OPTION]...\n" +
      "Print the user name associated with the current effective user ID.\n" +
      "Same as id -un.\n" +
      "\n" +
      "      --help        display this help and exit\n" +
      "      --version     output version information and exit\n" +
    "");
  }
}
