#include <iostream>
#include <unistd.h>
#include <sys/wait.h>
#include <cstring>

using namespace std;

int main(){

    int fd[2];

    pipe(fd);

    pid_t pid = fork();

    if(pid < 0){
        perror("fork");
        return 1;
    }

    if(pid == 0){
        close(fd[1]);

        char message[100];

        read(fd[0],message,sizeof(message));

        cout << "Child process reads: " << message << endl;
        close(fd[0]);
    }
    else{
        close(fd[0]);

        char message[] = "Hello from parent to the child ";

        write(fd[1],message,strlen(message) + 1);
        close(fd[1]);

        wait(NULL);

        cout << "Message sent " << endl;

    }
    return 0;
}