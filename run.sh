#!/usr/bin/env sh
set -e
cd "$(dirname "$0")"
rm -rf out
mkdir -p out
javac -encoding UTF-8 --release 17 -d out $(find src -name "*.java")
java -cp out webserver.Main
