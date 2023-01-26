#!/usr/bin/env bash

## env.sh
## Copyright (C) 2023 Takayuki Sato. All Rights Reserved.
##
## This shell script register the directory of the dynamic link library:
## `libcore.jnilib` to the environment variable `DYLD_LIBRARY_PATH`.
## This shell script is needed to run the native commands built by the sub-
## projects with the dynamic link library.
##

CWD=$(cd $(dirname $0); pwd)

if [[ "$DYLD_LIBRARY_PATH" == "" ]]; then
  export DYLD_LIBRARY_PATH=${CWD}/lib/target/nar/lib-0.1.0-x86_64-MacOSX-jni/lib/x86_64-MacOSX/jni
else
  export DYLD_LIBRARY_PATH=${CWD}/lib/target/nar/lib-0.1.0-x86_64-MacOSX-jni/lib/x86_64-MacOSX/jni:$DYLD_LIBRARY_PATH
fi
