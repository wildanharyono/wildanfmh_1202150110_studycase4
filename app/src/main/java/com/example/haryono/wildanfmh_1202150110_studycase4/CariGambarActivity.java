package com.example.haryono.wildanfmh_1202150110_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class CariGambarActivity extends AppCompatActivity {
    //Inisiasivariable
    private ImageView ImageView;
    private ProgressDialog progress;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        ImageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.editText);

    }

    public void onClick(View view) {
        String url = editText.getText().toString();
        if(url.isEmpty()){
            Toast.makeText(CariGambarActivity.this,"Masukkan URL gambar terlebih dahulu",Toast.LENGTH_SHORT).show();
        }else {
            new ImageAsyncTask().execute(url);
        }
    }

    private class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress = new ProgressDialog(CariGambarActivity.this);//inisiasi progress dialog
            progress.setTitle("Search Image");
            progress.setIndeterminate(false);
            progress.show();// show progress dialog
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String urlImage = URL[0];

            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(urlImage).openStream();  // mengmbil Image dari url
                bitmap = BitmapFactory.decodeStream(input); //menjalankan bitmap
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView.setImageBitmap(result);// set bitmap ke ImageView
            progress.dismiss();// tutup dialog
        }
    }
}