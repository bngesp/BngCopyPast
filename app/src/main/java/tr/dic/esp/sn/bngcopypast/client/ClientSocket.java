package tr.dic.esp.sn.bngcopypast.client;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by sonyvaio on 22/01/2017.
 */

public class ClientSocket {
    DatagramSocket socket;

    public ClientSocket() throws SocketException {
        this.socket = new DatagramSocket();
        Log.d("serveur", "constructeur de client");

    }

    public ClientSocket sendText(String msg, String add, int port) throws IOException {
        byte[] buffer = msg.getBytes();
        Log.d("donnees", msg);
        DatagramPacket sendPaquet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(add), port);
        socket.send(sendPaquet);
        return this;
    }


    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

}
