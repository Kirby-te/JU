/***********************************************************************
 *
 * Name: 
 * Roll: 
 * 
 * Date:
 *
 * Assignment: 4, mmap & page fault
 * 
 *      Objective: 
 *          1. Craete a big file (about 8GB) using ‘fallocate’.
 *          2. Using mmap() call, map the file in the virtual address space.
 *          3. This file should be written with a single byte value (say X) at a specific offset (say F). X (between 0-255) & F (between 0 and 8G) are genearted using random function.
 *          4. Now, read data (say X`) from from the same specific offset (F) then verify that X and X` are the same. 
 *          5. In case of verification failure, an error message is to be printed and also the program should terminate. 
 *          6. Repeat steps 3 to 5 indefinitely
 *          7. In another terminal execute the command ‘sar –B 1’ (at 1 second gap) to observe for the page fault.
 *             Start the comamnd before program execution. So, one can observe that the page faults are increasing.
 *      
 *        (Display the offset as hex number format; comment the output of the 'sar' command)
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

#define _GNU_SOURCE

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>

#define FILE_SIZE (8l * 1024 * 1024 * 1024) // 8GB

void error_exit(const char*);

int main() {
    int file_fd, rc;
    char *file_name = "somewhat_big_file";
    int file_oflags = O_CREAT | O_RDWR;

    // Creating file
    if ((file_fd = open(file_name, file_oflags, 0666)) < 0) {
        error_exit("[-]Creating file");
    }

    printf("[+]File created: %s\n", file_name);

    if ((rc = fallocate(file_fd, 0, 0, FILE_SIZE)) < 0) {
        error_exit("[-]fallocate");
    }

    // map the file
    

    // cleaing up
    unlink(file_name);
}

void error_exit(const char *error_msg) {
    perror(error_msg);
    exit(EXIT_FAILURE);
}