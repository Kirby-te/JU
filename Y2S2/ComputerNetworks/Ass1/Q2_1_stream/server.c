#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>

// port for listening
#define PORT 8080

int main(int argc, char const* argv[]) {
    // creater a scoket
    // AF_INET: loop back from local host
    // TCP
    // IP protocol: 0
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == -1) {
        perror("Need proer socket");
        exit(1);
    }

    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = inet_addr("");
    addr.sin_port = htons
}