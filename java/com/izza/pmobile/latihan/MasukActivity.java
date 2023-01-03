package com.izza.pmobile.latihan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MasukActivity extends AppCompatActivity {
    EditText username, password, editData;
    Button signin, signout;
    DataHelper dh;
//    SessionManager session;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        username = findViewById(R.id.surel1);
        password = findViewById(R.id.sandi2);
        signin = findViewById(R.id.masuk);
        signout = findViewById(R.id.keluar);
        dh = new DataHelper(this);

        sp = getSharedPreferences("Myuserpref", Context.MODE_PRIVATE);
//        session = new SessionManager(getApplicationContext());
//        if(session.isLoggedIn()){
//            Intent intent = new Intent(MasukActivity.this, MasukActivity.class);
//            startActivity(intent);
//            finish();
//        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(MasukActivity.this, "Semua baris harus diisi!", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean periksauserpass = dh.periksausernamepassword(user, pass);
                    if(periksauserpass == true){
                        Toast.makeText(MasukActivity.this, "Berhasil masuk!", Toast.LENGTH_SHORT).show();
                        sp = getSharedPreferences(session, MODE_PRIVATE);
                        editor = sp.edit();
                        editor.putString("getname", username.getText().toString());
                        editor.putString("getpass", password.getText().toString());
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MasukActivity.class);
                        startActivity(intent);
//                        session.saveSession();
//                        session.setLogin(true);
                        finish();
                    }
                    else {
                        Toast.makeText(MasukActivity.this, "Gagal masuk!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = sp.edit();
                editor.clear();
                editor.commit();
            }
        });
    }
}