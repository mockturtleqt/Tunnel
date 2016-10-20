package com.epam.third.controller;


import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

public class Main {
    public static void main(String[] args) {

        Tunnel tunnel = Tunnel.getInstance();
        Train train1 = new Train("1"); train1.setDirection(1);
        Train train2 = new Train("2"); train2.setDirection(1);
        Train train3 = new Train("3"); train3.setDirection(1);
        Train train4 = new Train("4"); train4.setDirection(1);
        Train train5 = new Train("5"); train5.setDirection(1);
        Train train6 = new Train("6"); train6.setDirection(1);
        Train train7 = new Train("7");
        train1.start();
        train2.start();
        train3.start();
        train4.start();
        train5.start();
        train6.start();
        train7.start();
    }

}



