package com.example.jonathan.rpi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Date;

/**
 * sendNetwork Class used to communicate with RPI.
 * Created on 05/06/15.
 */
public class sendNetwork extends AsyncTask<String, Integer, String> {
    //private Context mContext;
    private ProgressBar progressSpinner;
    private Context mainContext;

    public sendNetwork(ProgressBar progression, Context contex) {
        progressSpinner = progression;
        mainContext = contex;

    }

    protected String doInBackground(String... params) {
        //Params are IPAddress and Port Passed from editText
        try {
            server(params[0], params[1]);
        } catch (IOException e) {
            Log.wtf("IOException", "Failed at server method");
        }

        return null;
    }

    protected void onPreExecute() {
        // do something before start
    }

    public void onProgressUpdate(Integer... args) {

        progressSpinner.setVisibility(View.INVISIBLE);
        int duration = Toast.LENGTH_SHORT;
        if (args[0] == 1) {

            CharSequence text = "Connection Failed";

            Toast toast = Toast.makeText(mainContext, text, duration);
            toast.show();

        }
        if (args[0] == 0) {
            CharSequence text = "Connection Successful";

            Toast toast = Toast.makeText(mainContext, text, duration);
            toast.show();
        }

    }

    protected void onPostExecute(String result) {
        //  do something after execution


    }


    public void server(String Ip, String SPort) throws IOException {

        byte[] send_data = new byte[1024];
        byte[] receive_data = new byte[50];
        InetAddress IPAddress = InetAddress.getByName(Ip);
        int Port = Integer.parseInt(SPort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String str = "Toast"; //date.toString();


        DatagramSocket client_socket = new DatagramSocket(Port);

        send_data = str.getBytes();


        DatagramPacket send_packet = new DatagramPacket(send_data, str.length(), IPAddress, Port);
        client_socket.send(send_packet);


        DatagramPacket packet = new DatagramPacket(receive_data, receive_data.length);
        // Waits 2 seconds for a reply from rpi
        client_socket.setSoTimeout(2000);
        try {
            client_socket.receive(packet);
        } catch (SocketTimeoutException e) {
            Log.wtf("Socket", "Timeout Exception");
        }
        receive_data = packet.getData();

        String S = new String(receive_data).trim();
        Log.wtf("Receive", "Message: " + S);

        client_socket.close();
        Log.wtf("Socket", "Closed");
        interpret(S);


    }

    public void interpret(String S) {
        int status = -1;
        if (S.equals("Connected")) {
            status = 0;
            publishProgress(status);
        } else {
            status = 1;
            publishProgress(status);
        }
    }
}
