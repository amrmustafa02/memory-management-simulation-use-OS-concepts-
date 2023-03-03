import java.util.*;

public class WorstFit {


    void putProcesses(ArrayList<Partition> partitions, ArrayList<Process> processes) {

        // for each on processes
        for (Process process : processes) {
            int maxPartSize = Integer.MIN_VALUE, indexOfMaxPart = -1;

            for (int j = 0; j < partitions.size(); j++) {
                if (process.getSize() <= partitions.get(j).getSize() && partitions.get(j).getSize() >= maxPartSize && partitions.get(j).isEmpty() && !process.isUsed()) {  //  set indexOfMaxPart to partitions
                    indexOfMaxPart = j;
                    maxPartSize = partitions.get(j).getSize();
                }
            }

            if (indexOfMaxPart != -1) {
                // set process to this partition
                partitions.get(indexOfMaxPart).setProcess(process);

                // set process is used
                process.Used();


                // check if I will create new partition
                int dif = partitions.get(indexOfMaxPart).getSize() - process.getSize();

                // update this of partition
                partitions.get(indexOfMaxPart).used(process.getSize());

                if (dif > 0) {
                    Partition newPart = new Partition(dif);
                    partitions.add(indexOfMaxPart + 1, newPart);
                }
            }

        }
    }
}
