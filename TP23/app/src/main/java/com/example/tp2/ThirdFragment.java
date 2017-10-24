package com.example.tp2;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class ThirdFragment extends Fragment {

    View Myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        /*String[] menuitem = {"Vídeos", "Links"};

        ListView listview = (ListView) Myview.findViewById(R.id.lviw);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                menuitem
        );

        listview.setAdapter(listViewAdapter);*/
        String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "Video");

        Myview = inflater.inflate(R.layout.fragment_third, container, false);

        return Myview;
    }
}