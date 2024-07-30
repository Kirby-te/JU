#include <stdio.h>
#include <stdlib.h>

int str_varify(char[], int);// alphabatic string
int str_length(char[]);
int count_chars(char[], char);
int check_counts_equal(char[], int);

int main() {
    char str[33];
    char char1 = 'a', char2 = 'b';
    int len, count_char1, count_char2;

    printf("Enter a alphabatic string: ");
    scanf("%s", str);

    len = str_length(str);

    int check = str_varify(str, len);
    if(check) {
        printf("Invalid string %s. Enter a alphabatic string.\n", str);
        exit(0);
    }

    count_char1 = count_chars(str, char1);
    count_char2 = count_chars(str, char2);

    printf("Number of %c's: %d\n", char1, count_char1);
    printf("Number of %c's: %d\n", char2, count_char2);

    return 0;
}

int str_length(char str[]) {
    int len = 0;
    while (str[len] != '\0')
        len++;
    return len;
}

int str_varify(char str[], int len) {
    int i;
    for (i = 0; i < len; i++)
        if ((str[i] - 'a' < 0 ) || (str[i] - 'a' > 25))
            return 1;
    return 0;
}

int count_chars(char str[], char ch) {
    int i, count = 0;
    for (i = 0; str[i] != '\0'; i++)
        if (str[i] == ch)
            count++;
    
    return count;
}
