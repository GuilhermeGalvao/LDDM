package com.example.tp2;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.tp2.R.id.btn1;

/**
 * Created by GuilhermeGalvão on 13/10/17.
 */

public class SecondFragment extends Fragment implements View.OnClickListener {
    ArrayList<String> Link = new ArrayList<>();

    View Myview;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<listItem> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*try {

            String[] menuitem = {"Vídeos", "Links"};

            ListView listview = (ListView) Myview.findViewById(R.id.lviw);

            ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1,
                    menuitem
            );

            listview.setAdapter(listViewAdapter);


        }catch(Exception io){
            //TextView textView = (TextView) getView().findViewById(R.id.);
            //io.getMessage();
        }*/

        String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "Link");
        Myview = inflater.inflate(R.layout.fragment_second, container, false);
        Myview.findViewById(R.id.btn1).setOnClickListener(this);
        Link.add("");

        recyclerView = (RecyclerView) Myview.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        listItems = new ArrayList<>();
        String nomeArq = subjectName+"Link.txt";
        File arq = new File(nomeArq);
        try {
            if (arq.exists()) {
                FileReader fileR = new FileReader(nomeArq);
                BufferedReader buffR = new BufferedReader(fileR);
                String linha = buffR.readLine();
                while(!linha.equals(null)){
                    if(!Link.contains(linha)){
                        Link.add(linha);
                    }
                    linha = buffR.readLine();
                }

                buffR.close();
                for(int i = 0; i < Link.size(); i ++){
                    listItem list = new listItem("Link", Link.get(i));
                    listItems.add(list);
                }
            }else{
                String link = "Links";
                listItem list = new listItem(link , "");
                listItems.add(list);
            }

        }catch(IOException e){

        }

        adapter = new MyAdapter(listItems, getActivity());

        recyclerView.setAdapter(adapter);


        return Myview;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case btn1:
                Snackbar.make(view, "Link adicionado com sucesso", Snackbar.LENGTH_LONG).show();
//                    File file = new File("GUilhermeG.txt");
//                    abrirArq(file);
                addInLink();
                break;
        }

    }
    private void addInLink(){
        EditText e1 = (EditText) getView().findViewById(R.id.editText);

        String contentE1 = e1.getText().toString();
        Link.add(contentE1);
        System.out.println("link = " + contentE1);
//
        listItem listItem = new listItem("Link",contentE1);
        String subjectName = this.getArguments().getString("subjectName");
        String nomeArq = subjectName+"Link.txt";

        File arq = new File(nomeArq);
        try {
            if (arq.exists()) {
                FileWriter file = new FileWriter(nomeArq);
                BufferedWriter buffW = new BufferedWriter(file);

                buffW.write(contentE1);
                listItems.add(listItem);

                buffW.close();
                file.close();
            }else{
                FileWriter file = new FileWriter(nomeArq);
                BufferedWriter buffW = new BufferedWriter(file);

                buffW.write(contentE1);
                listItems.add(listItem);

                buffW.close();
                file.close();
            }

        }catch(IOException e){

        }
    }






}
