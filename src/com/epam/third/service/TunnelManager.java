package com.epam.third.service;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

public class TunnelManager {
    private static final int TRAIL_COUNT = 2;
    private static final int MAX_TRAINS_IN_A_ROW = 3;

    private static Tunnel tunnel = Tunnel.getInstance(1);

    private static boolean isTunnelEmpty(Tunnel tunnel) {
        return (tunnel.getSemaphore().availablePermits() == TRAIL_COUNT);
    }

    private static boolean isDirectionSame(Train train, Tunnel tunnel) {
        return (train.getDirection().equals(tunnel.getLastTrainDirection()));
    }

    private static boolean canEnterTunnel(Train train, Tunnel tunnel) {
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
