package com.epam.third.controller;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

import static com.epam.third.entity.ConstantHolder.MAX_TRAINS_IN_A_ROW;
import static com.epam.third.entity.ConstantHolder.TRAIL_COUNT;

public class SemaphoreController {
    private static Tunnel tunnel1 = Tunnel.getInstance(1);
    private static Tunnel tunnel2 = Tunnel.getInstance(2);

    public static boolean isTunnelEmpty(Tunnel tunnel) {
        return (tunnel.getSemaphore().availablePermits() == TRAIL_COUNT);
    }

    public static boolean isDirectionSame(Train train, Tunnel tunnel) {
        return (train.getDirection() == tunnel.getLastTrainDirection());
    }

    public static boolean canEnterTunnel(Train train, Tunnel tunnel) {
        return (isTunnelEmpty(tunnel) ||
                (!isTunnelEmpty(tunnel) && isDirectionSame(train, tunnel) && (tunnel.getTrainCounter().get() < MAX_TRAINS_IN_A_ROW)));
    }

    public static boolean canRun(Train train) {
        return (canEnterTunnel(train, tunnel1) || canEnterTunnel(train, tunnel2));
    }

    public static Tunnel getAvailableTunnel(Train train) {
        Tunnel availableTunnel = null;
        if (canEnterTunnel(train, tunnel1)) {
            availableTunnel = tunnel1;
        }
        if (canEnterTunnel(train, tunnel2)) {
            availableTunnel = tunnel2;
        }
        return availableTunnel;
    }

}
