package com.epam.third.action;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

import java.util.concurrent.Semaphore;

import static com.epam.third.entity.ConstantHolder.BACK_DIRECTION;
import static com.epam.third.entity.ConstantHolder.FRONT_DIRECTION;
import static com.epam.third.entity.ConstantHolder.MAX_INSTANCE_NUMBER;

public class SemaphoreController {
    public static boolean isTunnelEmpty() {
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

    public static boolean isTrainWaintingOnTheOtherSide(Train train) {
        return (train.getDirection() == FRONT_DIRECTION) ?
                Tunnel.getInstance().getFrontEntrance().hasQueuedThreads():
                Tunnel.getInstance().getBackEntrance().hasQueuedThreads();
    }

    public static boolean canRun(Train train) {
        return (isTunnelEmpty() ||
                (!isTunnelEmpty() && (isDirectionSame(train) && (Train.getCounter() < 3) ||
                                        !(isTrainWaintingOnTheOtherSide(train)))));
    }

    public static Semaphore getSemaphore(Train train) {
        return (train.getDirection() == FRONT_DIRECTION) ?
                Tunnel.getInstance().getBackEntrance():
                Tunnel.getInstance().getFrontEntrance();
    }
}
