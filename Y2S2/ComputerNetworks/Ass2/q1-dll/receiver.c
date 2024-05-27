#include "global.h"

void receiveFrame(Frame *frame) {
    printf("Frame received, sequence number: %d\n", frame->seq);
}

void extractData(Frame *frame, char *data) {
    strncpy(data, frame->data, FRAME_SIZE);
}

void deliverData(char *data) {
    printf("Received data: %s\n", data);
}

