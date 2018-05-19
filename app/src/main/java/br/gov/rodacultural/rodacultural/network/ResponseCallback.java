package br.gov.rodacultural.rodacultural.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Giovanne on 28/06/2016.
 */
public interface ResponseCallback {
    void onSuccess(String jsonStringResponse, int statusCode);
    void onFail(VolleyError error);
}
