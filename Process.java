public class Process {

    private int id;
    private int duration;
    private int arrivalTime;
    private int priority;

    public Process() {
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
    public void setProcessId(int id) {
        this.id = id ;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public String toString() {
        String c =
                "Id = " + id + ", priority = " + priority + ", duration = " + duration
                        + ", arrival time = " + arrivalTime;

        return c;
    }


}
