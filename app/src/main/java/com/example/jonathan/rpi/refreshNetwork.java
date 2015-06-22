package com.example.jonathan.rpi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by jonathan on 22/06/15.
 */
public class refreshNetwork extends AsyncTask<String, Integer, Void> {
    static {
    }


    @Override
    protected Void doInBackground(String... params) {
        try {
            //Params = [IP, Port, String to Send]
            refreshData(params[0], params[1]);
        } catch (IOException e) {
            Log.wtf("IOException", "Failed at refreshData method");
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPostExecute(Long result) {

    }


    public void refreshData(String Ip, String SPort) throws IOException {
        String refreshString = "Refresh";
        byte[] send_data = refreshString.getBytes();

        int Port = Integer.parseInt(SPort);


        InetAddress IPAddress = InetAddress.getByName(Ip);
        DatagramSocket client_socket = new DatagramSocket(Port);
        DatagramPacket send_packet = new DatagramPacket(send_data, refreshString.length(), IPAddress, Port);
        client_socket.send(send_packet);
        client_socket.close();
    }
}