#include <stdio.h>
#include <stdlib.h>

#define SIZE 8

struct stack {
    int top = -1;
    int array[SIZE];
};

void push(struct stack, int);
int pop(struct stack);
void show(struct stack);

void push(struct stack s, int data) {
    if (s.top == SIZE - 1) {
        printf("\nOverflow!");
    } else {
        s.top = s.top + 1;
        s.array[s.top] = data;
    }
}

int pop(struct stack s) {
    if (s.top == -1) {
        printf("\nUnderflow!");
    } else {
        int data = s.array[s.top];
        s.top = s.top - 1;
        return data;
    }
}

void show(struct stack s) {
    if (s.top == -1) {
        printf("\nUnderflow!");
    } else {
        printf("\nElements present in the stack: \n");
        for (int i = s.top; i >= 0; --i) {
            printf("%d\n", s.array[i]);
        }
    }
}