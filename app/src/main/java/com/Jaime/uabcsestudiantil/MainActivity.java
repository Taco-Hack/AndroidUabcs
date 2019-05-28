package com.Jaime.uabcsestudiantil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button ok,registro;
    private EditText email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok=findViewById(R.id.xmlStart);
        registro=findViewById(R.id.xmlBtnRegistro);
        email=findViewById(R.id.xmlETxtEmailLog);
        pass=findViewById(R.id.xmlETxtPassLog);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Registro.class);
                startActivity(i);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() ||
                        pass.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(),
                            "Campos vacios",
                            Toast.LENGTH_SHORT).show();
                }else{

                    //consultar si existe :v

                    Intent i = new Intent(getApplicationContext(), Menu.class);
                    startActivity(i);
                }

            }
        });
    }
}
