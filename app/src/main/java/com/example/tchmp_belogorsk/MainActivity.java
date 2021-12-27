package com.example.tchmp_belogorsk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gpsRegimka = findViewById((R.id.btn_regimka));
        Button perehodRaschetBelMgd = findViewById(R.id.btn_raschetBelMgd);
        Button perehodRaschetBelObl = findViewById(R.id.btn_raschetBelObl);
        Button pzv2019 = findViewById(R.id.btn_pzv2019);
        Button vihod = findViewById(R.id.btn_vihod);

        gpsRegimka.setOnClickListener(this);
        perehodRaschetBelMgd.setOnClickListener(this);
        perehodRaschetBelObl.setOnClickListener(this);
        pzv2019.setOnClickListener(this);
        vihod.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regimka:
                Intent intentRegimka = new Intent(this, Regimka.class);
                startActivity(intentRegimka);
                break;
            case R.id.btn_raschetBelMgd:
                Intent intentRaschetBelMgd = new Intent(this, RaschetTerBelMgd.class);
                startActivity(intentRaschetBelMgd);
                break;
            case R.id.btn_raschetBelObl:
                Intent intentRaschetBelObl = new Intent(this, RaschetTerBelObl.class);
                startActivity(intentRaschetBelObl);
                break;
            case R.id.btn_pzv2019:
                Intent intentPzv2019 = new Intent(this, FullscreenActivity_Pzv2019.class);
                startActivity(intentPzv2019);
                break;
            case R.id.btn_vihod:
                finish();
                break;
            default:
                break;
        }
    }


}
