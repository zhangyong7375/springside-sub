#!/bin/bash
PROG=$0
CURDIR=`pwd`
PROGDIR=`dirname $PROG`
SRCDIR=

cd $PROGDIR
PROGDIR=`pwd`
SRCDIR="$PROGDIR/../.."

cd "$SRCDIR" || ( echo "Missing $SRCDIR" && exit 1 )
SRCDIR=`pwd`

cd $CURDIR

CENTRAL_REPO=$SRCDIR/tools/maven/central-repository

#
# load the environment setting
#
[ -e "${PROGDIR}/springside-env.sh" ] && "source ${PROGDIR}/springside-env.sh"

[ -e "${PROGDIR}/springside-functions.sh" ] || ( echo "Missing springside-functions.sh" && exit 1 )
source "${PROGDIR}/springside-functions.sh"

NEED_PROGS=('java' 'mvn' 'ant')
NEED_ENVS=('JAVA_HOME')

check_prog ${NEED_PROGS[*]}

if [ $? -ne 0 ] ; then
	exit 1
fi

check_env ${NEED_ENVS[*]}

if [ $? -ne 0 ] ; then
	exit 1
fi

# Copy the central-repository to user's home
if [ -d "$CENTRAL_REPO" ] ; then
	cp -v -rp "${CENTRAL_REPO}" "$HOME/.m2/repository"
else
	info_msg "Skip copying ${CENTRAL_REPO}... Missing"
fi

cd $SRCDIR
info_msg "[Step 2] Compile springside 3 modules and examples"
mvn ${OFF_LINE} clean install -Dmaven.test.skip=true
if [ $? -ne 0 ] ; then
	exit 1
fi

mvn ${OFF_LINE} eclipse:clean eclipse:eclipse
if [ $? -ne 0 ] ; then
	exit 1
fi


info_msg "[Step 3] Configure embedded database"
cd tools/h2
mvn ${OFF_LINE} exec:java
if [ $? -ne 0 ] ; then
	exit 1
fi
cd ${SRCDIR}

info_msg "[Step 4] Mini-Service"
cd examples\mini-service
ant -f bin/build.xml init-db 
nohup mvn ${OFF_LINE} -Djetty.port=8084 jetty:run &

cd ${SRCDIR}

info_msg "[Step 5] Mini-Web"
cd examples\mini-web
ant -f bin/build.xml init-db 
nohup mvn ${OFF_LINE} -Djetty.port=8085 jetty:run &
cd ${SRCDIR}

info_msg "[Step 6] Showcase"
cd examples\showcase
ant -f bin/build.xml init-db
nohup mvn ${OFF_LINE} -Djetty.port=8086 jetty:run &
cd ${SRCDIR}

info_msg "http://localhost:8084/mini-service"
info_msg "http://localhost:8085/mini-web"
info_msg "http://localhost:8086/showcase"

