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


    protected String doInBackground(String... params) {
        // do something in background
        //String.

        try {
            server(params[0], params[1]);
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


    public void server(String Ip, String SPort) throws IOException {
        byte[] send_data = new byte[1024];
        String str = "SENT THIS FROM MY MOBILE";
        DatagramSocket client_socket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(Ip);
        int Port = Integer.parseInt(SPort);
        send_data = str.getBytes();


        DatagramPacket send_packet = new DatagramPacket(send_data, str.length(), IPAddress, Port);
        client_socket.send(send_packet);

        client_socket.close();


    }
}
