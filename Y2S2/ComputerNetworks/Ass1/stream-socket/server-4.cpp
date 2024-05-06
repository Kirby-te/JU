#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <fcntl.h>

using namespace std;

int main(int argc, char** argv) {
    if (argc < 4) {
        cerr << "Syntax: " << argv[0] << " <IP> <Port> <Clients>(optional)" << endl;
        return EXIT_FAILURE;
    }

    
}