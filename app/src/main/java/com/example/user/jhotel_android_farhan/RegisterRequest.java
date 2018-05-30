package com.example.user.jhotel_android_farhan;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 02/05/2018.
 */

public class RegisterRequest extends StringRequest {
    private static final String Regis_URL = "http://192.168.43.176:8080/newcustomer";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String password,
                           Response.Listener<String> listener) {
        super(Method.POST, Regis_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }
        @Override
        public Map<String, String> getParams() {
            return params;
        }

}
