public class Process {

    private final int id;
    private final int duration;
    private final int arrivalTime;
    private int priority;

    public Process() {
        this.id = 0;
        this.priority = 0;
        this.duration = 0;
        this.arrivalTime = 0;
    }

    public Process(int id, int priority, int duration, int arrivalTime) {
        this.id = id;
        this.priority = priority;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
    }
    //getters
    public int getProcessId() { return id; }
    public int getPriority() { return priority; }
    public int getDuration() { return duration; }
    public int getArrivalTime() { return arrivalTime; }
    //setters

    public void setPriority(int priority){
        this.priority = priority;
    }


    public String toString() {
        return "Id = " + id + ", priority = " + priority + ", duration = " + duration
                        + ", arrival time = " + arrivalTime;

    }


}
