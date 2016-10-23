package com.epam.third.service;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;
import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class TunnelManager {
    private static Logger logger = Logger.getLogger(TunnelManager.class);
    private static TunnelManager instance;
    private static AtomicInteger instanceCounter = new AtomicInteger(0);
    private Queue<Train> trains = new ArrayDeque<>();
    private Queue<Tunnel> tunnels = new ArrayDeque<>();

    private TunnelManager(Queue<Tunnel> tunnels, Queue<Train> trains) {
        this.tunnels = tunnels;
        this.trains = trains;
    }

    public static TunnelManager getInstance(Queue<Tunnel> tunnels, Queue<Train> trains) {
        if (instanceCounter.getAndIncrement() < 1) {
            instance = new TunnelManager(tunnels, trains);
        }
        return instance;
    }

    public void processTrains() {
        for (Train train : trains) {
            train.setTunnel(tunnels.peek());
            train.start();
        }
    }
}