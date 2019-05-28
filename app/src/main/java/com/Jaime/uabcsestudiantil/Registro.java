package com.Jaime.uabcsestudiantil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    private EditText name,lName,email,pass1,pass2;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        name=findViewById(R.id.xmlETxtName);
        lName=findViewById(R.id.xmlETxtLName);
        email=findViewById(R.id.xmlETxtEmail);
        pass1=findViewById(R.id.xmlETxtPas1);
        pass2=findViewById(R.id.xmlETxtPas2);
        ok=findViewById(R.id.xmlBtnOk);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()||
                        lName.getText().toString().isEmpty()||
                        email.getText().toString().isEmpty()||
                        pass1.getText().toString().isEmpty()||
                        pass2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Campos vacios",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if (pass1.getText().toString().equals(pass2.getText().toString())){
                        if (pass1.getText().toString().trim().length()>6 ){

                        }else{
                            Toast.makeText(getApplicationContext(),
                                    "Contraseñas muy pequeña",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "Contraseñas incorrectas",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
