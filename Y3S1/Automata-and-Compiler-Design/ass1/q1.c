#include <stdio.h>
#include <stdlib.h>

int bin_varify(char[], int);
int get_bin_length(char[]);
void count_ones_zeros(char[], int*, int*);
int check_counts_equal(char[], int);

int main() {
    char bin_str[33];
    int len, count_one = 0, count_zero = 0;

    printf("Enter a binary string: ");
    scanf("%s", bin_str);

    len = get_bin_length(bin_str);

    int check = bin_varify(bin_str, len);
    if(check) {
        printf("Invalid binary number %s.\n", bin_str);
        exit(0);
    }

    count_ones_zeros(bin_str, &count_one, &count_zero);

    printf("Number of 1's: %d\n", count_one);
    printf("Number of 0's: %d\n", count_zero);

    

    return 0;
}

int get_bin_length(char str[]) {
    int len = 0;

    while (str[len] != '\0') {
        len++;
    }

    return len;
}

int bin_varify(char str[], int len) {
    int i;
    for (i = 0; i < len; i++) {
        if ((str[i] - '0' != 1 ) && (str[i] - '0' != 0)) {
            return 1;
        }
    }
    return 0;
}

void count_ones_zeros(char str[], int *count_one, int *count_zero) {
    int i;
    for (i = 0; str[i] != '\0'; i++) {
        if (str[i] == '1') {
            (*count_one)++;
        } else if (str[i] == '0') {
            (*count_zero)++;
        }
    }
}

int check_counts_equal(char str[], int len) {
    
}
