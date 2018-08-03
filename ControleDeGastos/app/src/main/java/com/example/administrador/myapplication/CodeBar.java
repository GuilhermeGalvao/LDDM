package com.example.administrador.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodeBar extends AppCompatActivity {
    private EditText editText;
    private ZXingScannerView scannerView;
    protected TextView textView ;
    protected TextView textView2 ;
    protected String valor;
    protected String code;
    protected String full;
    private DatabaseReference mDatabase;
    private  String mUserId;
    private  FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_bar);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        valor = intent.getStringExtra("Valor");
        if(valor == null){
            Toast.makeText(CodeBar.this, "Por favor inserir um valor abaixo ", Toast.LENGTH_LONG).show();
        }else{
            if(valor.contains("R") && valor.contains("$")){
                valor.replace("R", " ");
                valor.replace("$", " ");
            }else if(valor.contains("R") && !valor.contains("$")){
                valor.replace("R", " ");
            }else if(valor.contains("$") && !valor.contains("R")){
                valor.replace("$", " ");
            }
        }
        code = intent.getStringExtra("Code");

        if(mFirebaseUser == null){

        }else{
            mUserId = mFirebaseUser.getUid();

//            final TextView text = (TextView) findViewById(R.id.Code);
            final Button button = (Button) findViewById(R.id.SaveData);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    full = valor + "\t" +code;
                    mDatabase.child("users").child(mUserId).child("items").push().child("Itens adicionados").setValue(full);
                    Intent intent = new Intent(CodeBar.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        textView = (TextView) findViewById(R.id.Valor);
        textView.setText(valor);
        textView2 = (TextView) findViewById(R.id.Code);
        textView2.setText(code);
        Button button2 = (Button) findViewById(R.id.Stop);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CodeBar.this, Valores.class);
                startActivity(intent);
            }
        });

        if(mFirebaseUser == null){

        }else{
            mUserId = mFirebaseUser.getUid();

//            final TextView text = (TextView) findViewById(R.id.Code);
            final Button button3 = (Button) findViewById(R.id.SaveAndStop);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    full = valor + "\t" +code;
                    mDatabase.child("users").child(mUserId).child("items").push().child("Itens adicionados").setValue(full);
                    Intent intent = new Intent(CodeBar.this, Valores.class);
                    startActivity(intent);
                }
            });
        }

       editText = (EditText) findViewById(R.id.NewValue);

        Button replaceValue = (Button)findViewById(R.id.ReplaceValue);
        replaceValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newValue = editText.getText().toString();
                valor = newValue;
                textView.setText(newValue);
            }
        });

    }
}
