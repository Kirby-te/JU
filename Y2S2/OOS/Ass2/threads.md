Thread
* A thread is a lightweight sub-process, the smallest unit of processing.

Multitasking
* Multitasking is a process of executing multiple tasks simultaneously. We use multitasking to utilize the CPU. Multitasking can be achieved in two ways:
1. Process-based Multitasking (Multiprocessing)
     * each process allocates a separate memory area
2. Thread-based Multitasking (Multithreading)
     * share the same address space

Thread States
  * New: 
  * Active: start()
    * Runnable
      * threads lie for thier slice of time to get run in the CPU
    * Running
      * run for a pre-determined time
  * Blocked / Waiting
    * a thread is insctive for a span of time
    * in this state it doesn't consume any cycle of CPU
    * when main thread invokes join() method it goes into waiting for the child threads to finish
  * Timed Waiting
    * waitng for leads to stervation
    * sleep()
  * Terminated


