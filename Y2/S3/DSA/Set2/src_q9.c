#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_SIZE 100

struct Stack {
    int arr[MAX_SIZE];
    int top;
};

void initializeStack(struct Stack* s) {
    s->top = -1;
}

bool isStackEmpty(struct Stack* s) {
    return s->top == -1;
}

bool isStackFull(struct Stack* s) {
    return s->top == MAX_SIZE - 1;
}

void push(struct Stack* s, int item) {
    if (isStackFull(s)) {
        printf("Stack is full. Cannot push.\n");
        return;
    }
    s->arr[++s->top] = item;
}

int pop(struct Stack* s) {
    if (isStackEmpty(s)) {
        printf("Stack is empty. Cannot pop.\n");
        return -1;
    }
    return s->arr[s->top--];
}

int peek(struct Stack* s) {
    if (isStackEmpty(s)) {
        printf("Stack is empty. Cannot peek.\n");
        return -1;
    }
    return s->arr[s->top];
}

struct Queue {
    struct Stack s1;
    struct Stack s2;
};

void initializeQueue(struct Queue* q) {
    initializeStack(&q->s1);
    initializeStack(&q->s2);
}

bool isQueueEmpty(struct Queue* q) {
    return isStackEmpty(&q->s1) && isStackEmpty(&q->s2);
}

bool isQueueFull(struct Queue* q) {
    return isStackFull(&q->s1) || isStackFull(&q->s2);
}

void enqueue(struct Queue* q, int item) {
    if (isQueueFull(q)) {
        printf("Queue is full. Cannot enqueue.\n");
        return;
    }

    // Transfer all elements from s1 to s2
    while (!isStackEmpty(&q->s1)) {
        push(&q->s2, pop(&q->s1));
    }

    push(&q->s1, item);

    // Transfer all elements back to s1
    while (!isStackEmpty(&q->s2)) {
        push(&q->s1, pop(&q->s2));
    }
}

int dequeue(struct Queue* q) {
    if (isQueueEmpty(q)) {
        printf("Queue is empty. Cannot dequeue.\n");
        return -1;
    }
    return pop(&q->s1);
}

int getSize(struct Queue* q) {
    return q->s1.top + 1;
}

void displayQueue(struct Queue* q) {
    if(isQueueEmpty(q)) {
        printf("Queue is empty.\n");
        return;
    }

    while (!isStackEmpty(&q->s1)) {
        int data = pop(&q->s1);
        push(&q->s2, data);
        printf("%d ", data);
    }

    // Transfer all elements back to s1
    while (!isStackEmpty(&q->s2)) {
        push(&q->s1, pop(&q->s2));
    }

    printf("\n");
}

int main() {
    struct Queue q;
    initializeQueue(&q);

    int choice, item;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Enqueue\n");
        printf("2. Dequeue\n");
        printf("3. Check if empty\n");
        printf("4. Check if full\n");
        printf("5. Get size\n");
        printf("6. Display queue\n");
        printf("7. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter the item to enqueue: ");
                scanf("%d", &item);
                enqueue(&q, item);
                break;
            case 2:
                item = dequeue(&q);
                if (item != -1) {
                    printf("Dequeued item: %d\n", item);
                }
                break;
            case 3:
                if (isQueueEmpty(&q)) {
                    printf("Queue is empty.\n");
                } else {
                    printf("Queue is not empty.\n");
                }
                break;
            case 4:
                if (isQueueFull(&q)) {
                    printf("Queue is full.\n");
                } else {
                    printf("Queue is not full.\n");
                }
                break;
            case 5:
                printf("Current size of the queue: %d\n", getSize(&q));
                break;
            case 6:
                displayQueue(&q);
                break;
            case 7:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid choice. Please choose again.\n");
        }
    }

    return 0;
}
