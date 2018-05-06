package com.example.user.jhotel_android_farhan;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class BuatPesananActivity extends AppCompatActivity {
    private int currentUserId;
    private int banyakHari;
    private int idHotel;
    private double tariff;
    private String roomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b!=null){
            currentUserId = b.getInt("id_customer");
            idHotel = b.getInt("id_hotel");
            tariff = b.getDouble("dailyTariff");
            roomNumber = b.getString("nomorKamar");
        }

        final TextView room_number = (TextView) findViewById(R.id.room_number);
        final TextView tariff = (TextView) findViewById(R.id.tariff);
        final EditText durasi = (EditText) findViewById(R.id.durasi_hari);
        final TextView total = (TextView) findViewById(R.id.total_biaya);
        final Button hitung = (Button) findViewById(R.id.hitung);
        final Button pesan = (Button) findViewById(R.id.pesan);

        pesan.setVisibility(View.INVISIBLE);
        room_number.setText(roomNumber);
        tariff.setText(String.valueOf(tariff));
        total.setText("0");

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banyakHari = Integer.parseInt(durasi.getText().toString());
                total.setText(String.valueOf(tariff*banyakHari));
                pesan.setVisibility(View.VISIBLE);
                hitung.setVisibility(View.INVISIBLE);
            }
        });

        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                final String jumlah_hari = String.valueOf(banyakHari);
                final String id_customer = String.valueOf(currentUserId);
                final String id_hotel = String.valueOf(idHotel);
                final String nomor_kamar = roomNumber;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            if (jsonResponse != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                                builder.setMessage("Pesanan Success")
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(BuatPesananActivity.this);
                            builder.setMessage("Pesanan Failed.")
                                    .create()
                                    .show();
                        }
                    }
                };
                BuatPesananRequest pesananRequest = new BuatPesananRequest(jumlah_hari, id_customer, id_hotel, nomor_kamar, responseListener);
                RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(pesananRequest);
            }
        });
    }
}
