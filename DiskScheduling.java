import java.util.*;

public class DiskScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of requests: ");
        int n = sc.nextInt();
        int[] a = new int[n];

        System.out.println("Enter values: ");
        for(int i=0;i<n;i++) a[i] = sc.nextInt();
        System.out.print("Enter head: ");
        int head = sc.nextInt();
        System.out.print("Enter disk size: ");
        int size = sc.nextInt();

        int h = head;
        int t = 0;

        //fifo
        for(int i=0;i<n;i++){
            t += Math.abs(h-a[i]);
            h = a[i];
        }
        System.out.println("FIFO " + t);

        //sstf
        boolean[] used = new boolean[n];
        h = head;
        t = 0;

        for(int i=0;i<n;i++){
            int p = -1, min = 99999;

            for(int j=0;j<n;j++){
                if(!used[j] && Math.abs(h - a[j]) < min){
                    min = Math.abs(h-a[j]);
                    p = j;
                }
            }
            used[p] = true;
            t += min;
            h = a[p];
        }
        System.out.println("SSTF: " + t);

         // SCAN
        h = head; t = 0;
        for (int x : a)
            if (x >= head) { t += Math.abs(h-x); h=x; }
        t += size-1-h; h = size-1;
        for (int i=n-1; i>=0; i--)
            if (a[i] < head) { t += h-a[i]; h=a[i]; }
        System.out.println("SCAN: " + t);

        // C-SCAN
        h = head; t = 0;
        for (int x : a)
            if (x >= head) { t += Math.abs(h-x); h=x; }
        if (a[0] < head) {
            t += size-1-h + size-1; h=0;
            for (int x : a)
                if (x < head) { t += x-h; h=x; }
        }
        System.out.println("C-SCAN: " + t);
    }
}