/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bolasqueviajanp2p;

import java.io.IOException;
import controller.Controller;
import Communications.CommunicationsController;
import DTO.BallDTO;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author thomas
 */
public class MasterController {

    public static Controller controller;
    public static CommunicationsController communicationsController;

    private static final String IP_EQUIPO_1 = "172.16.8.25"; // Ip del thomas
    private static final String IP_EQUIPO_2 = "172.16.8.23"; // IP del otro

    public MasterController() throws IOException {
        String miIP = obtenerMiIP();
        String peerHost = obtenerIPRemota(miIP);

        System.out.println("Mi IP: " + miIP + " | IP Remota a conectar: " + peerHost);

        controller = new Controller();
        controller.getView().startViewer();

        communicationsController = new CommunicationsController(10000, 10001, peerHost, 10001, 10000);
        communicationsController.start();
    }

    private String obtenerIPRemota(String miIP){
        if (miIP.equals(IP_EQUIPO_1)) {
            return IP_EQUIPO_2;
        } else if (miIP.equals(IP_EQUIPO_2)) {
            return IP_EQUIPO_1;
        } else {
            System.out.println("MODO DESARROLLO: IP no reconocida, usando localhost");
            return "localhost";
        }
    }

    private String obtenerMiIP(){
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (!iface.isUp() || iface.isLoopback()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    
                    if (addr instanceof Inet4Address) {
                        String ip = addr.getHostAddress();
                        
                        if (ip.startsWith("172.16.8.")) { 
                            return ip;
                        }
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress();

        } catch (Exception e) {
            System.err.println("Error obteniendo IP: " + e.getMessage());
            return "localhost";
        }
    }

    public static void enviar(Object obj) {
        if (communicationsController != null) {
            try {
                if (communicationsController.getConnection() != null) {
                    communicationsController.getConnection().send(obj);
                } else {
                    System.out.println("Conexión aún no establecida, bola perdida.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void recibirBolaRed(BallDTO bola) {
        if (controller != null) {
            controller.recibirBola(bola);
        }
    }
}
