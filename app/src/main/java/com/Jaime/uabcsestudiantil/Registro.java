package com.Jaime.uabcsestudiantil;

import android.app.Activity;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.app.PendingIntent.getActivity;

public class Registro extends AppCompatActivity {
    private EditText name,lName,email,pass1,pass2;
    private Button ok;
    private FirebaseAuth mAuth;


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

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();




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

                           //aqui es la autenticacion

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

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user != null) {
//            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
//                    user.getEmail(), user.isEmailVerified()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.xmlETxtPas1).setVisibility(View.GONE);
            findViewById(R.id.xmlETxtPas2).setVisibility(View.GONE);
            //findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            findViewById(R.id.xmlETxtEmail).setEnabled(!user.isEmailVerified());
        } else {
//            mStatusTextView.setText(R.string.signed_out);
//            mDetailTextView.setText(null);

            findViewById(R.id.xmlETxtPas1).setVisibility(View.GONE);
            findViewById(R.id.xmlETxtPas2).setVisibility(View.GONE);
            //findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
    }


}
