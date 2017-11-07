package com.example.tp2;


import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static com.example.tp2.R.id.btn1;

/**
 * Created by Guilherme Galvão on 13/10/17.
 */

public class ThirdFragment extends Fragment implements View.OnClickListener {

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
        Myview.findViewById(R.id.btn1).setOnClickListener(this);

        return Myview;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case btn1:
//                    File file = new File("GUilhermeG.txt");
//                    abrirArq(file);
                OpenVideo();

                Snackbar.make(view, "Tentei abrir o video", Snackbar.LENGTH_LONG).show();
                break;
        }

    }
    public void OpenVideo(){

        EditText e1 = (EditText) getView().findViewById(R.id.editID);

        String contentE1 = e1.getText().toString();

        Intent app = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + contentE1));
        Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + contentE1));
        try{
            startActivity(app);
        }catch (ActivityNotFoundException ex){
            startActivity(browse);
        }
    }

}