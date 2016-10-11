package com.epam.training.third.action;

import com.epam.training.third.entity.Tunnel;

public class TunnelCreator1 extends Thread{
    public void run() {
        try {
            System.out.println("TunnelCreator1 before sleep");
            Thread.sleep(50);
            System.out.println("TunnelCreator1 after sleep");
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        Tunnel t = Tunnel.getInstance();
        System.out.println(t);
    }
}
