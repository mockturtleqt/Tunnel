package com.epam.third.service;

import com.epam.third.entity.Train;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.epam.third.entity.TrainDirection.BACK;
import static com.epam.third.entity.TrainDirection.FRONT;

public class TrainAdder {
    public static Queue<Train> addTrains() {
        Queue<Train> trains = new ArrayDeque<>();
        trains.add(new Train(1, FRONT));
        trains.add(new Train(2, FRONT));
        trains.add(new Train(3, BACK));
        trains.add(new Train(4, FRONT));
        trains.add(new Train(5, FRONT));
        trains.add(new Train(6, FRONT));
        trains.add(new Train(7, BACK));
        trains.add(new Train(8, BACK));
        trains.add(new Train(9, BACK));
        return trains;
    }
}
