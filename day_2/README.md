# Assignment 2

## Task
Implement a program that simulates a race between three runners.
Each runner is represented by a separate thread. The program should output the progress of each runner at regular intervals and declare the winner when one of the runners crosses the finish line.

## Approach
- Create a separate class called "Runner" that implements the Runnable interface.
- The "run" method of the "Runner" class can simulate the progress of the runner by randomly incrementing the distance covered by the runner.
- The main program can create three instances of the "Runner" class and start them as separate threads.
- The main program can then output the progress of each runner at regular intervals and declare the winner when one of the runners crosses the finish line.

## Code
To check the .java file:
* go to day_2/TrackRunner/src.
* click on Main.java file.
