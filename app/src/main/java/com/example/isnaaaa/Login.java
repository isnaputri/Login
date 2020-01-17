package com.example.isnaaaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        AndroidNetworking.initialize(getApplicationContext());
        final EditText username = findViewById(R.id.txtusername);
        final EditText password = findViewById(R.id.txtpassword);
        final Button button = findViewById(R.id.btlogin);
        final Button btnreg = findViewById(R.id.btregister);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), Register.class);
                startActivity(s);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AndroidNetworking.post(baseUrl.baseURL + "/login.php")
                        .addBodyParameter("username", username.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("suksess", "onRespone: " + response);
                                try {
                                    JSONObject status = response.getJSONObject("hasil");
                                    if (status.getBoolean("sukses")) {
                                        Intent i = new Intent(getApplicationContext(), menu.class);
                                        startActivity(i);
                                        Toast.makeText(Login.this, "suksess1", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(Login.this, "login gagal", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(Login.this, "password dan username salah", Toast.LENGTH_SHORT);

                                }
                                // Toast.makeText(Login.this,"suksess Login", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(ANError error) {
                                Log.d("eror", "onResponse: " + error);
                                Toast.makeText(Login.this, "gagal Login", Toast.LENGTH_SHORT).show();


                            }
                        });

                ;
            }
        });
    }
}
