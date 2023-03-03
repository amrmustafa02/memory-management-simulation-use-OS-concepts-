

public class Partition {

    private int size;
    public static int id = 0;
    private int PID;
    private boolean empty = true;
    private Process process;

    public Partition(int size) {
        this.size = size;
        this.PID = id;
        id++;
    }

    public Partition(Partition partition) {
        this.size = partition.getSize();
        this.PID = partition.getId();
    }

    boolean isEmpty() {
        return empty;
    }

    public int getSize() {
        return size;
    }

    public int getId() {
        return PID;
    }


    public void setProcess(Process process) {
        this.process = process;
    }

    public void used(int size) {
        this.size = size;
        empty = false;
    }

    public Process getProcess() {
        return process;
    }
}

