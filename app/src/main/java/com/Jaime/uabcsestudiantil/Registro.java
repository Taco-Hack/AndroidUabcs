package com.Jaime.uabcsestudiantil;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {
    private EditText name,lName,email,pass1,pass2;
    private Button ok;
    private DatabaseReference bd;


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

        bd= FirebaseDatabase.getInstance().getReference();

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


                            Map<String, Object> datosUsuario=new HashMap<>();
                            datosUsuario.put("nombre",name.getText().toString().trim());
                            datosUsuario.put("apellido",lName.getText().toString().trim());
                            datosUsuario.put("email",email.getText().toString().trim());
                            datosUsuario.put("password",pass1.getText().toString().trim());

                            datosUsuario.put("semestre",2);
                            datosUsuario.put("notas","hola estas son tus notas:   ");

                            bd.child("Usuario").push().setValue(datosUsuario);

                            name.setText("");
                            lName.setText("");
                            email.setText("");
                            pass1.setText("");
                            pass2.setText("");

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);





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

/*
bd.child("Usuario").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot:dataSnapshot.getChildren() ){
                                        Usuario u= snapshot.getValue(Usuario.class);
                                        if (u.getEmail().equals(email.getText().toString()) ){
                                            bandera[0] =true;

                                            Toast.makeText(getApplicationContext(),"El email ya existe",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });




                            if(bandera[0]){
                                //aqui es la autenticacion
                                Toast.makeText(getApplicationContext(),"prueba",Toast.LENGTH_SHORT).show();
                            }else
                            {

                            }

 */
