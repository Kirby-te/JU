/***********************************************************************
 * Team Number: 
 * Member: 
 * 
 * Date: 05.08.2024
 *
 * Assignment: 2A
 *       [Catch the signal ‘SIGINT’ and display “Ha Ha, Not Stopping”. 
 *        Use ‘signal’ system call. Always use “perror” to check the return status of a library/system call.]
 *
 * Input Description:
 *
 * Output Description:
 *
 *
 * Compilation Command:
 * Execution Sequence:
 *
 *
 * Sample Input:
 * Sample Output:
 /-----------------------------------

 -----------------------------------/
***********************************************************************/

#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>

void handle_sigint(int);

int main(int argc, char **argv) {
    if (signal(SIGINT, handle_sigint) == SIG_ERR) {
        perror("signal");
        exit(1);
    }

    while (1) {
        printf("Somebody stop me\n");
        sleep(2);
    }

    return 0;
}

void handle_sigint(int sig) {
    printf("Ha Ha, Not Stopping\n");
}