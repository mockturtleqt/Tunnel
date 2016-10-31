package com.epam.third.service;

import com.epam.third.entity.Tunnel;

import java.util.ArrayList;
import java.util.List;

public class TunnelAdder {
    public static List<Tunnel> addTunnels() {
        List<Tunnel> tunnels = new ArrayList<>();
        tunnels.add(new Tunnel(1));
        tunnels.add(new Tunnel(2));
        return tunnels;
    }
}
