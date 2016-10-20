package com.epam.third.entity;

import com.epam.third.service.TunnelManager;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Train extends Thread {
    private static Logger logger = Logger.getLogger(Train.class);
    private TrainDirection direction;
    private String name;
    private Tunnel tunnel;

    public Train(String name, TrainDirection direction) {
        this.name = name;
        this.direction = direction;
    }

    public void run() {
        while (!TunnelManager.canRun(this)) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        try {
            tunnel = TunnelManager.getAvailableTunnel(this);
            tunnel.occupyTrail();

            if (tunnel.getLastTrainDirection() == this.getDirection()) {
                tunnel.incrementCounter();
            } else {
                tunnel.setCounterToZero();
            }
            tunnel.setLastTrainDirection(this.getDirection());

            System.out.println("Train num " + name +
                    " dir " + direction +
                    " tunnel dir " + tunnel.getLastTrainDirection() +
                    " count = " + tunnel.getTrainCounter() +
                    " " + tunnel + " " + LocalTime.now());
        } finally {
            tunnel.releaseTrail();
        }
    }

    public TrainDirection getDirection() {
        return direction;
    }

    public void setDirection(TrainDirection direction) {
        this.direction = direction;
    }
}
