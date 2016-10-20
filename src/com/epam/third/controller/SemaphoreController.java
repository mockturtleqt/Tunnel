package com.epam.third.controller;

import com.epam.third.entity.ConstantHolder;
import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;
import com.epam.third.entity.TunnelManager;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

import static com.epam.third.entity.ConstantHolder.*;

public class SemaphoreController {

    /*public static boolean isTunnelEmpty() {
        return (Tunnel.getInstance().getFrontEntrance().availablePermits() == MAX_INSTANCE_NUMBER &&
                Tunnel.getInstance().getBackEntrance().availablePermits() == MAX_INSTANCE_NUMBER);
    }

    public static boolean isDirectionSame(Train train) {
        return (train.getDirection() == tunnelTrainDirection());
    }

    public static int tunnelTrainDirection() {
        return (Tunnel.getInstance().getFrontEntrance().availablePermits() == MAX_INSTANCE_NUMBER)?
                FRONT_DIRECTION:
                BACK_DIRECTION;
    }

    public static boolean isTrainWaitingOnTheOtherSide(Train train) {
        return (train.getDirection() == FRONT_DIRECTION) ?
                Tunnel.getInstance().getFrontEntrance().hasQueuedThreads():
                Tunnel.getInstance().getBackEntrance().hasQueuedThreads();
    }

    public static boolean canRun(Train train) {
        return (isTunnelEmpty() ||
                (!isTunnelEmpty() && (isDirectionSame(train) && (Train.getCounter() < 3) ||
                                        !(isTrainWaitingOnTheOtherSide(train)))));
    }

    public static Semaphore getSemaphore(Train train) {
        return (train.getDirection() == FRONT_DIRECTION) ?
                Tunnel.getInstance().getBackEntrance():
                Tunnel.getInstance().getFrontEntrance();
    }*/
    public static boolean isTunnelEmpty() {
        return (TunnelManager.getSemaphore().availablePermits() == POOL_SIZE);
    }

    public static boolean isDirectionSame(Train train) {
        return (train.getDirection() == TunnelManager.getLastTrainDirection());
    }

    public static boolean canRun(Train train) {
        return (isTunnelEmpty() ||
                (!isTunnelEmpty() && isDirectionSame(train) && (TunnelManager.getCounter().get() < 3)));
    }
    //TODO static getCounter() ??

}
