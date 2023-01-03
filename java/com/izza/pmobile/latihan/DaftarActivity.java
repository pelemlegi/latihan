package com.izza.pmobile.latihan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity {
    DataHelper dh;
    Button tomboldaftar;
    EditText tulisan1, tulisan2, tulisan3, tulisan4, tulisan5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        dh = new DataHelper(this);
        tulisan1 = findViewById(R.id.nim);
        tulisan2 = findViewById(R.id.nama);
        tulisan3 = findViewById(R.id.surel);
        tulisan4 = findViewById(R.id.sandi1);
        tulisan5 = findViewById(R.id.sandi2);

        tomboldaftar = findViewById(R.id.daftar1);
        tomboldaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim = tulisan1.getText().toString();
                String nama = tulisan2.getText().toString();
                String surel = tulisan3.getText().toString();
                String sandi1 = tulisan4.getText().toString();
                String sandi2 = tulisan5.getText().toString();

                if(TextUtils.isEmpty(nim) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(surel) || TextUtils.isEmpty(sandi1) || TextUtils.isEmpty(sandi2)){
                    Toast.makeText(DaftarActivity.this, "Semua baris harus diisi!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(sandi1.equals(sandi2)){
                        boolean checkuser = dh.periksausername(surel);
                        if(checkuser == false){
                            boolean insert = dh.tambahpengguna(surel, sandi1);
                            if(insert == true){
                                Toast.makeText(DaftarActivity.this, "Berhasil daftar!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(DaftarActivity.this, "Gagal daftar!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(DaftarActivity.this, "Pengguna sudah ada!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(DaftarActivity.this, "Kata sandi tidak cocok!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}