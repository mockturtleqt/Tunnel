package com.epam.third.service;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

public class TunnelManager {
    public static final int TRAIL_COUNT = 2;
    public static final int MAX_TRAINS_IN_A_ROW = 3;

    private static Tunnel tunnel = Tunnel.getInstance(1);

    public static boolean isTunnelEmpty(Tunnel tunnel) {
        return (tunnel.getSemaphore().availablePermits() == TRAIL_COUNT);
    }

    public static boolean isDirectionSame(Train train, Tunnel tunnel) {
        return (train.getDirection().equals(tunnel.getLastTrainDirection()));
    }

    public static boolean canEnterTunnel(Train train, Tunnel tunnel) {
        return (isTunnelEmpty(tunnel) ||
                (!isTunnelEmpty(tunnel) && isDirectionSame(train, tunnel) && (tunnel.getTrainCounter().get() < MAX_TRAINS_IN_A_ROW)));
    }

    public static boolean canRun(Train train) {
        return (canEnterTunnel(train, tunnel));
    }

    public static Tunnel getTunnel() {
        return tunnel;
    }

}
