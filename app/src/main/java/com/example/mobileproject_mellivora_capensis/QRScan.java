package com.example.mobileproject_mellivora_capensis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScan extends AppCompatActivity {
    private IntentIntegrator qrScan;
    public static int getId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.setPrompt("바코드를 스캔하세요");
        qrScan.initiateScan();
        setContentView(R.layout.activity_q_r_scan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "존재하지 않는 데이터입니다.", Toast.LENGTH_LONG).show();
                // todo
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                getId = Integer.parseInt(result.getContents());
                Intent intent = new Intent(getApplicationContext(),TypeSelect.class);
                startActivity(intent);
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}