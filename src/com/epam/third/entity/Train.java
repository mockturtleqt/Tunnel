package com.epam.third.entity;

import com.epam.third.controller.SemaphoreController;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Train extends Thread {
    private static Logger logger = Logger.getLogger(Train.class);
    private int direction;
    private String name;
    private Tunnel tunnel;

    public Train(String name, Tunnel tunnel) {
        this.name = name;
        this.tunnel = tunnel;
    }

    public void run() {
        while (!SemaphoreController.canRun(this, tunnel)) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        try {
            tunnel.occupyTrail();

            if (tunnel.getLastTrainDirection() == this.getDirection()) {
                Tunnel.getInstance().incrementCounter();
            } else {
                tunnel.setCounterToZero();
            }
            tunnel.setLastTrainDirection(this.getDirection());
            System.out.println("Train num " + name +
                    " dir " + direction +
                    " tunnel dir " + tunnel.getLastTrainDirection() +
                    " count = " + tunnel.getTrainCounter() +
                    " " + LocalTime.now());
        }
        finally {
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
