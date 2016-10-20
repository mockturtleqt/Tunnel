package com.epam.third.controller;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

import static com.epam.third.entity.ConstantHolder.TRAIL_COUNT;

public class SemaphoreController {
    public static boolean isTunnelEmpty(Tunnel tunnel) {
        return (tunnel.getSemaphore().availablePermits() == TRAIL_COUNT);
    }

    public static boolean isDirectionSame(Train train, Tunnel tunnel) {
        return (train.getDirection() == tunnel.getLastTrainDirection());
    }

    public static boolean canRun(Train train, Tunnel tunnel) {
        return (isTunnelEmpty(tunnel) ||
                (!isTunnelEmpty(tunnel) && isDirectionSame(train, tunnel) && (tunnel.getTrainCounter().get() < 3)));
    }
}
