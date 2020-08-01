@echo on
javac src/*.java -d build
cd build
java Main
pause