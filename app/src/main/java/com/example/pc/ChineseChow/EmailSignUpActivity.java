package com.example.pc.ChineseChow;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Daniel on 4/13/2017.
 */

public class EmailSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText getEditTextPassword;


    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emailsignup_activity);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        getEditTextPassword = (EditText) findViewById(R.id.editTextPassword);


        buttonRegister.setOnClickListener(this);

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = getEditTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email))
        // email is empty
        {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            //stop function execution
            return;
        }

        if (TextUtils.isEmpty(password))
        //password is empty
        {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            //stop function execution
            return;
        }
        // if validations are fine
        // show a progressdialog
        progressDialog.setMessage("Your account is being registered. Please wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // user is successfully registered and logged in
                            Toast.makeText(EmailSignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EmailSignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.hide();
                    }
                });



    }


    @Override
    public void onClick(View view) {
        if (view == buttonRegister)
            registerUser();
    }
}
