package com.epam.third.controller;


import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

public class Main {
    public static void main(String[] args) {

        Tunnel tunnel = Tunnel.getInstance(1);
        Train train1 = new Train("1", 1);
        Train train2 = new Train("2", 1);
        Train train3 = new Train("3", 1);
        Train train4 = new Train("4", 1);
        Train train5 = new Train("5", 1);
        Train train6 = new Train("6", 1);
        Train train7 = new Train("7", 0);
        train1.start();
        train2.start();
        train3.start();
        train4.start();
        train5.start();
        train6.start();
        train7.start();


    }

}



