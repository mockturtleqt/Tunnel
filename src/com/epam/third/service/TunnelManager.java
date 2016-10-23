package com.epam.third.service;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TunnelManager {
    private static TunnelManager instance;
    private static AtomicInteger instanceCounter = new AtomicInteger(0);
    private Queue<Train> trains = new ArrayDeque<>();
    private List<Tunnel> tunnels = new ArrayList<>();

    private TunnelManager(List<Tunnel> tunnels, Queue<Train> trains) {
        this.tunnels = tunnels;
        this.trains = trains;
    }

    public static TunnelManager getInstance(List<Tunnel> tunnels, Queue<Train> trains) {
        if (instanceCounter.getAndIncrement() < 1) {
            instance = new TunnelManager(tunnels, trains);
        }
        return instance;
    }

    public void processTrains() {
        Random random = new Random();
        for (Train train : trains) {
            Tunnel tunnel = tunnels.get(random.nextInt(tunnels.size()));
            train.setTunnel(tunnel);
            train.start();
        }
    }
}