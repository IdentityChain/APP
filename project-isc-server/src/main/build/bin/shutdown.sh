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

BOOT_JAR=`echo "${AW_COMPONENT_HOME}"/lib/*.jar`

# donot check pid file is existed, shutdown.
AW_LOG_DIR=${AW_COMPONENT_HOME}/logs
echo "Try shutdown using http"
#"$JAVACMD" \
#  $JAVA_OPTS -DAW_COMPONENT_HOME="${AW_COMPONENT_HOME}"\
#  -DLOG_PATH="${AW_LOG_DIR}"\
#  -jar "${BOOT_JAR}" "shutdown" "--spring.config.location=${AW_COMPONENT_HOME}/conf/"
sleep 5

# shutdown again using kill
if [ -f "${AW_COMPONENT_HOME}/bin/application.pid" ] ; then
  echo "Try shutdown using kill"
  PID="$(cat "${AW_COMPONENT_HOME}/bin/application.pid")"
  kill ${PID}
  echo "Shutdown process [${PID}]"
  rm -fr "${AW_COMPONENT_HOME}/bin/application.pid"
fi