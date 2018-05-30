package com.example.user.jhotel_android_farhan;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText Fullname = (EditText) findViewById(R.id.FullName);
        final EditText Email = (EditText) findViewById(R.id.Email);
        final EditText Password = (EditText) findViewById(R.id.RegisPass);
        final Button regisButton = (Button) findViewById(R.id.Register);

        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                final String fullname = Fullname.getText().toString();
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();

                if(TextUtils.isEmpty(fullname)){
                    Fullname.setError( "Name is required!" );
                } else if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required!");
                }else if(TextUtils.isEmpty(password)){
                    Password.setError("Password is required!");
                }else{
                Response.Listener<String> responseListener = new Response.Listener<String> () {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            if(jsonResponse!=null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration Success")
                                        .create()
                                        .show();
                                Intent regisInt = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(regisInt);
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Registration Failed.")
                                    .create()
                                    .show();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(fullname,email,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
            }
        });
    }
}
