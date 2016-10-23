package com.epam.third.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Train extends Thread {
    private static final Logger logger = Logger.getLogger(Train.class);
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
            while (!tunnel.occupyTunnel(this)) {
                TimeUnit.MICROSECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
        finally {
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