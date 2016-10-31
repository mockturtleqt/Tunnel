package com.epam.third.controller;


import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;
import com.epam.third.service.TrainAdder;
import com.epam.third.service.TunnelAdder;
import com.epam.third.service.TunnelManager;

import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Train> trains = TrainAdder.addTrains();
        List<Tunnel> tunnels = TunnelAdder.addTunnels();

        TunnelManager tunnelManager = TunnelManager.getInstance(tunnels, trains);
        tunnelManager.processTrains();
    }
}



