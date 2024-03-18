#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<arpa/inet.h>

#define BUFFER_SIZE 1024

int main() {
	int server_fd, new_socket;
	struct sockaddrr_in address;
	int addrlen = sizeof(address);
	char bufer[BUFFER_SIZE] = {0};
	char *measage = "Hello form server";
		

}
