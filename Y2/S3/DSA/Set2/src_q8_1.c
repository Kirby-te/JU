#include <stdio.h>
#include <stdbool.h>

#define MAX_SIZE 5

struct CircularQueue {
    int queue[MAX_SIZE];
    int front, rear, size;
};

void initializeCircularQueue(struct CircularQueue* cq) {
    cq->front = -1;
    cq->rear = -1;
    cq->size = 0;
}

bool isFull(struct CircularQueue* cq) {
    return cq->size == MAX_SIZE;
}

bool isEmpty(struct CircularQueue* cq) {
    return cq->size == 0;
}

int getSize(struct CircularQueue* cq) {
    return cq->size;
}

void enqueue(struct CircularQueue* cq, int item) {
    if (isFull(cq)) {
        printf("Queue is full. Cannot enqueue.\n");
        return;
    }
    
    cq->rear = (cq->rear + 1) % MAX_SIZE;
    cq->queue[cq->rear] = item;
    cq->size++;

    if (cq->front == -1) {
        cq->front = cq->rear;
    }
}

int dequeue(struct CircularQueue* cq) {
    if (isEmpty(cq)) {
        printf("Queue is empty. Cannot dequeue.\n");
        return -1;
    }

    int item = cq->queue[cq->front];
    cq->front = (cq->front + 1) % MAX_SIZE;
    cq->size--;

    if (cq->size == 0) {
        cq->front = -1;
        cq->rear = -1;
    }

    return item;
}

void displayQueue(struct CircularQueue* cq) {
    if (isEmpty(cq)) {
        printf("Circular Queue is empty.\n");
        return;
    }

    printf("Queue elements: ");
    for (int i = cq->front; i <= cq->rear; i++) {
        printf("%d ", cq->queue[i]);
    }
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
        printf("4. Check if full\n");
        printf("5. Get size\n");
        printf("6. Display Circular Queue\n");
        printf("7. Exit\n");
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
                if (isFull(&cq)) {
                    printf("Queue is full.\n");
                } else {
                    printf("Queue is not full.\n");
                }
                break;
            case 5:
                printf("Current size of the queue: %d\n", getSize(&cq));
                break;
            case 6:
                displayQueue(&cq);
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