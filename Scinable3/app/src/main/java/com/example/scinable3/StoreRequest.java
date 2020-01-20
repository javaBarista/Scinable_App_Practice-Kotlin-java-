package com.example.scinable3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class StoreRequest extends StringRequest {
    final static private String URL = "http://nobles1030.cafe24.com/RequestRegister.php";
    private Map<String, String> parameters;

    public StoreRequest(String company, String department, String name, String mail, String phone, String request, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("company", company);
        parameters.put("department", department);
        parameters.put("name", name);
        parameters.put("mail", mail);
        parameters.put("phone", phone);
        parameters.put("request", request);
    }

    public Map<String, String> getParams() {
        return parameters;
    }
}