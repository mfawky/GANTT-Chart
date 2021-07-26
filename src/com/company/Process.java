package com.company;

public class Process {
    public int ArrivalTime;
    public int BurstTime;
    public int WaitTime;

    public  Process() {}

    public Process(int Arr, int Burst) {
        this.ArrivalTime = Arr;
        this.BurstTime = Burst;
        this.WaitTime = 0;
    }
}
