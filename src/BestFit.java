import java.util.ArrayList;

public class BestFit {


    void putProcesses(ArrayList<Partition> partitions, ArrayList<Process> processes) {

        // for each on processes
        for (Process process : processes) {
            int minPartSize = Integer.MAX_VALUE, indexOfMinPart = -1;

            for (int j = 0; j < partitions.size(); j++) {
                if (process.getSize() <= partitions.get(j).getSize() && partitions.get(j).getSize() <= minPartSize && partitions.get(j).isEmpty() && !process.isUsed()) {  //  set indexOfMinPart to partitions
                    indexOfMinPart = j;
                    minPartSize = partitions.get(j).getSize();
                }
            }

            if (indexOfMinPart != -1) {
                // set process to this partition
                partitions.get(indexOfMinPart).setProcess(process);

                // set process is used
                process.Used();
                // check if I will create new partition
                int dif = partitions.get(indexOfMinPart).getSize() - process.getSize();

                // update this of partition
                partitions.get(indexOfMinPart).used(process.getSize());

                if (dif > 0) {
                    Partition newPart = new Partition(dif);
                    partitions.add(indexOfMinPart + 1, newPart);
                }
            }

        }
    }
}
