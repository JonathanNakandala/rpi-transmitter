package com.example.jonathan.rpi;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * Created by jonathan on 22/06/15.
 */
public class refreshNetwork extends AsyncTask<String, Integer, String> {


    static {
    }

    private Context mContext;
    private View rootView;

    public refreshNetwork(Context context, View rootView) {
        this.mContext = context;
        this.rootView = rootView;
    }

    @Override
    protected String doInBackground(String... params) {
        String SS = null;
        try {
            //Params = [IP, Port, String to Send]
            SS = refreshData(params[0], params[1]);
        } catch (IOException e) {
            Log.wtf("IOException", "Failed at refreshData method");
        }
        return SS;
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(String result) {
        // Switches


        //Switch Switch2 = (Switch) ((Activity)mContext).findViewById(R.id.pin_2_switch);
        //Switch Switch3 = (Switch) ((Activity)mContext).findViewById(R.id.pin_3_switch);


        String[] splitStatus = result.split("[ ]");
        int sizeSplitStatus = splitStatus.length;
        //Switch 0 Status
        Switch Switch0 = (Switch) ((Activity) mContext).findViewById(R.id.pin_0_switch);
        boolean switch0 = convertToBoolean(splitStatus[0]);
        Switch0.setChecked(switch0);

        //Switch 1 Status
        Switch Switch1 = (Switch) ((Activity) mContext).findViewById(R.id.pin_1_switch);
        boolean switch1 = convertToBoolean(splitStatus[1]);
        Switch1.setChecked(switch1);

        //Switch 2 Status
//        Switch Switch2 = (Switch) ((Activity)mContext).findViewById(R.id.pin_2_switch);
//        boolean switch2 = convertToBoolean(splitStatus[2]);
//        Switch2.setChecked(switch2);
        //Switch 3 Status
//        Switch Switch3 = (Switch) ((Activity)mContext).findViewById(R.id.pin_3_switch);
//        boolean switch3 = convertToBoolean(splitStatus[3]);
//        Switch3.setChecked(switch3);

        Log.wtf("StringData", "0: " + result);
    }

    private boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) ||
                "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
            returnValue = true;
        return returnValue;
    }


    public String refreshData(String Ip, String SPort) throws IOException {
        String refreshString = "Refresh";
        byte[] send_data = refreshString.getBytes();

        int Port = Integer.parseInt(SPort);
        InetAddress IPAddress = InetAddress.getByName(Ip);
        DatagramSocket client_socket = new DatagramSocket(Port);
        DatagramPacket send_packet = new DatagramPacket(send_data, refreshString.length(), IPAddress, Port);
        client_socket.send(send_packet);


        byte[] receive_data = new byte[50];
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


        String[] splitStatus = S.split("[ ]");
        int sizeSplitStatus = splitStatus.length;
        Log.wtf("Split", "Status 1st: " + splitStatus[0]);
        Log.wtf("Split", "Status 2nd: " + splitStatus[1]);


        return S;
    }
}