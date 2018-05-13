package com.example.user.jhotel_android_farhan;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 13/05/2018.
 */

public class PesananSelesaiRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.2.101:8080/finishpesanan";
    private Map<String, String> params;

    public PesananSelesaiRequest(String id_pesanan, Response.Listener<String> listener) {
        super(Method.POST, Regis_URL, listener, null);
        params = new HashMap<>();
        params.put("id_pesanan",id_pesanan);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
