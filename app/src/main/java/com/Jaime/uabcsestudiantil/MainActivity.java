package com.Jaime.uabcsestudiantil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Jaime.uabcsestudiantil.Ususario.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button ok,registro;
    private EditText email,pass;

    private DatabaseReference bd;
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
                Intent i = new Intent(getApplicationContext(), Menu.class);
                startActivity(i);

                if (email.getText().toString().isEmpty() ||
                        pass.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(),
                            "Campos vacios",
                            Toast.LENGTH_SHORT).show();
                }else{

                    //consultar si existe :v
//                    bd.child("Usuario").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange( DataSnapshot dataSnapshot) {
//
////                            for (DataSnapshot snapshot:dataSnapshot.getChildren() ){
////                                Usuario u= snapshot.getValue(Usuario.class);
////                                if (u.getEmail().equals(email.getText().toString()) ){
////
////
////                                    Toast.makeText(getApplicationContext(),"El email ya existe",Toast.LENGTH_SHORT).show();
////                                    break;
////                                }
////                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled( DatabaseError databaseError) {
//
//                        }
//                    });


//                    Intent i = new Intent(getApplicationContext(), Menu.class);
//                    startActivity(i); // descomentar
                }

            }
        });
    }
}
