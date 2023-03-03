import java.util.ArrayList;

public class FirstFit {

    void putProcesses(ArrayList<Partition> partitions, ArrayList<Process> processes) {

        for (Process process : processes) { // for each on processes

            for (int j = 0; j < partitions.size(); j++) {

                Partition partition = partitions.get(j);  // get current partition

                if (process.getSize() <= partition.getSize() && partition.isEmpty() && !process.isUsed()) {
                    // set process to this partition
                    partition.setProcess(process);

                    //set process is used
                    process.Used();

                    // check if I will create new partition
                    int dif = partition.getSize() - process.getSize();

                    //set this partition is used;
                    partition.used(process.getSize());

                    if (dif > 0) {
                        Partition newPart = new Partition(dif);
                        partitions.add(j + 1, newPart);
                    }
                    break;
                }
            }
        }
    }
}
