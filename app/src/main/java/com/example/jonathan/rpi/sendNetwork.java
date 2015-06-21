package com.example.jonathan.rpi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by jonathan on 17/06/15.
 * Background asynctask that sends input datagram via UDP
 */
public class sendNetwork extends AsyncTask<String, Integer, Void> {
    static {
    }


    @Override
    protected Void doInBackground(String... params) {
        try {
            //Params = [IP, Port, String to Send]
            stringSend(params[0], params[1], params[2]);
        } catch (IOException e) {
            Log.wtf("IOException", "Failed at stringSend method");
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPostExecute(Long result) {

    }

    public void stringSend(String Ip, String SPort, String Data) throws IOException {
        byte[] send_data = Data.getBytes();
        int Port = Integer.parseInt(SPort);
        InetAddress IPAddress = InetAddress.getByName(Ip);
        DatagramSocket client_socket = new DatagramSocket(Port);
        DatagramPacket send_packet = new DatagramPacket(send_data, Data.length(), IPAddress, Port);
        client_socket.send(send_packet);
        client_socket.close();
    }
}
