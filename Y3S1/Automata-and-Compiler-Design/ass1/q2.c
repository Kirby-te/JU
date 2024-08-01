#include <stdio.h>
#include <stdlib.h>

#include "header.h"
#include "stack.h"

int str_varify(char[], int);// alphabatic string
int check_counts_equal(char[], char, char, int);

int main() {
    char str[SIZE-1];
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

    int equal_check = check_counts_equal(str, char1, char2, len);
    if (equal_check) {
        printf("Number of %c's and %c's are equal.\n", char1, char2);
    } else {
        printf("Number of %c's and %c's aren't equal.\n", char1, char2);
    }

    if (count_char1%2==0 && count_char2%2==0) {
        printf("Count of %c's and %c's are even.\n", char1, char2);
    } else {
        printf("Count of %c's and %c's aren't even.\n", char1, char2);
    }

    return 0;
}

int str_varify(char str[], int len) {
    int i;
    for (i = 0; i < len; i++)
        if ((str[i] - 'a' < 0 ) || (str[i] - 'a' > 25))
            return 1;
    return 0;
}

int check_counts_equal(char str[], char char1, char char2, int len) {
    if (len == 0) {
        return 0;
    }

    struct Stack *s = createStack();
    int idx = 0;
    char flag;

    while (idx < len) {
        char ch = str[idx];
        if (ch == char1 || ch == char2) {
            flag = ch;
            push(s, ch);
            idx++;
            break;
        }
        idx++;
    }

    while (idx < len) {
        char ch = str[idx];
        if (ch == flag) {
            push(s, ch);
        } else if (ch == char1 || ch == char2){
            pop(s);
        }
        idx++;
    }

    int result = isEmpty(s);
    freeStack(s);
    free(s);
    return result;
}
