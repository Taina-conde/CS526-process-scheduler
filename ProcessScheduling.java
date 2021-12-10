import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

class ProcessScheduling {
    /** readProcess reads the input file that stores information about all processes. It creates Process instances
     * for each process from the file and add them to an ArrayList.
     * @return D returns the ArrayList of processes created from the input file.
     * @throws IOException the method throws an exception if it cannot find the process_scheduling_input.txt file.
     * */
    private static ArrayList<Process> readProcesses() throws IOException {
        ArrayList<Process> D = new ArrayList<>();
        // read input file and store all process Objects in the D
        File myFile = new File("process_scheduling_input.txt");
        Scanner myReader = new Scanner(myFile);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            // split the data string on the whitespace delimiter
            String[] tokens = data.split(" ");
            // eliminate whitespaces in each token. Convert them to integers
            int processId = Integer.parseInt(tokens[0].trim());
            int priority = Integer.parseInt(tokens[1].trim());
            int duration = Integer.parseInt(tokens[2].trim());
            int arrivalTime = Integer.parseInt(tokens[3].trim());
            // create a new process instance and add it to D
            Process newProcess = new Process(processId, priority, duration, arrivalTime);
            D.add(newProcess);

        }
        myReader.close();
        return D;

    }
    /** completeExecution prints a message about the completed process and updates the priorities of processes that
     * have been waiting longer than a max wait time.
     * @param currTime the integer that represents the current time.
     * @param maxWait maximum wait time a process can wait before its priority gets updated.
     * @param executingProcess the Process that is currently running, or just finished running.
     * @param Q the PriorityQueue that stores the processes that arrived at the system and are waiting to run according
     *          to their priority.
     * @throws IOException when an error occurs when writing to an output file.
     * */
    private static void completeExecution(
            int currTime,
            int maxWait,
            Process executingProcess,
            PriorityQueue<Process> Q,
            FileWriter myWriter) throws IOException{
        myWriter.write("Process "
                + executingProcess.getProcessId() +
                " finished at time "
                + currTime +
                "\n"
        );
        // Update priorities of processes that have been waiting longer than max. wait time
        myWriter.write("Update priority: ");
        for (Process process: Q) {
            int waitTime = currTime - process.getArrivalTime();
            if (waitTime > maxWait) {
                myWriter.write("PID = "
                        + process.getProcessId() +
                        " , wait time = "
                        + waitTime +
                        " , current priority = "
                        + process.getPriority()

                );
                process.setPriority(process.getPriority() - 1);
                myWriter.write("PID = "
                        + process.getProcessId() +
                        " , new priority = "
                        + process.getPriority()
                );
            }
        }

    }
    /** this method prints the messages about the process that started to run according to the expected output format.
     * @param currTime the integer that represents the current time.
     * @param waitTime the time the process waits from arrival until it is executed.
     * @param totalWaitTime the sum of wait times of all the processes that were executed.
     * @param executingProcess the process that is currently running.
     * @param myWriter  write to an output file
     * @throws IOException if an error occurs when writing to an output file.
     * */
    private static void printExecutingMessages(
            int currTime, int waitTime,
            double totalWaitTime,
            Process executingProcess,
            FileWriter myWriter) throws IOException {

        myWriter.write("\nProcess removed from queue is: id = "
                + executingProcess.getProcessId() +
                " , at time "
                + currTime +
                " , wait time = "
                + waitTime +
                " Total wait time = "
                + totalWaitTime);
        myWriter.write("Process id = "
                + executingProcess.getProcessId() +
                "\n\t\tPriority = "
                + executingProcess.getPriority() +
                "\n\t\tArrival = "
                + executingProcess.getArrivalTime() +
                "\n\t\tDuration =  "
                + executingProcess.getDuration()
        );
    }
    /** Simulates a simplified version of a process scheduler of a computer system.
     * @throws IOException if it cannot find the process_scheduling_input.txt file. The exception comes from calling
     * readProcesses method, or if writing to the output file process_scheduling.output.txt, an error occurs.
     * */
    public static void main(String[] args) throws IOException {
        //Read all processes from an input file and store them in an ArrayList, D
        ArrayList<Process> D = readProcesses();
        // initial number of processes
        int numProcesses = D.size();
        // initialize current time
        int currTime = 0;
        //maximum wait time
        int maxWait = 30;
        //set running initially to false
        boolean running = false;
        // executingDuration helps determine the time a process will keep executing
        int executingDuration = 0;
        Process executingProcess = new Process();
        // total wait time of all processes
        double totalWaitTime = 0;
        // PriorityQueue Q that orders its elements according to the comparator (lowest priority).
        PriorityQueue<Process> Q = new PriorityQueue<>( new ProcessComparator());

        //First, print all processes in an output file
        FileWriter myWriter = new FileWriter("process_scheduling_output.txt");
        for (Process p: D) {
            myWriter.write(p.toString());
        }

        //print maximum wait time
        myWriter.write("\nMaximum wait time = " + maxWait);
        // Each iteration of the while loop represents what occurs during one time unit
        while(!D.isEmpty()) {
            // look in all processes stored in D
            for (int i = 0; i < D.size(); i++) {
                // if the arrivalTime of that process is less than or equal to current time, remove that
                //process from D, and insert it into the PriorityQueue
                //That simulates the arrival of a new process at the system
                if (D.get(i).getArrivalTime() <= currTime) {
                    Process next = D.remove(i);
                    Q.add(next);
                }
            }

            //check if a process just finished running
            if (running && executingDuration == 0) {
                // set running back to false, the process that was running just reached the duration of its execution
                //and finished
                running = false;
                completeExecution(currTime, maxWait, executingProcess, Q, myWriter);

            }
            // if no process is running and there still processes waiting in the queue,
            // run the next process with the smallest priority
            if (!Q.isEmpty() && !running) {
                executingProcess = Q.poll();
                int waitTime = currTime - executingProcess.getArrivalTime();
                totalWaitTime += waitTime;
                printExecutingMessages(currTime, waitTime, totalWaitTime, executingProcess, myWriter);

                // set the executingDuration variable to mark the duration of the process that is now running
                executingDuration = executingProcess.getDuration();
                running = true;

            }
            if(D.isEmpty()) System.out.println("\nD becomes empty at time " + currTime + "\n");

            // increment currTime in each iteration
            currTime++;
            // if there is a process running, decrement the executingDuration
            // when this variable is 0, no process is running or one process just finished running
            if(executingDuration != 0) {
                executingDuration--;
            }
        }

        //At this time all processes in D have been moved to Q
        //execute all processes that are still in Q, one at a time
        while (!Q.isEmpty()) {

            //check if a process just finished running
            if (running && executingDuration == 0) {
                // set running back to false, the process that was running just reached the duration of its execution
                //and finished
                running = false;
                completeExecution(currTime,maxWait, executingProcess, Q, myWriter);
            }
            // execute the next process from the priority queue if there is no process running
            if (!running) {
                executingProcess = Q.poll();
                int waitTime = currTime - executingProcess.getArrivalTime();
                totalWaitTime += waitTime;
                printExecutingMessages(currTime, waitTime, totalWaitTime, executingProcess, myWriter);
                // set the executingDuration variable to mark the duration of the process that is now running
                executingDuration = executingProcess.getDuration();
                running = true;

            }
            // increment currTime in each iteration
            currTime++;
            // if there is a process running, decrement the executingDuration
            // when this variable is 0, no process is running or one process just finished running
            if(executingDuration != 0) {
                executingDuration--;
            }
        }
        //if there is a process running, add the remaining time of execution to currTime and complete the last process.
        if(executingDuration!= 0) {
            currTime += executingDuration;
            completeExecution(currTime, maxWait, executingProcess, Q, myWriter);
        }
        double averageWaitTime = totalWaitTime/numProcesses;
        myWriter.write("\nTotal wait time = " + totalWaitTime);
        myWriter.write("Average wait time = " + averageWaitTime);

        //close output file
        myWriter.close();

    }
}