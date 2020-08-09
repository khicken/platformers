@echo on
javac src/*.java -d build
cd build
java src/Main
pause