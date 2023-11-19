package edu.hw6;

import edu.hw6.Task6.PortScanner;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    public void openClosedTest() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(1061);) {
            assertFalse(PortScanner.isTcpOpen(1061));
            assertTrue(PortScanner.isUdpOpen(1061));
        }

        try(DatagramSocket datagramSocket = new DatagramSocket(1061);) {
            assertTrue(PortScanner.isTcpOpen(1061));
            assertFalse(PortScanner.isUdpOpen(1061));
        }

        assertTrue(PortScanner.isTcpOpen(1061));
        assertTrue(PortScanner.isUdpOpen(1061));

    }
}
