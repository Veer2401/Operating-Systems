import java.util.Scanner;

class Process{
    String PID;
    int AT,BT,CT,TAT,WT,RT;
    boolean completed;
}

public class SJF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        Process[] p = new Process[n];
        String[] gantt = new String[n];

        for(int i=0;i<n;i++){
            p[i] = new Process();
            p[i].PID = "P" + (i+1);

            System.out.println("Enter details for " + p[i].PID);
            System.out.print("Arrival time: ");
            p[i].AT = sc.nextInt();
            System.out.print("Burst time: ");
            p[i].BT = sc.nextInt();
        }

        int time = 0, completed = 0;

        //sjf
        while(completed < n){
            int index = -1;

            for(int i=0;i<n;i++){
                if(!p[i].completed && p[i].AT <= time){
                    if(index == -1 || p[i].BT < p[index].BT){
                        index = i;
                    }
                }
            }

            if(index == -1){
                time++;
                continue;
            }

            //store
            gantt[completed] = p[index].PID;

            time = time + p[index].BT;

            //calculate times
            p[index].CT = time;
            p[index].TAT = p[index].CT - p[index].AT;
            p[index].WT = p[index].TAT - p[index].BT;
            p[index].RT = p[index].WT;
            p[index].completed = true;
            completed++;
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT\tRT");
    System.out.println("--------------------------------------------");

    for (int i = 0; i < n; i++) {

        System.out.println(
            p[i].PID + "\t" +
            p[i].AT + "\t" +
            p[i].BT + "\t" +
            p[i].CT + "\t" +
            p[i].TAT + "\t" +
            p[i].WT + "\t" +
            p[i].RT
        );
    }

    System.out.println("Gantt Chart");
    System.out.print("|");

    for(int i=0;i<n;i++){
        System.out.print("   " + gantt[i] + "  |");
    }
    System.out.println();
    System.out.print("0");

    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(gantt[i].equals(p[j].PID)){
                    System.out.print("   " + p[j].CT + "  ");

            }
        }
    }
    System.out.println();
    sc.close();

    }
}