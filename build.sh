#!/usr/bin/env bash

end() {
  exitcd=$1
  if [[ "$exitcd" != "0" ]]; then
    exit $exitcd
  fi
}

clean() {
  mvn clean
  end $?
}

compile() {
  mvn compile
  end $?
}

test() {
  mvn test
  end $?
}

jar() {
  mvn package
  end $?
}

javadoc() {
  mvn javadoc:javadoc
  end $?
}

deps() {
  mvn versions:deplay-dependency-updates
  end $?
}

sver() {
  serialver -classpath target/classes $1
  end $?
}

native_compile() {
  mvn -Pnative package
  end $?
}

native_test() {
  mvn -Pnative test
  end $?
}

if [ "$#" == "0" ]; then
  clean
  jar
  javadoc
  native_compile
  native_test
else
  for a in "$@"; do
    case "$a" in
    clean)
      clean
      ;;
    compile)
      compile
      ;;
    test)
      test
      ;;
    jar)
      jar
      ;;
    javadoc)
      javadoc
      ;;
    deps)
      deps
      ;;
    sver)
      sver
      ;;
    native)
      case "$2" in
      test)
        native_test
        ;;
      compile | '')
        native_compile
        ;;
      esac
      ;;
    *)
      echo "Bad task: $a"
      exit 1
      ;;
    esac
  done
fi
