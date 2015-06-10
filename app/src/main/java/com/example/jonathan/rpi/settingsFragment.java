package com.example.jonathan.rpi;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link settingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link settingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingsFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private settingsFragment listener = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int SectionNumber = 2; // Heading Name in Nav Drawer

    // IP Address & Port


    private OnFragmentInteractionListener mListener;

    public settingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingsFragment newInstance(String param1, String param2) {
        settingsFragment fragment = new settingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // EditTexts and Buttons
        //Button storeRPIAddressButton =  (Button) getActivity().findViewById(R.id.storeRPIAddress);
        //final EditText ipAddressEditText = (EditText) getActivity().findViewById(R.id.ipAddress);
        //EditText portEditText = (EditText) getActivity().findViewById(R.id.portNumber);


//        storeRPIAddressButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //saveIP();
//                String str = ipAddressEditText.getText().toString();
//                Context context = getActivity().getApplicationContext();
//                CharSequence text = "Your IP: " + str;
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//            }
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                SectionNumber);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>ipAddresseditText
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
         void onFragmentInteraction(Uri uri);
    }

//    public void saveIP(View view){
//
//        Button storeRPIAddressButton =  (Button) getActivity().findViewById(R.id.storeRPIAddress);
//        final EditText ipAddressEditText = (EditText) getActivity().findViewById(R.id.ipAddress);
//        EditText portEditText = (EditText) getActivity().findViewById(R.id.portNumber);
//
//
//        String str = ipAddressEditText.getText().toString();
//                Context context = getActivity().getApplicationContext();
//                CharSequence text = "Your IP: " + str;
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//    }

}
