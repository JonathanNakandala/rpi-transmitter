package com.example.jonathan.rpi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

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

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String str = "Toast"; //date.toString();

        //String str = "SENT  FROM MY MOBILE";
        DatagramSocket client_socket = new DatagramSocket(45455);
        InetAddress IPAddress = InetAddress.getByName(Ip);
        int Port = Integer.parseInt(SPort);
        send_data = str.getBytes();


        DatagramPacket send_packet = new DatagramPacket(send_data, str.length(), IPAddress, Port);
        client_socket.send(send_packet);


        byte[] buf = new byte[50];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        client_socket.receive(packet);
        buf = packet.getData();

        String S = new String(buf).trim();
        Log.wtf("LOOK HERE!!!!!", "Received" + S);

        client_socket.close();
        Log.wtf("cl", "CLosed!!!!!");

    }
}
