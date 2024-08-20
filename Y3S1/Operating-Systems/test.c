#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <sys/types.h>

int main() {
    int i = 0;

    pid_t pid = fork();

    i++;

    printf("%d -> %d\n", getpid(), i);

    if (pid != 0) {
        i = 10;
    }

    printf("%d -> %d\n", getpid(), i);
    
    return 0;
}
