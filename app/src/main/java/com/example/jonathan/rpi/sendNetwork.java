package com.example.jonathan.rpi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * sendNetwork Class used to communicate with RPI.
 * Created on 05/06/15.
 */
public class sendNetwork extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... u) {
        // do something in background
        try {
            server();
        } catch (IOException e) {
            Log.e("YOU GOOFed", "SOCKET EXCEPTION");
        }
        return null;
    }

    protected void onPreExecute() {
        // do something before start
    }

    public void onProgressUpdate(Integer... args) {

    }

    protected void onPostExecute(String result) {
        //  do something after execution


    }


    public void server() throws IOException {
        byte[] send_data = new byte[1024];
        String str = "SENT THIS FROM MY MOBILE";
        DatagramSocket client_socket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("192.168.0.2");

        send_data = str.getBytes();


        DatagramPacket send_packet = new DatagramPacket(send_data, str.length(), IPAddress, 45455);
        client_socket.send(send_packet);

        client_socket.close();


    }
}
