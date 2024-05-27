#include "global.h"

void getData(char *buffer) {
    printf("\nEnter message: ");
    fgets(buffer, FRAME_SIZE, stdin);
    buffer[strcspn(buffer, "\n")] = '\0';
}

void makeFrame(int seq_number, char *data, Frame *frame) {
    frame->seq = seq_number;
    strncpy(frame->data, data, FRAME_SIZE);
}

void sendFrame(Frame *frame) {
    printf("Sending frame...\n");
}