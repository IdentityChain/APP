#!/bin/sh

## resolve links
PRG="$0"

# need this for relative symlinks
while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG="`dirname "$PRG"`/$link"
  fi
done

saveddir=`pwd`

AW_COMPONENT_HOME=`dirname "$PRG"`/..

# make it fully qualified
AW_COMPONENT_HOME=`cd "$AW_COMPONENT_HOME" && pwd`

JRE_LOCATION="${AW_COMPONENT_HOME}/../jre"

cd "$saveddir"

if [ -f "${AW_COMPONENT_HOME}/bin/application.pid" ] ; then
  ps -p "$(cat "${AW_COMPONENT_HOME}/bin/application.pid")" >/dev/null
  if [ "$?" = "0" ] ; then
    echo "Existed [${AW_COMPONENT_HOME}/bin/application.pid], and check process [$(cat "${AW_COMPONENT_HOME}/bin/application.pid")] is running" >&2
    exit 1
  fi
fi

if [ -d "${JRE_LOCATION}" ] ; then
    JAVA_HOME="${JRE_LOCATION}"
fi

if [ -z "$JAVA_HOME" ] ; then
  JAVACMD=`which java`
else
  JAVACMD="$JAVA_HOME/bin/java"
fi

if [ ! -x "$JAVACMD" ] ; then
  echo "The JAVA_HOME environment variable is not defined correctly" >&2
  echo "This environment variable is needed to run this program" >&2
  echo "NB: JAVA_HOME should point to a JDK not a JRE" >&2
  exit 1
fi


FILE_PID=${AW_COMPONENT_HOME}/bin/application.pid
if [[ -f "$FILE_PID" ]]; then
rm -rf "$FILE_PID"
fi

JAVA_OPTS=" ${JAVA_OPTS} -Xms128m -Xmx2048m "
AW_LOG_DIR=${AW_COMPONENT_HOME}/logs
BOOT_JAR=`echo "${AW_COMPONENT_HOME}"/lib/*.jar`

export SPRING_OPTS="-DLOG_PATH=${AW_LOG_DIR} -Dspring.pid.file=${FILE_PID} -Dspring.config.location=${AW_COMPONENT_HOME}/conf/"
exec "$JAVACMD" \
      ${JAVA_OPTS} -DAW_COMPONENT_HOME="${AW_COMPONENT_HOME}"\
      ${SPRING_OPTS} -jar "${BOOT_JAR}"




