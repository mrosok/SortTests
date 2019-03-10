package no.martr;

public interface QueueADT<T>
{
    //  Adds one element to the rear of the queue
    void enqueue (T element);

    //  Removes and returns the element at the front of the queue
    T dequeue();

    //  Returns without removing the element at the front of the queue
    T first();

    //  Returns true if the queue contains no elements
    boolean isEmpty();

    //  Returns the number of elements in the queue
    int size();

    //  Returns a string representation of the queue
   String toString();
}