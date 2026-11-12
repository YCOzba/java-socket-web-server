@echo off
chcp 65001 >nul
cd /d "%~dp0"
if exist out rmdir /s /q out
mkdir out
rem Main.java'dan başlayarak src altındaki tüm sınıflar derlenir.
rem Dosya listesi kullanılmadığı için klasör yolunda boşluk veya Türkçe karakter olması sorun çıkarmaz.
javac -encoding UTF-8 --release 17 -d out -sourcepath src src\webserver\Main.java
if errorlevel 1 (
    echo Derleme basarisiz.
    pause
    exit /b 1
)
java -Dstdout.encoding=UTF-8 -cp out webserver.Main
