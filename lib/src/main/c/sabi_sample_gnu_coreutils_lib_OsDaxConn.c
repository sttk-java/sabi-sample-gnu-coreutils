#include <jni.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/errno.h>
#include <pwd.h>
#include "sabi_sample_gnu_coreutils_lib_OsDaxConn.h"

void setIntField
  (JNIEnv *env, jobject jpwd, jclass jpwdCls, const char* name, int v)
{
  jfieldID jfid;
  jfid = (*env)->GetFieldID(env, jpwdCls, name, "I");
  (*env)->SetIntField(env, jpwd, jfid, v);
}

void setStringField
  (JNIEnv *env, jobject jpwd, jclass jpwdCls, const char* name, char *v)
{
  jstring jstr;
  jfieldID jfid;
  jstr = (*env)->NewStringUTF(env, v);
  jfid = (*env)->GetFieldID(env, jpwdCls, name, "Ljava/lang/String;");
  (*env)->SetObjectField(env, jpwd, jfid, jstr);
  (*env)->DeleteLocalRef(env, jstr);
}

JNIEXPORT jint JNICALL Java_sabi_sample_gnu_coreutils_lib_OsDaxConn_getEuid
  (JNIEnv * env, jobject me)
{
  return (jint) geteuid();
}

JNIEXPORT jint JNICALL Java_sabi_sample_gnu_coreutils_lib_OsDaxConn_getPwdByUid
  (JNIEnv *env, jobject me, jint juid, jobject jpwd)
{
  struct passwd pwd;
  struct passwd *ppwd;
  char *buf;
  size_t bufsize;
  int eno;

  jclass jpwdCls;

  jpwdCls = (*env)->GetObjectClass(env, jpwd);

  bufsize = sysconf(_SC_GETPW_R_SIZE_MAX);
  if (bufsize < 0) {
    bufsize = 16384;
  }
  buf = malloc(bufsize);
  if (buf == NULL) {
    eno = ENOMEM;
    goto END;
  }

  eno = getpwuid_r((int) juid, &pwd, buf, bufsize, &ppwd);
  if (eno != 0) {
    goto END;
  }

  if (ppwd == NULL) {
    eno = ENOENT;
    goto END;
  }

  setStringField(env, jpwd, jpwdCls, "name", pwd.pw_name);
  setStringField(env, jpwd, jpwdCls, "passwd", pwd.pw_passwd);
  setIntField(env, jpwd, jpwdCls, "uid", pwd.pw_uid);
  setIntField(env, jpwd, jpwdCls, "gid", pwd.pw_gid);
  setStringField(env, jpwd, jpwdCls, "gecos", pwd.pw_gecos);
  setStringField(env, jpwd, jpwdCls, "dir", pwd.pw_dir);
  setStringField(env, jpwd, jpwdCls, "shell", pwd.pw_shell);

END:
  (*env)->DeleteLocalRef(env, jpwdCls);
  free(buf);

  return eno;
}

JNIEXPORT jint JNICALL Java_sabi_sample_gnu_coreutils_lib_OsDaxConn_ttyname
  (JNIEnv *env, jobject me, jint jfd, jobject jtxt)
{
  int eno;
  size_t bufsize = 512;
  char buf[512];

  jclass jtxtCls;

  jtxtCls = (*env)->GetObjectClass(env, jtxt);

  eno = ttyname_r((int) jfd, buf, bufsize);
  if (eno != 0) {
    goto END;
  }

  setStringField(env, jtxt, jtxtCls, "value", buf);

END:
  (*env)->DeleteLocalRef(env, jtxtCls);

  return eno;
}
