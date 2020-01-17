package com.example.isnaaaa;

import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AndroidNetworking.initialize(getApplicationContext());
        final EditText username = findViewById(R.id.txtusername);
        final EditText password = findViewById(R.id.txtpassword);
        final EditText confirmpassword = findViewById(R.id.txtconfirmpassword);
        Button button = findViewById(R.id.btdaftar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post(baseUrl.baseURL+"/register.php")
                        .addBodyParameter("username",username.getText().toString())
                        .addBodyParameter("password",password.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Log.d("response", "onResponse: " + response);
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    if (hasil.getBoolean("suksess")) {
                                        Toast.makeText(Register.this, "suksess", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(Register.this,"password dan username salah", Toast.LENGTH_SHORT);
                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                    Toast.makeText(Register.this,"password dan username salah", Toast.LENGTH_SHORT);

                                }
                                Toast.makeText(Register.this,"suksess Login", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(Register.this,"gagal Login", Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });
    }
}
