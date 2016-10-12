package com.epam.third.controller;


import com.epam.third.entity.TrainEntranceLimiter;

public class Main {
    public static void main(String[] args) {


        TrainEntranceLimiter trainEntranceLimiter = new TrainEntranceLimiter();
        TrainEntranceLimiter.Train train1 = trainEntranceLimiter.new Train();
        TrainEntranceLimiter.Train train2 = trainEntranceLimiter.new Train();
        TrainEntranceLimiter.Train train3 = trainEntranceLimiter.new Train();
        train1.start();
        train2.start();
        train3.start();
    }

}
