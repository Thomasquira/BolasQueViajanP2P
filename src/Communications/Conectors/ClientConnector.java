/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Communications.Conectors;

import Communications.Connections.Channel;
import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author thomas
 */
public class ClientConnector implements Runnable {

    private String host;
    private int port1, port2;
    private Channel connection;
    private int forbiddenPort = -1;

    public ClientConnector(String host, int port1, int port2) {
        this.host = host;
        this.port1 = port1;
        this.port2 = port2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (connection != null && !connection.taVivo()) {
                    connection = null;
                    System.out.println("Conexión detectada muerta. Reiniciando búsqueda...");
                }

                if (connection == null) {
                    if (port1 != forbiddenPort) {
                        probarConexion(port1);
                    }
                    if (connection == null && port2 != forbiddenPort) {
                        probarConexion(port2);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                connection = null;
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    System.out.println("Intentando conectarse");
                }
            }
        }
    }

    public void probarConexion(int port) {
        try {
            // System.out.println("Probando conexión con: " + port);
            Socket socket = new Socket();
            socket.connect(new java.net.InetSocketAddress(host, port), 200);
            connection = new Channel(socket);
            System.out.println("Cliente conectado a: " + port);
        } catch (IOException ignored) {
            //System.out.println("Probando otro puerto");
          
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort1() {
        return port1;
    }

    public void setPort1(int port1) {
        this.port1 = port1;
    }

    public int getPort2() {
        return port2;
    }

    public void setPort2(int port2) {
        this.port2 = port2;
    }

    public Channel getConnection() {
        return connection;
    }

    public void setConnection(Channel connection) {
        this.connection = connection;
    }

    public void setForbiddenPort(int port) {
        this.forbiddenPort = port;
    }

}
