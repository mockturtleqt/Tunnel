package com.epam.training.third.controller;

import com.epam.training.third.action.TunnelCreator1;
import com.epam.training.third.action.TunnelCreator2;

public class Main {
    public static void main(String[] args) {
        TunnelCreator1 t1 = new TunnelCreator1();
        TunnelCreator2 t2 = new TunnelCreator2();
        t1.start();
        //t1.interrupt();
        t1.run();
        t2.start();
        t2.run();
        t2.run();
    }

}
