package sabi.sample.gnu.coreutils.lib;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

import sabi.DaxConn;
import sabi.Err;

import java.util.List;
import java.util.Arrays;

interface C extends Library {
  C INSTANCE = Native.load("c", C.class);

  int geteuid();
  int getpwuid_r(int uid, Passwd pwd, byte[] buf, int buflen, Passwd ppwd);

  class Passwd extends Structure {
    public String pw_name;
    public String pw_passwd;
    public int pw_uid;
    public int pw_gid;
    public long pw_change;
    public String pw_class;
    public String pw_gecos;
    public String pw_dir;
    public String pw_shell;
    public long pw_expire;
    public int pw_fields;

    @Override
    protected List<String> getFieldOrder() {
      return Arrays.asList(
        "pw_name", "pw_passwd", "pw_uid", "pw_gid", "pw_change", "pw_class",
        "pw_gecos", "pw_dir", "pw_shell", "pw_expire", "pw_fields");
    }
  }
}

public class OsDaxConn implements DaxConn {

  public record FailToGetPasswd(int errno) {};

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

  public int getEuid() {
    return C.INSTANCE.geteuid();
  }

  public OsUser getLookupId(final int uid) throws Err {
    var buf = new byte[65536];
    var pwd = new C.Passwd();
    var ppwd = new C.Passwd();
    int ret = C.INSTANCE.getpwuid_r(uid, pwd, buf, buf.length, ppwd);
    if (ret != 0) {
      throw new Err(new FailToGetPasswd(ret));
    }
    return new OsUser(uid, pwd.pw_gid, pwd.pw_name, pwd.pw_gecos, pwd.pw_dir);
  }
}
