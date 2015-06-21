package com.example.jonathan.rpi;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String PREFS_NAME = "rpi-transmitter-pref-file";


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    //settingsFragments Strings
    private String ipAddress;
    private String port;



    public void onsettingsFragmentEditTextChanged(String string, String string2) {
        ipAddress = string;
        port = string2;
    }


    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // RelativeLayout lt = (RelativeLayout) findViewById( R.id.container );
        // LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    // Edit Texts
        View view;
        // EditText ipAddressEditText = (EditText) findViewById(R.id.ipAddress);
        // EditText portEditText = (EditText) findViewById(R.id.portNumber);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // Stored Preference Data
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String ipAddress = settings.getString("IPAddress", "192.168.0.22");
        String portNumber = settings.getString("PortNumber", "7777");
        Log.wtf("GUTENTAG", settings.getString("IPAddress", "192.168.0.22"));
        //ipAddressEditText.getText().toString();
        //portEditText.setText(portNumber);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position) {
            default:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();

                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, settingsFragment.newInstance("a","a"))
                        .commit();

                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will Mercury could still bring a huge crowd to its feet.
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
// nmj

        return super.onOptionsItemSelected(item);
    }

    public void saveIP(View view) {
        //Preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        //UI Objects
        Button storeRPIAddressButton = (Button) findViewById(R.id.storeRPIAddress);
        EditText ipAddressEditText = (EditText) findViewById(R.id.ipAddress);
        EditText portEditText = (EditText) findViewById(R.id.portNumber);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //Lowercase is input text. Uppercase is hardcoded
        String ip = ipAddressEditText.getText().toString();
        String port = portEditText.getText().toString();
        //Store Preferences
        editor.putString("IPAddress", ip).apply();
        editor.putString("PortNumber", port).apply();
        editor.commit();

        // Details to Send
        String ipAdd = settings.getString("IPAddress", "192.168.0.22");
        String portNum = settings.getString("PortNumber", "45455");
        Context context = getApplicationContext();
//        CharSequence text = "Your IP: " + ip + " Your Port:" + port;
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
        progressBar.setVisibility(View.VISIBLE);
        Log.wtf("GUTENTAG2", ipAdd);
        Log.wtf("GUTENTAG2", portNum);
        new testNetwork(progressBar, context).execute(ipAdd, portNum);
        //sendNotification(view);

    }

    public void sendData(String Message) {
        // new testNetwork()
        EditText ipAddressEditText = (EditText) findViewById(R.id.ipAddress);
        EditText portEditText = (EditText) findViewById(R.id.portNumber);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//        String ip = ipAddressEditText.getText().toString();
//        String port = portEditText.getText().toString();
        String IP = settings.getString("IPAddress", "192.168.0.2");
        String PORT = settings.getString("PortNumber", "45455");
        new sendNetwork().execute(IP, PORT, Message);
    }

    // Creates a Notification
    public void sendNotification(View view) {

        // Use NotificationCompat.Builder to set up our notification.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        //icon appears in device notification bar and right hand corner of notification
        builder.setSmallIcon(R.drawable.ic_drawer);

        // This intent is fired when notification is clicked
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);

        // Large icon appears on the left of the notification
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_drawer));

        // Content title, which appears in large type at the top of the notification
        builder.setContentTitle("Harro");

        // Content text, which appears in smaller text below the title
        builder.setContentText("HIIIII");

        // The subtext, which appears under the text on newer devices.
        // This will show-up in the devices with Android 4.2 and above only
        builder.setSubText("Tap to view documentation about notifications.");

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }

    //Switch 0
    public void pin0buttonclicked(View view) {
        boolean on = ((Switch) view).isChecked();
        if (on) {
            // Enable vibrate
            sendData("0 on");
            //  v.vibrate(20000);
        } else {
            // Disable vibrate
            sendData("0 off");
            // v.cancel();
        }
    }

    // Switch 1
    public void pin1buttonclicked(View view) {
        boolean on = ((Switch) view).isChecked();
        // Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (on) {
            // Enable vibrate
            sendData("1 on");
            //  v.vibrate(20000);
        } else {
            // Disable vibrate
            sendData("1 off");
            // v.cancel();
        }

    }

    public void AllOnButtonPress(View view) {
        sendData("All on");
    }

    public void AllOffButtonPress(View view) {
        sendData("All off");
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
