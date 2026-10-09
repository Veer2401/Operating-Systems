
import java.util.Scanner;

public class PageReplace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        System.out.print("Enter number of frames: ");
        int f = sc.nextInt();

        int[] frame = new int[f];
        int faults;

        // FCFS
        for (int i = 0; i < f; i++)
            frame[i] = -1;

        int next = 0;
        faults = 0;

        for (int i = 0; i < n; i++) {
            boolean found = false;

            for (int j = 0; j < f; j++)
                if (frame[j] == pages[i])
                    found = true;

            if (!found) {
                frame[next] = pages[i];
                next = (next + 1) % f;
                faults++;
            }
        }
        System.out.println("FCFS Page Faults: " + faults);

        // LRU
        for (int i = 0; i < f; i++)
            frame[i] = -1;

        int[] time = new int[f];
        faults = 0;

        for (int i = 0; i < n; i++) {
            int pos = -1;

            for (int j = 0; j < f; j++)
                if (frame[j] == pages[i])
                    pos = j;

            if (pos == -1) {
                int least = 0;

                for (int j = 0; j < f; j++) {
                    if (frame[j] == -1) {
                        least = j;
                        break;
                    }
                    if (time[j] < time[least])
                        least = j;
                }

                frame[least] = pages[i];
                time[least] = i;
                faults++;
            } else {
                time[pos] = i;
            }
        }
        System.out.println("LRU Page Faults: " + faults);

        // Optimal
        for (int i = 0; i < f; i++)
            frame[i] = -1;

        faults = 0;

        for (int i = 0; i < n; i++) {
            boolean found = false;

            for (int j = 0; j < f; j++)
                if (frame[j] == pages[i])
                    found = true;

            if (!found) {
                int replace = -1;
                int farthest = -1;

                for (int j = 0; j < f; j++) {
                    if (frame[j] == -1) {
                        replace = j;
                        break;
                    }

                    int k;
                    for (k = i + 1; k < n; k++)
                        if (pages[k] == frame[j])
                            break;

                    if (k > farthest) {
                        farthest = k;
                        replace = j;
                    }
                }

                frame[replace] = pages[i];
                faults++;
            }
        }
        System.out.println("Optimal Page Faults: " + faults);

        sc.close();
    }
}