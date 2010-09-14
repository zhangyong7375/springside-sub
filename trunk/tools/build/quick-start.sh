#!/bin/bash
PROG=$0
CURDIR=`pwd`
PROGDIR=`dirname $PROG`
SRCDIR=

MINI_SERVICE_PORT=8084
MINI_WEB_PORT=8085
MINI_SHOWCASE_PORT=8086

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
	mkdir -p "$HOME/.m2/repository"
	cp -v -rp "${CENTRAL_REPO}/*" "$HOME/.m2/repository"
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


#info_msg "[Step 3] Configure embedded database"
#cd tools/h2
#mvn ${OFF_LINE} exec:java
#if [ $? -ne 0 ] ; then
#	exit 1
#fi
#cd ${SRCDIR}

#
# <description> <folder> <jetty port>
#
function start_service(){
	if [ $# -ne 3 ] ; then
		error_msg "Invalid parameter number for start_service: $#"
		exit 1
	fi
	local DESC=$1
	local FOLDER="examples/$2"
	local JETTY_PORT=$3
	local BUILD_XML_FILE="${SRCDIR}/$FOLDER/bin/build.xml"
	local POM_XML_FILE="${SRCDIR}/$FOLDER/pom.xml"

	info_msg "${DESC}"

	if [ ! -e ${BUILD_XML_FILE} ]; then
		error_msg "Missing build.xml at ${BUILD_XML_FILE}"
		exit 1
	fi

	if [ ! -e ${POM_XML_FILE} ] ; then
		error_msg "Missing pom.xml at ${POM_XML_FILE}"
		exit 1
	fi
	ant -f ${BUILD_XML_FILE} init-db
	nohup mvn ${OFF_LINE} -f ${POM_XML_FILE} -Djetty.port=${JETTY_PORT} jetty:run &> ${SRCDIR}/${FOLDER}/mvn.log &
	sleep 2
}

#Start h2 database
nohup mvn ${OFF_LINE} -f ${SRCDIR}/tools/h2/pom.xml exec:java &> ${SRCDIR}/h2.mvn.log &

sleep 1

start_service "[Step 4] Mini-Service" "mini-service" ${MINI_SERVICE_PORT}
start_service "[Step 5] Mini-Web" "mini-web" ${MINI_WEB_PORT}
start_service "[Step 6] Showcase" "showcase" ${MINI_SHOWCASE_PORT}

info_msg "http://localhost:${MINI_SERVICE_PORT}/mini-service"
info_msg "http://localhost:${MINI_WEB_PORT}/mini-web"
info_msg "http://localhost:${MINI_SHOWCASE_PORT}/showcase"

