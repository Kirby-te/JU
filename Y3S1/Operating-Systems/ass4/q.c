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
 * Input Description: None
 *
 * Output Description: 
 *
 *
 * Compilation Command: gcc q.c
 * Execution Sequence: ./a.out
 *
 *
 * Sample Input:
 * Sample Output:
 /-----------------------------------
File created: somewhat-big-file

Success: Offset 0x0037a371, Value 0x11
Success: Offset 0x00004cb9, Value 0xfa
Success: Offset 0x004fede4, Value 0xb7
Success: Offset 0x000fdf96, Value 0x30
Success: Offset 0x0020ba23, Value 0xf3
Success: Offset 0x0017a268, Value 0x2d
Success: Offset 0x003872c9, Value 0xfd
Success: Offset 0x001f7e1b, Value 0x1c
Success: Offset 0x00197f07, Value 0x43
Success: Offset 0x0077bf80, Value 0x73
Success: Offset 0x00684b58, Value 0xda
Success: Offset 0x000829d7, Value 0xdd
^C
 -----------------------------------/
 *
 *
 * Observe Page Faults:
 * 1. Using "sar -B 1"
 /-----------------------------------
02:18:52 PM  pgpgin/s pgpgout/s   fault/s  majflt/s  pgfree/s pgscank/s pgscand/s pgsteal/s    %vmeff
02:18:53 PM      0.00      0.00   1629.00      0.00   1322.00      0.00      0.00      0.00      0.00
02:18:54 PM      0.00    280.00   4584.00      9.00   4436.00      0.00      0.00      0.00      0.00
02:18:55 PM      0.00    148.00   4355.00     14.00   9026.00      0.00      0.00      0.00      0.00
02:18:56 PM      0.00      0.00   1605.00     11.00   1597.00      0.00      0.00      0.00      0.00
02:18:57 PM      0.00    204.00   1927.00      9.00   1863.00      0.00      0.00      0.00      0.00
02:18:58 PM      0.00      0.00   3995.00      8.00   3675.00      0.00      0.00      0.00      0.00
02:18:59 PM      0.00    152.00   2897.00      8.00   3435.00      0.00      0.00      0.00      0.00
02:19:00 PM      0.00     28.00   4260.00      5.00   3372.00      0.00      0.00      0.00      0.00
02:19:01 PM      0.00      8.00   2163.00      4.00   3439.00      0.00      0.00      0.00      0.00
02:19:02 PM      0.00      0.00    503.00      1.00    747.00      0.00      0.00      0.00      0.00
02:19:03 PM      0.00      0.00    783.00      2.00   1167.00      0.00      0.00      0.00      0.00
02:19:04 PM      0.00      0.00   3664.00      2.00   3936.00      0.00      0.00      0.00      0.00
02:19:05 PM      0.00      0.00   4278.00      1.00   4362.00      0.00      0.00      0.00      0.00
02:19:06 PM      0.00    144.00   1153.00      0.00   1241.00      0.00      0.00      0.00      0.00
02:19:07 PM      0.00      0.00    469.00      0.00    820.00      0.00      0.00      0.00      0.00
02:19:08 PM      0.00      0.00   1226.00      4.00   1472.00      0.00      0.00      0.00      0.00
02:19:09 PM      0.00      0.00   4399.00      0.00   4801.00      0.00      0.00      0.00      0.00
02:19:10 PM      0.00    160.00   7825.00      0.00   4855.00      0.00      0.00      0.00      0.00
02:19:11 PM      0.00      0.00   1785.00      0.00   2538.00      0.00      0.00      0.00      0.00
02:19:12 PM      0.00    232.00    879.00      0.00    775.00      0.00      0.00      0.00      0.00
^C

Average:         0.00     67.80   2718.95      3.90   2943.95      0.00      0.00      0.00      0.00
 -----------------------------------/
 * 
 * 
 * 2. Using custom script "./page_fault_listner.sh"; to focus on just one process that is this one
 /-----------------------------------
process 'a.out' has started with pid-'152857'


 pid     minor_fault     major_fault
152857           88              0
152857           88              0
152857           88              0
152857           88              0
152857           91              8
152857           97              23
152857           106             34
152857           115             46
152857           126             55
152857           143             58
152857           161             61
152857           177             63
152857           193             67
152857           210             70
152857           228             70


 pid     minor_fault     major_fault
152857           248             70
152857           264             74
152857           283             74
process 'a.out' has stopped running
 -----------------------------------/
 * 
 *
***********************************************************************/

#define _GNU_SOURCE

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>
#include <time.h>

#define FILE_SIZE (8L * 1024 * 1024)
#define PROCESS_INTERVAL 50000

void error_exit(const char*);

int main() {
    int file_fd;
    char *file_name = "somewhat-big-file";
    int file_flags = O_CREAT | O_RDWR | O_TRUNC;
    int file_mode = 0666;
    char *file_in_memory;
    off_t offset;
    unsigned char write_val, read_val;

    srand(time(NULL));

    // Creating file
    if ((file_fd = open(file_name, file_flags, file_mode)) < 0) {
        error_exit("Error: file opening");
    }

    printf("File created: %s\n\n", file_name);

    if (fallocate(file_fd, 0, 0, FILE_SIZE) < 0) {
        error_exit("Error: fallocate");
    }

    // mmap the file
    file_in_memory = mmap(NULL, FILE_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, file_fd, 0);
    if (file_in_memory == MAP_FAILED) {
        error_exit("Error: mmap");
    }

    unlink(file_name);
    usleep(5000000);

    while (1) {
        offset = rand() % FILE_SIZE;
        write_val = rand() % 256;

        file_in_memory[offset] = write_val;

        read_val = file_in_memory[offset];

        // Verify the values
        if (write_val != read_val) {
            fprintf(stderr, "Error: Verification failed at offset 0x%08lx (Expected: 0x%02x, Found: 0x%02x)\n", offset, write_val, read_val);
            munmap(file_in_memory, FILE_SIZE);
            close(file_fd);
            exit(EXIT_FAILURE);
        }

        printf("Success: Offset 0x%08lx, Value 0x%02x\n", offset, write_val);

        // small delay to control the pace
        usleep(PROCESS_INTERVAL);
    }

    // cleaing up
    munmap(file_in_memory, FILE_SIZE);
    close(file_fd);

    return 0;
}

void error_exit(const char *error_msg) {
    perror(error_msg);
    exit(EXIT_FAILURE);
}