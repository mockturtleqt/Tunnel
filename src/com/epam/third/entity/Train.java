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
            TunnelManager.getResource();
            TimeUnit.SECONDS.sleep(3);

            if (TunnelManager.getLastTrainDirection() == this.getDirection()) {
                TunnelManager.incrementCounter();
            }
            else {
                TunnelManager.setCounterToZero();
            }
            TunnelManager.setLastTrainDirection(this.getDirection());
            System.out.println("Train num " + name +
                                " dir " + direction +
                                " tunnel dir " + TunnelManager.getLastTrainDirection() +
                                " count = " + TunnelManager.getCounter() +
                                " " + LocalTime.now());

        } catch (InterruptedException e) {
            logger.error(e, e);
        }
        finally {
            TunnelManager.returnResource();
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
