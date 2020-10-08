package com.lufriraismaulana.latihanstorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    Button btnBuat, btnUbah, btnBaca, btnHapus;
    TextView tvBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        btnBuat = findViewById(R.id.btn_buat);
        btnUbah = findViewById(R.id.btn_ubah);
        btnBaca = findViewById(R.id.btn_baca);
        btnHapus = findViewById(R.id.btn_hapus);
        tvBaca = findViewById(R.id.tv_baca);

        btnBuat.setOnClickListener(this);
        btnUbah.setOnClickListener(this);
        btnBaca.setOnClickListener(this);
        btnHapus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_buat:
                buatFile();
                break;
            case R.id.btn_ubah:
                ubahFile();
                break;
            case R.id.btn_baca:
                bacaFile();
                break;
            case R.id.btn_hapus:
                hapusFile();
                break;
        }
    }

    public void buatFile() {
        String isiFile = "COBA ISI DATA FILE TEXT";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(this, "File berhasil dibuat", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Gagal membuat file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void ubahFile() {
        File file = new File(getFilesDir(), FILENAME);

        if (file.exists()) {
            file.delete();
            String isiFile = "isi data file DIUBAH";

            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file, true);
                outputStream.write(isiFile.getBytes());
                outputStream.flush();
                outputStream.close();
                Toast.makeText(this, "File berhasil diubah", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Gagal mengubah file", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "File tidak ada", Toast.LENGTH_SHORT).show();
        }
    }

    public void bacaFile() {
        File file = new File(getFilesDir(), FILENAME);

        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = openFileInput(FILENAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                tvBaca.setText(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "File tidak ada", Toast.LENGTH_SHORT).show();
        }
    }

    public void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
            tvBaca.setText("");
            Toast.makeText(this, "File telah dihapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "File tidak ada", Toast.LENGTH_SHORT).show();
        }
    }
}