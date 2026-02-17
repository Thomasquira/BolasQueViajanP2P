/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Communications.Connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 *
 * @author thomas
 */
public class Channel implements Runnable {

    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    volatile boolean taVivo = true;
    private Thread thread;
    private HealthChannel healthChannel;

    public Channel(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.healthChannel = new HealthChannel(this);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (taVivo) {
                Object obj = in.readObject();
                procesarMensaje(obj);
            }
        } catch (Exception e) {
            System.out.println("Se fue la conexión parce :((((, el otro se fue unu.");
            close();
        }
    }

    public synchronized void send(Object obj) throws IOException {
        out.writeObject(obj);
        out.flush();
    }

    private void procesarMensaje(Object obj) {
        if (healthChannel != null) {
            healthChannel.Udate();
        }

        if (obj instanceof String && obj.equals("PING")) {
            return; 
        }

        if (obj instanceof DTO.BallDTO) {
            System.out.println("Bola recibida!");
            bolasqueviajanp2p.MasterController.recibirBolaRed((DTO.BallDTO) obj);
        } else {
            System.out.println("Recibido objeto desconocido: " + obj.getClass().getSimpleName());
        }
    }

    public void close() {
        taVivo = false;
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }

    public boolean taVivo() {
        return taVivo && !socket.isClosed();
    }

}
