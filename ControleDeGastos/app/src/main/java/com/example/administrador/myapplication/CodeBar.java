package com.example.administrador.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodeBar extends AppCompatActivity implements View.OnClickListener {

    private ZXingScannerView scannerView;
    TextView textView ;
    TextView textView2 ;
    String valor;
    String valorOficial;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_bar);

        Intent intent = getIntent();
        valor = intent.getStringExtra("Valor");
        if(valor.contains("R") || valor.contains("$")){
            if(valor.contains("R$")){
                for(int i = 2; i < valor.length(); i ++) {
                    valorOficial = "" + valor.charAt(i);
                }
            }else{
                for(int i = 1; i < valor.length(); i ++) {
                    valorOficial = "" + valor.charAt(i);
                }
            }
        }
        code = intent.getStringExtra("Code");
        textView = (TextView) findViewById(R.id.Valor);
        textView.setText(valor);
        textView2 = (TextView) findViewById(R.id.Code);
        textView2.setText(code);

        Button SaveData = (Button) findViewById(R.id.SaveData);
        SaveData.setOnClickListener(this);

        Button Stop = (Button) findViewById(R.id.Stop);
        Stop.setOnClickListener(this);

    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SaveData:

//                BancoController crud = new BancoController(getBaseContext());
//                String resultado;
//                Double valor2 = Double.parseDouble(valor.replace(",", "."));
//                Toast.makeText(this, "" + valor2 , Toast.LENGTH_SHORT).show();
//                resultado = crud.inserirDado(valor2);
//                Toast.makeText(this, resultado , Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(),  MainActivity.class);
                startActivity(intent2);

                break;
            case R.id.Stop:
                Intent intent = new Intent(getApplicationContext(), Valores.class);
                startActivity(intent);
                break;

        }
    }
}
