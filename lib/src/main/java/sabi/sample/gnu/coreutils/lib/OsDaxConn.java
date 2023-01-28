package sabi.sample.gnu.coreutils.lib;

import sabi.DaxConn;
import sabi.Err;

class Passwd {
  public String name;
  public String passwd;
  public int uid;
  public int gid;
  public String gecos;
  public String dir;
  public String shell;
}

class TextData {
  public String value;
}

public class OsDaxConn implements DaxConn {

  public record FailToGetPasswd(int errno) {};
  public record FailToGetTtyname(int errno) {};

  public OsDaxConn() {
  }

  @Override
  public void commit() throws Err {
  }

  @Override
  public void rollback() {
  }

  @Override
  public void close() {
  }


  static {
    System.loadLibrary("core");
  }

  public native int getEuid();
  protected native int getPwdByUid(int uid, Passwd pwd);
  protected native int ttyname(int fd, TextData txt);

  public OsUser getLookupId(final int uid) throws Err {
    var pwd = new Passwd();
    var errno = getPwdByUid(uid, pwd);
    if (errno != 0) {
      throw new Err(new FailToGetPasswd(errno));
    }
    return new OsUser(uid, pwd.gid, pwd.name, pwd.gecos, pwd.dir);
  }

  public String getTtyName(final int fd) throws Err {
    var txt = new TextData();
    var errno = ttyname(fd, txt);
    if (errno != 0) {
      throw new Err(new FailToGetTtyname(errno));
    }
    return txt.value;
  }
}
