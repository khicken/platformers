@echo off
echo Compiling. . . & echo.
javac -d build/ -cp ".;lib/processing_core.jar" src/*.java 
echo Running. . . & echo.
cd build
java -cp ".;./../lib/processing_core.jar" Main
pause