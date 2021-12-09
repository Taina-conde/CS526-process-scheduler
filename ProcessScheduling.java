import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
class ProcessScheduling {
    /**
     *
     * */
    private static ArrayList readProcesses() throws IOException {
        ArrayList D = new ArrayList();
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
    public static void main(String[] args) {
        //Read all processes from an input file and store them in an appropriate data structure, D
        // initialize currentTime
        //set running initially to false
        // create an empty priority queue Q

        // Each iteration of the while loop represents what occurs during one time unit
        // Must increment currentTime in each iteration


    }
}