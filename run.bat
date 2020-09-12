@echo off
echo Compiling. . .
javac src/*.java -d build/
echo Running. . .
cd build/
java Main
pause