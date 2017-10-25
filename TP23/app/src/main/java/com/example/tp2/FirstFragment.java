package com.example.tp2;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

import static com.example.tp2.R.id.btn1;
import static com.example.tp2.R.id.btn2;
import static com.example.tp2.R.id.btn3;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {

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
        getActivity().setTitle(subjectName + " : " + "PDF");
        Myview = inflater.inflate(R.layout.fragment_first, container, false);
        Myview.findViewById(R.id.btn1).setOnClickListener(this);
        Myview.findViewById(R.id.btn2).setOnClickListener(this);
        Myview.findViewById(R.id.btn3).setOnClickListener(this);


//        btn1.setOnClickListener(onClickListener);
        return Myview;
    }

        @Override
        public void onClick(View view) {
            switch (Myview.getId()) {
                case btn1:
                    Snackbar.make(Myview, "Matéria adicionada com sucesso!", Snackbar.LENGTH_LONG);

                    break;
                case btn2:
                    Snackbar.make(Myview, "Matéria adicionada com sucesso!", Snackbar.LENGTH_LONG);
                    break;
                case btn3:
                    Snackbar.make(Myview, "Matéria adicionada com sucesso!", Snackbar.LENGTH_LONG);
                    break;
            }

        }
        public void abrirArq(File file){
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
}