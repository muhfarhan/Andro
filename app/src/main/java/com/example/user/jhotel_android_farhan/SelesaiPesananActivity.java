package com.example.user.jhotel_android_farhan;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class SelesaiPesananActivity extends AppCompatActivity {
    private String currentUserId;
    private int id_pesan;
    private int biaya_total;
    private int total_hari;
    private String tanggal_pesan;
    private TextView idPesanan;
    private TextView biaya;
    private TextView jumlahHari;
    private TextView tglPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

        final Handler handler = new Handler();
        final Button batal = (Button) findViewById(R.id.batal);
        final Button selesai = (Button) findViewById(R.id.selesai);

        idPesanan = (TextView) findViewById(R.id.id_pesanan);
        biaya = (TextView) findViewById(R.id.biaya_akhir);
        jumlahHari = (TextView) findViewById(R.id.jumlah_hari);
        tglPesan = (TextView) findViewById(R.id.tanggal);

        currentUserId = getIntent().getStringExtra("id_customer");
        System.out.println(currentUserId);
        fetchPesanan(currentUserId);

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Batal Success")
                                        .create()
                                        .show();
                                Intent regisInt = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                SelesaiPesananActivity.this.startActivity(regisInt);
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Batal Failed.")
                                    .create()
                                    .show();
                        }
                    }
                };
                PesananBatalRequest batalRequest = new PesananBatalRequest(String.valueOf(id_pesan), responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(batalRequest);
            }
        });

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                                builder.setMessage("Selesai Success")
                                        .create()
                                        .show();
                                Intent regisInt = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                SelesaiPesananActivity.this.startActivity(regisInt);
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Selesai Failed.")
                                    .create()
                                    .show();
                        }
                    }
                };
                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(String.valueOf(id_pesan), responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(selesaiRequest);
            }
        });
    }
    public void fetchPesanan(final String id_customer){
        Response.Listener<String> responseListener = new Response.Listener<String> () {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);

                    id_pesan = jsonResponse.getInt("id_pesanan");
                    biaya_total = jsonResponse.getInt("biaya_akhir");
                    total_hari = jsonResponse.getInt("jumlah_hari");
                    tanggal_pesan = jsonResponse.getString("tanggal");

                    System.out.println("id_customer = " +id_customer);
                    System.out.println("id_pesanan = " +id_pesan);
                    System.out.println("biaya_akhir = " +biaya_total);
                    System.out.println("jumlah_hari = " +total_hari);
                    System.out.println("tanggal = " +tanggal_pesan);

                    idPesanan.setText(String.valueOf(id_pesan));
                    biaya.setText(String.valueOf(biaya_total));
                    jumlahHari.setText(String.valueOf(total_hari));
                    tglPesan.setText(tanggal_pesan);

                } catch (JSONException e) {
                    Intent gagalInt = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                    gagalInt.putExtra("id_customer", currentUserId);
                    SelesaiPesananActivity.this.startActivity(gagalInt);
                }
            }
        };
        PesananFetchRequest fetchPesananRequest = new PesananFetchRequest(id_customer, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(fetchPesananRequest);
    }
}
