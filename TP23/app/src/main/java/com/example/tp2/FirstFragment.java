package com.example.tp2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.tp2.R.id.btn1;

//import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    View Myview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "PDF");
        Myview = inflater.inflate(R.layout.fragment_first, container, false);
        Myview.findViewById(R.id.btn1).setOnClickListener(this);


        return Myview;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case btn1:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
                break;
        }

    }
}