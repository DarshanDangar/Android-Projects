package com.darshandangar.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link AboutFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class AboutFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AboutFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AboutFragment newInstance(String param1, String param2) {
//        AboutFragment fragment = new AboutFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView tv;
        AppCompatButton overviewbtn,visionbtn,principalmessagebtn,facilitiesbtn,enewsletterbtn,informationbrochurebtn,mandatorydisclosurebtn,rankingbtn,moubtn;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_about, container, false);

        View view = inflater.inflate(R.layout.fragment_about, container, false);
//        TextView tv = view.findViewById(R.id.ft1);
//        return view;

        tv = view.findViewById(R.id.tview);
        overviewbtn = view.findViewById(R.id.overview);
        visionbtn = view.findViewById(R.id.vision);
        principalmessagebtn = view.findViewById(R.id.principalmessage);
        facilitiesbtn = view.findViewById(R.id.facilities);
        enewsletterbtn = view.findViewById(R.id.enewsletter);
        informationbrochurebtn = view.findViewById(R.id.informationbrochure);
        mandatorydisclosurebtn = view.findViewById(R.id.mandatorydisclosure);
        rankingbtn = view.findViewById(R.id.ranking);
        moubtn = view.findViewById(R.id.mou);

       overviewbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent ishare = new Intent(Intent.ACTION_SEND);
               ishare.setType("text/plain");
               ishare.putExtra(Intent.EXTRA_TEXT,"Download this amazing app,https://play.google.com/store/apps/details?id=com.hiteshvirani.googlyplayer");
               startActivity(Intent.createChooser(ishare,"Share via"));
           }
       });


        return view;
    }
}