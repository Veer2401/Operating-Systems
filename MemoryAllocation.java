import java.util.Scanner;

public class Memory {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of blocks: ");
        int n = sc.nextInt();
        int[] blocks = new int[n];
        System.out.println("Enter block sizes: ");
        for(int i=0;i<n;i++){
            blocks[i] = sc.nextInt();
        }

        System.out.print("Enter number of processes: ");
        int m = sc.nextInt();
        int[] process = new int[m];
        System.out.println("Enter process sizes: ");
        for(int i=0;i<m;i++){
            process[i] = sc.nextInt();
        }

        System.out.println("\n1. First Fit");
        System.out.println("2. Best Fit");
        System.out.println("3. Worst Fit");
        System.out.println("4. Next Fit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid choice");
            sc.close();
            return;
        }

        int[] memory = blocks.clone();
        int last = 0;

        for(int i=0;i<m;i++){
            int chosen = -1;

            for(int k=0;k<n;k++){
                int j = (choice == 4) ? (last + k) % n : k;

                if(memory[j] >= process[i]){
                    if(choice == 1 || choice == 4){
                        chosen = j;
                        break;
                    }

                    if(chosen == -1 ||
                        (choice == 2 && memory[j] < memory[chosen]) || 
                        (choice == 3 && memory[j] > memory[chosen])){
                            chosen = j;
                    }
                }
            }

            if(chosen != -1){
                memory[chosen] -= process[i];
                last = chosen;

                System.out.println("P" + (i+1) + " -> Block " + (chosen+1));
            }else{
                System.out.println("P" + (i + 1) + " -> Not Allocated");
            }
        }
        sc.close();

    }

}
