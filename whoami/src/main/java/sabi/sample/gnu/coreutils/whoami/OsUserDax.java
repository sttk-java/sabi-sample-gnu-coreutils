package sabi.sample.gnu.coreutils.whoami;

import sabi.sample.gnu.coreutils.lib.OsDax;

import sabi.Dax;
import sabi.Err;

public interface OsUserDax extends OsDax, WhoamiDax {

  public record InvalidUserId(String uid) {}

  @Override
  default String getEffectiveUserId() throws Err {
    var conn = getOsDaxConn("os");
    return String.valueOf(conn.getEuid());
  }

  @Override
  default String getUserNameByUserId(String uid) throws Err {
    var conn = getOsDaxConn("os");
    Integer iUid = null;
    try {
      iUid = Integer.valueOf(uid);
    } catch (Exception e) {
      throw new Err(new InvalidUserId(uid));
    }
    var user = conn.getLookupId(iUid);
    if (user == null) {
      return "";
    }
    return user.userName();
  }
}
