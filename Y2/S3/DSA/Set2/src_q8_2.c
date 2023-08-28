#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct Node {
    int data;
    struct Node* next;
};

struct CircularQueue {
    struct Node* front;
    struct Node* rear;
    int size;
};

void initializeCircularQueue(struct CircularQueue* cq) {
    cq->front = NULL;
    cq->rear = NULL;
    cq->size = 0;
}

bool isEmpty(struct CircularQueue* cq) {
    return cq->size == 0;
}

void enqueue(struct CircularQueue* cq, int item) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = item;
    newNode->next = NULL;

    if (isEmpty(cq)) {
        cq->front = newNode;
        cq->rear = newNode;
    } else {
        cq->rear->next = newNode;
        cq->rear = newNode;
    }

    cq->rear->next = cq->front; // Making it circular
    cq->size++;
}

int dequeue(struct CircularQueue* cq) {
    if (isEmpty(cq)) {
        printf("Queue is empty. Cannot dequeue.\n");
        return -1;
    }

    struct Node* temp = cq->front;
    int item = temp->data;
    cq->front = cq->front->next;
    cq->rear->next = cq->front; // Adjusting rear pointer for circular behavior
    free(temp);
    cq->size--;

    if (cq->size == 0) {
        cq->front = NULL;
        cq->rear = NULL;
    }

    return item;
}

int getSize(struct CircularQueue* cq) {
    return cq->size;
}

void displayQueue(struct CircularQueue* cq) {
    if (isEmpty(cq)) {
        printf("Queue is empty.\n");
        return;
    }

    struct Node* current = cq->front;
    printf("Queue elements: ");
    do {
        printf("%d ", current->data);
        current = current->next;
    } while(current != cq->rear->next);
    printf("...\n");
}

int main() {
    struct CircularQueue cq;
    initializeCircularQueue(&cq);

    int choice, item;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Enqueue\n");
        printf("2. Dequeue\n");
        printf("3. Check if empty\n");
        printf("4. Get size\n");
        printf("5. Display Circular Queue\n");
        printf("6. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter the item to enqueue: ");
                scanf("%d", &item);
                enqueue(&cq, item);
                break;
            case 2:
                item = dequeue(&cq);
                if (item != -1) {
                    printf("Dequeued item: %d\n", item);
                }
                break;
            case 3:
                if (isEmpty(&cq)) {
                    printf("Queue is empty.\n");
                } else {
                    printf("Queue is not empty.\n");
                }
                break;
            case 4:
                printf("Current size of the queue: %d\n", getSize(&cq));
                break;
            case 5:
                displayQueue(&cq);
                break;
            case 6:
                printf("Exiting the program.\n");
                return 0;
            default:
                printf("Invalid choice. Please choose again.\n");
        }
    }

    return 0;
}