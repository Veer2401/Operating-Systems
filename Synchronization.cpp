#include <iostream>
#include <pthread.h>
#include <unistd.h>
using namespace std;

string message;

pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t condition = PTHREAD_COND_INITIALIZER;

int readers = 0;
int writers = 0;
int writing = 0;

// WITHOUT SYNCHRONIZATION

void* readerWithout(void*)
{
    cout << "Reader reads: " << message << endl;
    usleep(50000);

    cout << "Reader finished.\n";

    return NULL;
}

void* writerWithout(void*)
{
    cout << "Writer is writing...\n";

    message = "";
    usleep(100000);

    message = "The operating system manages all processes safely.";

    cout << "Writer finished.\n";

    return NULL;
}

// WITH SYNCHRONIZATION

void* readerWith(void*)
{
    pthread_mutex_lock(&mtx);

    while (writing || writers > 0)
        pthread_cond_wait(&condition, &mtx);

    readers++;

    pthread_mutex_unlock(&mtx);

    cout << "Reader reads: " << message << endl;
    usleep(50000);

    pthread_mutex_lock(&mtx);

    readers--;

    if (readers == 0)
        pthread_cond_broadcast(&condition);

    pthread_mutex_unlock(&mtx);

    return NULL;
}

void* writerWith(void*)
{
    pthread_mutex_lock(&mtx);

    writers++;

    while (writing || readers > 0)
        pthread_cond_wait(&condition, &mtx);

    writers--;
    writing = 1;

    pthread_mutex_unlock(&mtx);

    cout << "Writer is writing...\n";

    message = "";
    usleep(100000);

    message = "The operating system manages all processes safely.";

    cout << "Writer finished.\n";

    pthread_mutex_lock(&mtx);

    writing = 0;

    pthread_cond_broadcast(&condition);

    pthread_mutex_unlock(&mtx);

    return NULL;
}

int main()
{
    pthread_t r1, r2, w;

    cout << "===== WITHOUT SYNCHRONIZATION =====\n";

    message = "The system is currently processing a request.";

    pthread_create(&w, NULL, writerWithout, NULL);

    usleep(20000);

    pthread_create(&r1, NULL, readerWithout, NULL);
    pthread_create(&r2, NULL, readerWithout, NULL);

    pthread_join(w, NULL);
    pthread_join(r1, NULL);
    pthread_join(r2, NULL);

    cout << "Final message: " << message << endl;


    cout << "\n===== WITH SYNCHRONIZATION =====\n";

    message = "The system is currently processing a request.";

    pthread_create(&w, NULL, writerWith, NULL);

    usleep(20000);

    pthread_create(&r1, NULL, readerWith, NULL);
    pthread_create(&r2, NULL, readerWith, NULL);

    pthread_join(w, NULL);
    pthread_join(r1, NULL);
    pthread_join(r2, NULL);

    cout << "Final message: " << message << endl;

    return 0;
}