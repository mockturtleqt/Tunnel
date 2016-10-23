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
        } finally {
            tunnel.releaseTunnel(this);
        }
    }

    public TrainDirection getDirection() {
        return direction;
    }

    public int getTrainId() {
        return trainId;
    }
}
