package com.epam.third.entity;

import com.epam.third.controller.SemaphoreController;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Train extends Thread {
    private static Logger logger = Logger.getLogger(Train.class);
    private static AtomicInteger counter = new AtomicInteger(0);
    private String name;

    public Train(String name) {
        this.name = name;
    }

    private int direction;

    public void run() {
        while (!SemaphoreController.canRun(this)) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        try {
            //SemaphoreController.getSemaphore(this).acquire();
            Tunnel.getInstance().getBackEntrance().acquire();
            Tunnel.getInstance().getFrontEntrance().acquire();
            System.out.println("Train " + name + " " + direction);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error(e);
        } finally {
            //SemaphoreController.getSemaphore(this).release();
            Tunnel.getInstance().getBackEntrance().release();
            Tunnel.getInstance().getFrontEntrance().release();
        }
    }

    public static int getCounter() {
        return counter.get();
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}