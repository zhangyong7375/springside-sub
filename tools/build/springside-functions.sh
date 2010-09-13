#
# Check the program location one by one.
# Return 0 if all found
# Return 1 if one of them missing
#
function generic_msg() {
	local msg=""
	for msg in $* ; do
		printf "$msg "
	done
	echo ""
}

function debug_msg() {
	generic_msg "[DEBUG]" $*
}

function info_msg() {
	generic_msg "[INFO]" $*
}

function warning_msg() {
	generic_msg "[WARN]" $*
}

function error_msg() {
	generic_msg "[ERROR]" $*
}

function check_prog() {
	local NP=""
	local P=""
	for NP in $* ; do
		P=`which $NP 2>&1`
		if [ $? -ne 0 ] ; then
			error_msg "Program $NP is not found"
			error_msg "Change the environemt by using springside-env.sh"
			return 1
		else
			info_msg "Program $NP is found at $P"
		fi
	done
}

function check_env() {
	local NE=""

	for NE in $* ; do
		if [ "x${!NE}" == "x" ] ; then
			error_msg "Environment $NE is not found"
			error_msg "Change the environment by using springside-env.sh"
			return 1
		else
			info_msg "${NE} = ${!NE}"
		fi
	done
}
