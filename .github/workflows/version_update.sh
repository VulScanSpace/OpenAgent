OLD_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
NEW_VERSION=$1

echo "curent path: `pwd`, change version $OLD_VERSION to $NEW_VERSION"
mvn -q versions:set -DnewVersion="${NEW_VERSION}"
mvn -q versions:update-child-modules
mvn -q versions:commit