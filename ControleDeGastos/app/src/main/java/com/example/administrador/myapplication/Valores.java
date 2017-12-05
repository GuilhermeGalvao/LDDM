package com.example.administrador.myapplication;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class Valores extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
    private ListView lista;
    String valor;
    private Sensor mySensor;
    private SensorManager sensorManager;
    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valores);
        textView = (TextView) findViewById(R.id.textView_Valor);

        Intent intent = getIntent();
        valor = intent.getStringExtra("Valor");

        //mostrar o valor pego pelo OCR
        textView.setText("valor = "  + valor);
//        Toast.makeText(Valores.this, "setText", Toast.LENGTH_SHORT).show();


        Button button = (Button) findViewById(R.id.btn1);
       // Button button2 = (Button) findViewById(R.id.btn2);
        button.setOnClickListener(this);
       // button2.setOnClickListener(this);

        //inicializar o sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        sensorManager.requestTriggerSensor(myListener,mySensor);

        //Carregar Valores gastos
        BancoController crud = new BancoController(getBaseContext());
        List<String> cursos = crud.getAllContacts();
        ListView listaDeCursos = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cursos);
        listaDeCursos.setAdapter(adapter);


        //carregar o valor total gasto
        BancoController crud2 = new BancoController(getBaseContext());
//        setContentView(R.layout.activity_valores);
        TextView textView2 = (TextView) findViewById(R.id.textView3);
        double z = crud2.getContactsCount();
        textView2.setText("Total Gasto" + z);


    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:

                BancoController crud = new BancoController(getBaseContext());
                String resultado;
                Double valor2 = Double.parseDouble(valor.replace(",", "."));
                Toast.makeText(this, "" + valor2 , Toast.LENGTH_SHORT).show();
                resultado = crud.inserirDado(valor2);
                Toast.makeText(this, resultado , Toast.LENGTH_SHORT).show();

                break;

        }
    }
    //sensor para voltar para a tela principal
    public final TriggerEventListener myListener = new TriggerEventListener() {
        @Override
        public void onTrigger(TriggerEvent triggerEvent) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    };



}
