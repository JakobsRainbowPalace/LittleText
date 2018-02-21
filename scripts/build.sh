# Run this script inside the project folder to build it

BUILD="build"
RESOURCES="resources"

# Cleanup build directory
if [ -d "$BUILD" ]; then
  rm -fr ./build/*
else
  mkdir ./build
fi

# Generate source files
echo "> Building..."
cd src
javac Main.java -d ../build
cd ..
cp -r ./resources/* ./build
echo "> Done."
