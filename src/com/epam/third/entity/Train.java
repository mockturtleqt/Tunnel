package com.epam.third.entity;

import java.time.LocalTime;

public class Train extends Thread {
    private Tunnel tunnel;
    private int trainId;
    private TrainDirection direction;

    public Train(int id, TrainDirection direction) {
        this.trainId = id;
        this.direction = direction;
    }

    public void setTunnel(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    public void run() {
        try {
            tunnel.occupyTunnel(this);
            System.out.println("Train " + trainId + " got tunnel " + tunnel.getTunnelId() + " " + LocalTime.now());
        } finally {
            tunnel.releaseTunnel();
            System.out.println("Train " + trainId + " releases tunnel " + tunnel.getTunnelId() + " " + LocalTime.now());
        }
    }

    public TrainDirection getDirection() {
        return direction;
    }
}
