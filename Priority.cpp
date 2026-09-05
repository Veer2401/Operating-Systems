#include <iostream>
using namespace std;

int main(){
    int n, AT[10], BT[10], RBT[10], CT[10], TAT[10], WT[10], RT[10], PR[10];

    cout << "Enter number of processes: ";
    cin >> n;

    for(int i=0;i<n;i++){
        cout << "Enter details for P" << (i+1) << endl;
        cout << "Arrival Time: ";
        cin >> AT[i];
        cout << "Burst Time: ";
        cin >> BT[i];
        cout << "Priority: ";
        cin >> PR[i];
        RBT[i] = BT[i];
        RT[i] = -1;
    }

    int time = 0, completed = 0;

    while(completed < n){
        int index = -1;

        for(int i=0;i<n;i++){
            if(AT[i] <= time && RBT[i] > 0 && (index == -1 || PR[i] > PR[index])){
                index = i;
            }
        }

        if(index == -1){
            time++;
            continue;
        }

        if(RT[index] == -1){
            RT[index] = time - AT[index];
        }


        RBT[index]--;
        time++;

        if(RBT[index] == 0){
            CT[index] = time;
            TAT[index] = CT[index] - AT[index];
            WT[index] = TAT[index] - BT[index];
            completed++;
        }

    }

    cout << "\nPID\tAT\tBT\tPR\tCT\tTAT\tWT\tRT\n";

    for(int i = 0; i < n; i++)
        cout << "P" << i + 1 << "\t"
             << AT[i] << "\t"
             << BT[i] << "\t"
             << PR[i] << "\t"
             << CT[i] << "\t"
             << TAT[i] << "\t"
             << WT[i] << "\t"
             << RT[i] << endl;
    
    return 0;    
}