#!/usr/bin/env bash

clean() {
  mvn clean
}

compile() {
  mvn compile
}

test() {
  mvn test
}

jar() {
  mvn package
}

javadoc() {
  mvn javadoc:javadoc
}

deps() {
  mvn versions:deplay-dependency-updates
}

sver() {
  serialver -classpath target/classes $1
}

native_compile() {
  mvn -Pnative package
}

native_test() {
  mvn -Pnative test
}

case "$1" in
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
'')
  clean
  jar
  javadoc
  ;;
esac
