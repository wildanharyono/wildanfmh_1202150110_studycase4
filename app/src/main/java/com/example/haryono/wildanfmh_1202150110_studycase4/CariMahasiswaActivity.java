package com.example.haryono.wildanfmh_1202150110_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CariMahasiswaActivity extends AppCompatActivity {

    public ListView listView;
    Button button;
    ArrayAdapter adapter;
    ArrayList<String> list;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_mahasiswa);

    listView = (ListView)findViewById(R.id.listView);
    button = (Button)findViewById(R.id.button);
    }

    public class AsyncTask extends android.os.AsyncTask<String, Integer, String> { //membuat class AsyncTask

        public AsyncTask(ListView listView){ //contructor

            progress = new ProgressDialog(CariMahasiswaActivity.this);  //menginisiasi variabel
            list = new ArrayList<>();
        }
        @Override
        protected void onPreExecute(){ //method preExecuted
            super.onPreExecute();

            progress.setTitle("Loading");      //Pesan pada AsyncTask
            progress.setIndeterminate(false);
            progress.setProgress(0); //ukuran minimal  progres
            progress.setMax(100); //ukuran maksimal progress

            progress.setCancelable(true);
            progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progress.dismiss();
                    AsyncTask.this.cancel(true);
                }
            });
            progress.show(); //menampilkan progres
        }

        @Override
        protected String doInBackground(String... strings){ //method AsyncTask dilakukan
            adapter = new ArrayAdapter<>(CariMahasiswaActivity.this, android.R.layout.simple_list_item_1, list); //inisiasi adapter
            String [] data = getResources().getStringArray(R.array.mahasiswa); //mengambil array
            //membuat pengkondisian untuk menyimpan arrah ke dalam variabel a
            for (int i=0; i<data.length; i++){ //inisiasi kondisi
                final long proses = 100*i/data.length; //membuat formul untuk lama persenan pernama-nya
                final String nama = data[i];
                try{
                    Runnable gantipesan = new Runnable() {
                        @Override
                        public void run() {
                            progress.setMessage((int)proses+"% - Adding - "+nama);
                        }
                    };
                    runOnUiThread(gantipesan);
                    Thread.sleep(300); //mengatur waktu delay
                    list.add(data[i]); //menambahkan item ke dalam variabel yang memuat arraylist

                } catch (InterruptedException e){ // eksekusi gagal
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute (String result){ //method onPost Execute
            super.onPostExecute(result);
            listView.setAdapter (adapter); //setting adapter
            progress.dismiss();//menutup progres bar
        }
    }

    public void onClick (View view){ //method onclick
        new AsyncTask(listView).execute(); //menjalankan AsyncTask
    }
}