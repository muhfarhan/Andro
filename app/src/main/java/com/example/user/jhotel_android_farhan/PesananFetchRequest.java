package com.example.user.jhotel_android_farhan;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 13/05/2018.
 */

public class PesananFetchRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.43.176:8080/pesanancustomer/";
    private Map<String, String> params;

    public PesananFetchRequest(String id_customer, Response.Listener<String> listener) {
        super(Method.GET, Regis_URL + id_customer, listener, null);
        params = new HashMap<>();
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
