import java.util.Scanner;

public class MemoryAllocation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int n = sc.nextInt();

        int[] blocks = new int[n];

        System.out.println("Enter memory block sizes:");
        for (int i = 0; i < n; i++) {
            blocks[i] = sc.nextInt();
        }

        System.out.print("Enter number of processes: ");
        int m = sc.nextInt();

        int[] process = new int[m];

        System.out.println("Enter process sizes:");
        for (int i = 0; i < m; i++) {
            process[i] = sc.nextInt();
        }

        int[] memory;
        int[] allocation;

        // First Fit
        memory = blocks.clone();
        allocation = new int[m];

        for (int i = 0; i < m; i++) {
            allocation[i] = -1;

            for (int j = 0; j < n; j++) {
                if (memory[j] >= process[i]) {
                    allocation[i] = j;
                    memory[j] -= process[i];
                    break;
                }
            }
        }

        System.out.println("\nFirst Fit:");
        display(allocation);

        // Best Fit
        memory = blocks.clone();
        allocation = new int[m];

        for (int i = 0; i < m; i++) {
            allocation[i] = -1;
            int best = -1;

            for (int j = 0; j < n; j++) {
                if (memory[j] >= process[i]) {
                    if (best == -1 || memory[j] < memory[best]) {
                        best = j;
                    }
                }
            }

            if (best != -1) {
                allocation[i] = best;
                memory[best] -= process[i];
            }
        }

        System.out.println("\nBest Fit:");
        display(allocation);

        // Worst Fit
        memory = blocks.clone();
        allocation = new int[m];

        for (int i = 0; i < m; i++) {
            allocation[i] = -1;
            int worst = -1;

            for (int j = 0; j < n; j++) {
                if (memory[j] >= process[i]) {
                    if (worst == -1 || memory[j] > memory[worst]) {
                        worst = j;
                    }
                }
            }

            if (worst != -1) {
                allocation[i] = worst;
                memory[worst] -= process[i];
            }
        }

        System.out.println("\nWorst Fit:");
        display(allocation);

        // Next Fit
        memory = blocks.clone();
        allocation = new int[m];
        int last = 0;

        for (int i = 0; i < m; i++) {
            allocation[i] = -1;
            int count = 0;
            int j = last;

            while (count < n) {
                if (memory[j] >= process[i]) {
                    allocation[i] = j;
                    memory[j] -= process[i];
                    last = j;
                    break;
                }

                j = (j + 1) % n;
                count++;
            }
        }

        System.out.println("\nNext Fit:");
        display(allocation);

        sc.close();
    }

    static void display(int[] allocation) {
        System.out.println("Process\tAllocated Block");
        for (int i = 0; i < allocation.length; i++) {
            System.out.print("P" + (i + 1) + "\t");

            if (allocation[i] == -1) {
                System.out.println("Not Allocated");
            } else {
                System.out.println("Block " + (allocation[i] + 1));
            }
        }
    }
}