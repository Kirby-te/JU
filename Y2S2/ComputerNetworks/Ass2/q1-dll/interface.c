#include "sender.c"
#include "receiver.c"

int main() {
    char data[FRAME_SIZE];
    int counter = 10;

    for (int i=0; i<counter; i++) {
        Frame frame;

        waitForEvent();

        getData(data);
        makeFrame(i, data, &frame);
        sendFrame(&frame);

        waitForEvent();

        receiveFrame(&frame);
        extractData(&frame, data);
        deliverData(data);
    }

    return 0;
}
