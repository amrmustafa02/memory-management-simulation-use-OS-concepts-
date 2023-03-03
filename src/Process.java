public class Process {
    private int size;
    private static int id = 1;
    private int PID;
    private Boolean check = false;

    public Process(int size) {
        this.size = size;
        this.PID = id;
        id++;
    }

    public Process(Process process) {
        this.size = process.getSize();
        this.PID = process.getId();
    }

    public int getSize() {
        return size;
    }

    public int getId() {
        return PID;
    }

    public void Used() {
        check = true;
    }

    public Boolean isUsed() {
        return check;
    }

}
