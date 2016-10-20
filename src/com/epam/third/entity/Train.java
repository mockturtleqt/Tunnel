package com.epam.third.entity;

import com.epam.third.service.TunnelManager;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Train extends Thread {
    private static Logger logger = Logger.getLogger(Train.class);
    private TrainDirection direction;
    private String trainName;

    public Train(String name, TrainDirection direction) {
        this.trainName = name;
        this.direction = direction;
    }

    public void run() {
        TunnelManager.processTrain(this);
    }

    public TrainDirection getDirection() {
        return direction;
    }

    public void setDirection(TrainDirection direction) {
        this.direction = direction;
    }

    public String getTrainName() {
        return trainName;
    }
}
