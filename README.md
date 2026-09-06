Operating Systems
This repository contains my Operating Systems course projects and assignments — implementations of core OS concepts like CPU scheduling algorithms and inter-process communication.

📂 Contents
#	File	Language	Topic
1	FCFS.java	Java	First-Come-First-Serve CPU Scheduling
2	SJF.java	Java	Shortest Job First (Non-Preemptive) CPU Scheduling
3	RR.java	Java	Round Robin CPU Scheduling
4	Priority.cpp	C++	Priority CPU Scheduling
5	pipe.cpp	C++	Inter-Process Communication using Pipes
🔧 Prerequisites
Java — JDK 8 or higher (for .java files)
C++ — A C++ compiler with POSIX support, e.g. g++ (for .cpp files)
🚀 How to Run
FCFS — First-Come-First-Serve
javac FCFS.java
java FCFS
Inputs: number of processes, then for each — Arrival Time and Burst Time. Outputs: a table with Completion Time (CT), Turnaround Time (TAT), Waiting Time (WT), and Response Time (RT) for every process.

SJF — Shortest Job First (Non-Preemptive)
javac SJF.java
java SJF
Inputs: number of processes, then for each — Arrival Time and Burst Time. The scheduler always picks the available process with the shortest burst time next. Outputs: per-process CT, TAT, WT, RT, and a Gantt chart sequence.

RR — Round Robin
javac RR.java
java RR
Inputs: number of processes, then for each — Arrival Time and Burst Time, and finally the Time Quantum. Uses a ready-queue and cycles through processes, giving each one quantum at a time. Outputs: per-process CT, TAT, WT, RT, and a Gantt chart sequence.

Priority Scheduling
g++ Priority.cpp -o priority
./priority
Inputs: number of processes, then for each — Arrival Time, Burst Time, and Priority (higher number = higher priority). The ready process with the highest priority runs to completion (non-preemptive). Outputs: a table with CT, TAT, WT, and RT for all processes.

Pipe IPC
g++ pipe.cpp -o pipe
./pipe
Demonstrates inter-process communication using a POSIX pipe:

The parent process writes a message into the pipe.
The child process (created via fork()) reads the message from the pipe and prints it.
📝 What Each Algorithm Does
FCFS
Processes are executed strictly in the order they arrive. Simple and easy to implement, but can suffer from the convoy effect — short processes get stuck behind a long one.

SJF
Among all processes that have arrived, the one with the shortest burst time goes next. Minimizes average waiting time, but requires knowing burst times in advance and can cause starvation for long jobs.

Round Robin
Each process gets a fixed time slice (quantum). If it doesn't finish in that slice, it goes to the back of the queue. Fair and responsive — widely used in time-sharing systems.

Priority Scheduling
The ready process with the highest priority runs first. Simple priority model; can starve low-priority processes unless aging is added.

Pipe IPC
A one-way communication channel between a parent and child process created with fork(). The parent writes, the child reads — a foundational example of how processes exchange data in Unix-like systems.

🎯 Course Context
These are solutions to standard OS lab assignments covering:

Process Scheduling — FCFS, SJF, Priority, Round Robin
Scheduling Metrics — CT, TAT, WT, RT
Inter-Process Communication — pipes and fork()
📄 License
This repository is for educational purposes. Feel free to use the code as a reference for your own OS coursework.

One thing I spotted while reviewing your code: in FCFS.java the bubble sort inner loop has for(int j=0;j<n-i-1;i++) — the increment should be j++, not i++. Want me to fix that before you commit?
