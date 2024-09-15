#!/bin/bash

OUTPUT_FILE="a.out"                 # process name
COUNTER_INTERVAL=15                 # interval to print the header
COUNTER="$COUNTER_INTERVAL"         # counter to print header after certain intervals
PID=$(pgrep "$OUTPUT_FILE")         # get the process id

# exit if process doesn't exist
if [ -z "$PID" ]; then
    echo "process '$OUTPUT_FILE' not found"
    exit 1
fi

echo "process '$OUTPUT_FILE' has started with pid-'$PID'"

# indifinitly check for page faults
while true; do
    # check if still running
    PID=$(pgrep "$OUTPUT_FILE")
    if ! kill -0 "$PID" 2>/dev/null; then
        echo "process '$OUTPUT_FILE' has stopped running"
        exit 1
    fi

    CURRENT_TIME=$(date +%H:%M:%S)

    # print header after every interval 
    if [ "$COUNTER" -ge "$COUNTER_INTERVAL" ]; then
        echo -e "\n\n time \t\t state \t minor_fault \t\t major_fault \t virtual_memory_size \t resident_set_size"
        COUNTER=0                   # reset after printing header
    fi

    # extract and print minor and major page faults
    stat=$(awk -v curr_time="$CURRENT_TIME" '{print curr_time, "\t", $3, "\t\t", $10, "\t\t", $12, "\t\t", $23, "\t\t", $24}' /proc/$PID/stat)
    echo -e "$stat"

    # increment the counter
    COUNTER=$((COUNTER + 1))

    # wait for 1 second
    sleep 1
done