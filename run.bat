@echo off
chcp 65001 >nul
if exist out rmdir /s /q out
mkdir out
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 --release 17 -d out @sources.txt
if errorlevel 1 (
    del sources.txt
    echo Derleme basarisiz.
    exit /b 1
)
del sources.txt
java -Dstdout.encoding=UTF-8 -cp out webserver.Main
