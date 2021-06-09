package classes;

public class Queue {
    int maxRoomQueue = 7;
    int start, end;
    Room items[] = new Room[maxRoomQueue];

    Queue() {
        start = -1;
        end = -1;
    }

    // Check if the queue is empty
    boolean isEmpty() {
        if (start == -1)
            return true;
        else
            return false;
    }

    // Adding an element
    void addingToQueue(Room element) {
        if (start == -1)
            start = 0;
        end = (end + 1) % maxRoomQueue;
        items[end] = element;
        System.out.println("Inserted " + element);
    }

    // Removing an element
    Room removingFromQueue() {
        Room element;
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            element = items[start];
            if (start == end) {
                start = -1;
                end = -1;
            } /* Q has only one element, so we reset the queue after deleting it. */ else {
                start = (start + 1) % maxRoomQueue;
            }
            return (element);
        }
    }

}
