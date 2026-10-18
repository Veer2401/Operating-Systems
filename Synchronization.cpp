#include <iostream>
#include <pthread.h>
#include <unistd.h>
using namespace std;

int data = 0;
pthread_mutex_t lock1 = PTHREAD_MUTEX_INITIALIZER;

void* reader(void*) {
    pthread_mutex_lock(&lock1);
    cout << "Reader reads: " << data << endl;
    pthread_mutex_unlock(&lock1);
    return NULL;
}

void* writer(void*) {
    pthread_mutex_lock(&lock1);
    data++;
    cout << "Writer writes: " << data << endl;
    pthread_mutex_unlock(&lock1);
    return NULL;
}

int main() {
    pthread_t r, w;

    cout << "With Synchronization:\n";
    pthread_create(&r, NULL, reader, NULL);
    pthread_create(&w, NULL, writer, NULL);
    pthread_join(r, NULL);
    pthread_join(w, NULL);

    cout << "\nWithout Synchronization:\n";
    data = 0;
    cout << "Reader reads: " << data << endl;
    data++;
    cout << "Writer writes: " << data << endl;

    pthread_mutex_destroy(&lock1);
    return 0;
}
