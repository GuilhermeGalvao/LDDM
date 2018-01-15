package com.example.administrador.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_senha);
        Button btLogin = (Button) findViewById(R.id.btlogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tlogin = (TextView) findViewById(R.id.tlogin);
                TextView tSenha = (TextView) findViewById(R.id.tsenha);
                String login = tlogin.getText().toString();
                String senha = tSenha.getText().toString();
                if(login.equals("Master") && senha.equals("123")){
                    alert("Login efetuado com sucesso");
                }else{
                    alert("Login ou senha incorretos ");
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
    private void alert(String s){
        Toast.makeText(this, s , Toast.LENGTH_LONG).show();
    }
}
