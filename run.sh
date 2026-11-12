#!/usr/bin/env sh
set -e
cd "$(dirname "$0")"
rm -rf out
mkdir -p out
# Main.java'dan başlayarak src altındaki tüm sınıflar derlenir.
javac -encoding UTF-8 --release 17 -d out -sourcepath src src/webserver/Main.java
java -Dstdout.encoding=UTF-8 -cp out webserver.Main
