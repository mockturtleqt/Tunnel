package com.epam.third.controller;


import com.epam.third.entity.Train;

import static com.epam.third.entity.TrainDirection.BACK;
import static com.epam.third.entity.TrainDirection.FRONT;

public class Main {
    public static void main(String[] args) {

        Train train1 = new Train("1", FRONT);
        Train train2 = new Train("2", FRONT);
        Train train3 = new Train("3", FRONT);
        Train train4 = new Train("4", FRONT);
        Train train5 = new Train("5", FRONT);
        Train train6 = new Train("6", FRONT);
        Train train7 = new Train("7", BACK);
        train1.start();
        train2.start();
        train3.start();
        train4.start();
        train5.start();
        train6.start();
        train7.start();


    }

}



