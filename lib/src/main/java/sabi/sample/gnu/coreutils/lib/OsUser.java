package sabi.sample.gnu.coreutils.lib;

public record OsUser(
  int uid,
  int gid,
  String userName,
  String name,
  String homeDir
) {}
