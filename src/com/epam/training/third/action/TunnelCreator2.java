package com.epam.training.third.action;

import com.epam.training.third.entity.Tunnel;

public class TunnelCreator2 extends Thread {
    public void run() {
        Tunnel t = Tunnel.getInstance();
        System.out.println(t);
    }
}
