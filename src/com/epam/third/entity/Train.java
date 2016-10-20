package com.epam.third.entity;

import com.epam.third.controller.SemaphoreController;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Train extends Thread {
    private static Logger logger = Logger.getLogger(Train.class);
    private int direction;
    private String name;
    private Tunnel tunnel;

    public Train(String name) {
        this.name = name;
    }

    public void run() {
        while (!SemaphoreController.canRun(this)) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        try {
            tunnel = SemaphoreController.getAvailableTunnel(this);
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
