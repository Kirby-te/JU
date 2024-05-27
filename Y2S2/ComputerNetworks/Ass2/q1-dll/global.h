#ifndef GLOBAL_H
#define GLOBAL_H

#include <stdio.h>
#include <string.h>
#include<unistd.h>

#define FRAME_SIZE 1024

typedef struct {
    int seq; // sequence number
    char data[FRAME_SIZE];
} Frame;

void waitForEvent() {
    sleep(2);
}

#endif