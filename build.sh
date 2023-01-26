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
  mvn versions:display-dependency-updates
  end $?
}

#sver() {
#  serialver -classpath target/classes $1
#  end $?
#}

native_package() {
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
  native_package
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
    native_test)
      native_test
      ;;
    native_package)
      native_package
      ;;
    *)
      echo "Bad task: $a"
      exit 1
      ;;
    esac
  done
fi
