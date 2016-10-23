package com.epam.third.controller;


import com.epam.third.entity.Train;
import com.epam.third.service.TunnelManager;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.epam.third.entity.TrainDirection.BACK;
import static com.epam.third.entity.TrainDirection.FRONT;

public class Main {
    public static void main(String[] args) {
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

        TunnelManager tunnelManager = TunnelManager.getInstance(trains);
        tunnelManager.processTrains();
    }
}



