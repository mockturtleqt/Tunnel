package com.epam.third.controller;


import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

public class Main {
    public static void main(String[] args) {

        Tunnel tunnel = Tunnel.getInstance();
        Train train1 = new Train("1", tunnel);
        train1.setDirection(1);
        Train train2 = new Train("2", tunnel);
        train2.setDirection(1);
        Train train3 = new Train("3", tunnel);
        train3.setDirection(1);
        Train train4 = new Train("4", tunnel);
        train4.setDirection(1);
        Train train5 = new Train("5", tunnel);
        train5.setDirection(1);
        Train train6 = new Train("6", tunnel);
        train6.setDirection(1);
        Train train7 = new Train("7", tunnel);
        train1.start();
        train2.start();
        train3.start();
        train4.start();
        train5.start();
        train6.start();
        train7.start();
    }

}



