#!/bin/sh
#Executes a couple of tests

#The result file
result="result"
program="shearsort.jar"

#Run the program for the given dimensions with various number of threads
function run {
	echo "####################" >> $result
	echo "####################"
	for threads in 1 2 4 8 16 32
	do
		echo "--------------------" >> $result
		echo -n "Calculating for mesh $1 X $2 with $threads threads..."
		echo "Calculating for mesh $1 X $2 with $threads threads" >> $result
		java -jar $program $1 $2 $threads >> $result
		echo "Done!"
	done
}

#Clear thre result file
function clear {
	echo "" > $1
}

#Executing all test
function main {

	clear $result

	run 10 10	# 100
	run 10 100	# 1 000
	run 100 100	# 10 000
	run 100 1000	# 100 00
	run 1000 1000	# 1 000 000
	run 1000 2000	# 2 000 000
	run 2000 2000	# 4 000 000
	run 2000 3000	# 6 000 000
	run 2000 4000	# 8 000 000
	run 3000 3000	# 9 000 000
	run 2000 5000	# 10 000 000
	run 3000 4000	# 12 000 000
	run 3000 5000	# 15 000 000
	run 4000 4000	# 16 000 000
	run 3000 6000	# 18 000 000
}

#The main method
if [ $# -eq 0 ]
then
	echo ""
	echo "Using defalut arguments:"
	echo "program = $program"
	echo "result = $result"
	main
	echo "Done!"
	echo ""
elif [ $# -eq 2 ]
then
	echo ""
	program=$1
	result=$2
	echo "program = $program"
	echo "result = $result"
	main
	echo "Done!"
	echo ""
else
	echo ""
	echo "Wrong input!!"
	echo "1.) Enter two arguments - the path to the executable jar file and the path to the result file."
	echo "2.) Do not enter any arguments - use default values:"
	echo "program = $program"
	echo "result = $result"
	echo ""
	exit 1
fi
