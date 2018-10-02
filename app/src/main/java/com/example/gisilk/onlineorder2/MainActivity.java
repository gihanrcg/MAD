package com.example.gisilk.onlineorder2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button enterButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        enterButton = (Button) findViewById(R.id.btnEnter);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        enterButton.setOnClickListener(this);



    }

    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                }
                else{
                    Toast.makeText(MainActivity.this,"Invalid email passs",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }
    @Override
    public void onClick(View view){
        if(view == enterButton){
            userLogin();
        }
    }

    //private void registerUser() {

      //  String email = editTextEmail.getText().toString().trim();
        //String password = editTextPassword.getText().toString().trim();

    //}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
