import java.util.Scanner;
import java.util.ArrayList;

public class Main {


    static void bubbleSort(ArrayList<Partition> copPartitions) {
        int n = copPartitions.size();
        int i, j;
        Partition temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (copPartitions.get(j).getSize() > copPartitions.get(j + 1).getSize()) {

                    temp = copPartitions.get(j);

                    copPartitions.set(j, copPartitions.get(j + 1));

                    copPartitions.set(j + 1, temp);

                    swapped = true;
                }
            }

            // IF no two elements were
            // swapped by inner loop, then break
            if (!swapped) break;
        }
    }

    static void printPartition(ArrayList<Partition> copyPartition) {
        for (Partition part : copyPartition) {
            if (part.isEmpty()) {
                System.out.println("Partition " + part.getId() + "( " + part.getSize() + " KB ) => External fragment");
            } else {
                System.out.print("Partition " + part.getId() + " (" + part.getSize() + " KB )");
                System.out.println("=> Process " + part.getProcess().getId());
            }
        }

    }

    static void printProcess(ArrayList<Process> copProcess) {
        for (Process process : copProcess)
            if (!process.isUsed()) System.out.println("Process" + process.getId() + " can not be allocated");

    }

    static void compact(ArrayList<Partition> copyPartition, ArrayList<Process> copProcess, int ch) {


        int sum = 0;

        for (int i = 0; i < copyPartition.size(); i++) {
            if (copyPartition.get(i).isEmpty()) {
                sum += copyPartition.get(i).getSize();
                copyPartition.remove(i);
                i--;
            }
        }

        // copy compact
        Partition allPart = new Partition(sum);
        copyPartition.add(allPart);

        bubbleSort(copyPartition);

        if (ch == 1) {
            FirstFit firstFit = new FirstFit();
            firstFit.putProcesses(copyPartition, copProcess);
        } else if (ch == 2) {
            BestFit bestFit = new BestFit();
            bestFit.putProcesses(copyPartition, copProcess);
        } else {
            WorstFit worstFit = new WorstFit();
            worstFit.putProcesses(copyPartition, copProcess);
        }

    }


    static void copyArray(ArrayList<Partition> partitions, ArrayList<Process> processes, ArrayList<Partition> copPartitions, ArrayList<Process> copProcesses) {

        for (Partition partition : partitions) {
            Partition newPart = new Partition(partition);
            copPartitions.add(newPart);
        }

        for (Process process : processes) {
            Process process1 = new Process(process);
            copProcesses.add(process1);
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        // array to store partitions
        // array to store processes
        ArrayList<Partition> partitions = new ArrayList<>();
        ArrayList<Process> processes = new ArrayList<>();


        // take number of partition
        System.out.print("Enter number of partitions: ");
        int numOfPartition = sc.nextInt();

        for (int i = 0; i < numOfPartition; i++) {
            System.out.print("Enter size of partition" + i + " : ");
            int sizeOfPartition = sc.nextInt();

            // make new partition and store it in array
            Partition partition = new Partition(sizeOfPartition);
            partitions.add(partition);
        }

        /*
        take processes from user and store it in array
         */
        // take number of partition
        System.out.print("Enter number of processes: ");
        int numOfProcesses = sc.nextInt();

        for (int i = 0; i < numOfProcesses; i++) {
            System.out.print("Enter size of process" + i + " : ");
            int sizeOfProcess = sc.nextInt();

            // make new partition and store it in array
            Process process = new Process(sizeOfProcess);
            processes.add(process);
        }


        // var to take choice;
        int ch, ch2;

        while (true) {
            ArrayList<Partition> copyPartition = new ArrayList<>();
            ArrayList<Process> copyProcess = new ArrayList<>();

            System.out.println("\n1- First fit");
            System.out.println("2- Best fit");
            System.out.println("3- Worst fit");
            System.out.println("4- Exit");
            System.out.print("select policy: ");
            ch = sc.nextInt();

            Partition.id = numOfPartition;

            copyArray(partitions, processes, copyPartition, copyProcess);

            if (ch == 1) {
                // make object from first fit and send it to first fit
                FirstFit firstFit = new FirstFit();
                firstFit.putProcesses(copyPartition, copyProcess);
            } else if (ch == 2) {
                // make object from first fit and send it to first fit
                BestFit bestFit = new BestFit();
                bestFit.putProcesses(copyPartition, copyProcess);
            } else if (ch == 3) {
                // make object from first fit and send it to first fit
                WorstFit worstFit = new WorstFit();
                worstFit.putProcesses(copyPartition, copyProcess);
            } else break;

            printPartition(copyPartition);
            printProcess(copyProcess);


            // Do you want to make compact؟
            System.out.println("Do you want to compact? (1.yes) (2.no): ");
            ch2 = sc.nextInt();

            if (ch2 == 1) {
                compact(copyPartition, copyProcess, ch);
                printPartition(copyPartition);
                printProcess(copyProcess);
            }

        }


    }
}

