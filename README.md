This application will connect with translator api to get translation:
list of available translation services so far:
google-translator api.

build-app:

build-app/javafx-build

give a jdk17 binary which includes java fx.

if you want to change for other build jdk:

1º zip jdk-17

zip -r jdk-17.zip jdk17-folder

2º split in several files:

split -b49M jdk-17.zip jdk17.zip

3º maven atrun task will concat and unzip