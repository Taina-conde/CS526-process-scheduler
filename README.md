# Process Scheduler

This project is an implementation of a simplified version of a process scheduler of a computer system. This programs simulates the basic operations of a process scheduler.

The idea is that a computer system excecutes a process one at a time and each process takes a certain amount of time to be excecuted. Each process has an id, priority, arrivalTime and duration.

As the processes arrive at the system, the scheduler controls which process executes based on its priority. Once the computer system executes a process, it doesn't stop executing it for the duration of the process. 

After the current process finishes executing, the system executes the next process with the lowest priority. If a process has been waiting for more than 30 milliseconds, this process' priority decreases to prevent that a process waits for a very long time. 


## Data Structures used in the program

The readProcesses method reads the process_scheduling_input.txt file, creates Process instances from the information in the file, and stores all the processes in an ArrayList data structure called D.


The ArrayList data structure is a resizable-array implementation of the List interface, that includes the following methods used in the process scheduler program:


size( ) : returns the number of elements in the list.

isEmpty( ): returns true if the list is empty, that is, contains no elements. get(int i) : returns the element at index i.

add( E e) : appends the element e at the end of the list.

remove( int i ): removes the element at index i.

In the main method, a PriorityQueue instance Q is created, and, when time reaches the arrival time of a process stored in D, it is removed from D and added to Q. Q arranges the processes according to the ProcessComparator, which uses the processes’s priorities for comparison. 

Therefore, the Q PriorityQueue arranges the processes in non-descending order. When it is time to execute a process, the one with smallest priority is removed from Q and executed.

In the PriorityQueue data structure, the elements are ordered according to a Comparator provided at construction time. The head of the queue contains the least element according to the ordering. This data structure includes the following methods used in the process scheduler program:

size( ): returns the number of elements in the PriorityQueue.

isEmpty( ): returns true if the PriorityQueue is empty, that is, contains no elements. add(E e ): inserts element e into the PriorityQueue.

poll( ): retrieves and removes the head of the queue. Returns null if the queue is empty.

## Discussion

This project was a great opportunity to put into practice what I learned in class about the ArrayList and PriorityQueue data structures. 

The ArrayList is simple to work with, specially when we have to add new elements to it or remove elements from it because of its dynamic nature. In the process scheduler program, new elements were added to the ArrayList D and then later removed from it without needing to resize it.

I also learned that it is possible to iterate over an ArrayList using a for loop, a for each loop and an iterator. Even though I didn’t use the last, I studied and learned how to use the iterator( ) method.

Regarding the PriorityQueue, I learned how to define a comparator. In the program, I wrote the ProcessComparator class, which is passed to the PriorityQueue constructor to determine to compare Process instances according to their priority. The ProcessComparator implements the Comparator interface and overrides its compare( ) method. In my implementation of the ProcessComparator, the compare( ) method returns -1 if the first Process instance’s priority passed as a parameter is less than the second Process instance parameter. It returns 1 if the opposite is true, that is the first process’s priority is greater than the second process’s priority, and it returns 0 if both are equal.

Additionally, the process scheduler project helped me put into practice my recently developed skills in Java, since I didn’t have any experience with it before taking CS526. I learned how to debug an application build in Java and how to use the StringBuilder class for string concatenation.

Lastly, it was interesting to learn how a process scheduler in a computer system works. Even though the simulation implemented in this project is very small and simplified, I wasn’t familiar with how the computer executes the processes that arrive at different moments.

## References

ArrayList : https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
PriorityQueue: https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
