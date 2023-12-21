package edu.hw6.Task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PortScanner {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int MAX_PORT_COUNT = 49151;

    private final static String UDP = "UDP";
    private final static String TCP = "TCP";
    private final static String SEPARATOR = "|";

    private final static String HEADER = "Protocol       Port     Status      App";
    private final static Map<Integer, String> PORTS_AND_APPS = Map.of(
        135, "EPMAP",
        137, "NetBIOS Name Service",
        138, "NetBIOS Datagram Service",
        139, "NetBIOS Session Service",
        445, "Microsoft-DS (Active Directory)",
        843, "Adobe Flash",
        1900, "Simple Service Discovery Protocol (SSDP)",
        3702, "Dynamic Web Services Discovery"
    );

    private PortScanner() {

    }

    public static List<PortInfo> checkAllPorts() {
        List<PortInfo> ports = new ArrayList<>();
        for (int port = 0; port <= MAX_PORT_COUNT; ++port) {
            if (isTcpOpen(port) && isUdpOpen(port)) {
                ports.add(new PortInfo(UDP + SEPARATOR + TCP, port, false));
            } else if (isUdpOpen(port)) {
                ports.add(new PortInfo(UDP, port, false));
            } else if (isTcpOpen(port)) {
                ports.add(new PortInfo(TCP, port, false));
            } else {
                ports.add(new PortInfo(UDP + SEPARATOR + TCP, port, true));
            }
        }
        return ports;
    }

    public static boolean isTcpOpen(int port) {
        try (ServerSocket tcpSocket = new ServerSocket(port)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUdpOpen(int port) {
        try (DatagramSocket tcpSocket = new DatagramSocket(port)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void printAll(List<PortInfo> ports) {
        LOGGER.info(HEADER);
        for (var a : ports) {
            LOGGER.info(a.udpAndTcp() + a.port()
                + (a.used() ? "Closed" : "Open")
                + PORTS_AND_APPS.getOrDefault(a.port(), ""));
        }
    }
}
