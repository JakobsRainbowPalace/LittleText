START="build/Main.class"
DIR="build"
FILE="Main"

if [ -f "$START" ]; then
  echo "> Executing..."
  cd "$DIR"
  java "$FILE"
else
  scripts/build.sh
  scripts/start.sh
fi