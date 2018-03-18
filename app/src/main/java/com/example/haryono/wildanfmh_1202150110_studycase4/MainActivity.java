package com.example.haryono.wildanfmh_1202150110_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClikMahaiswa(View view){//method onclick
        Intent next = new Intent(this, CariMahasiswaActivity.class);//inisiasi intent
        startActivity(next);
    }
    public void onClikGambar(View view){//method onclick
        Intent next = new Intent(this, CariGambarActivity.class);//inisiasi intent
        startActivity(next);
    }
}
