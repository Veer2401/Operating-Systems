#include <iostream>
#include <pthread.h>
#include <unistd.h>
using namespace std;

string data = "Hello";
pthread_mutex_t m = PTHREAD_MUTEX_INITIALIZER;
bool syncOn;

void* reader(void*) {
    if (syncOn) pthread_mutex_lock(&m);
    cout << "Reader reads: " << data << endl;
    if (syncOn) pthread_mutex_unlock(&m);
    return NULL;
}

void* writer(void*) {
    if (syncOn) pthread_mutex_lock(&m);
    data = "Data updated by writer";
    cout << "Writer writes: " << data << endl;
    if (syncOn) pthread_mutex_unlock(&m);
    return NULL;
}

void run(bool s) {
    syncOn = s;
    pthread_t r, w;
    pthread_create(&r, NULL, reader, NULL);
    pthread_create(&w, NULL, writer, NULL);
    pthread_join(r, NULL);
    pthread_join(w, NULL);
}

int main() {
    cout << "Enter a sentence: ";
    getline(cin, data);

    cout << "\nWith Synchronization:\n";
    run(true);

    data = "Original sentence";
    cout << "\nWithout Synchronization:\n";
    run(false);

    pthread_mutex_destroy(&m);
    return 0;
}
