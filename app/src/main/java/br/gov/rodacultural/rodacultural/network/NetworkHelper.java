package br.gov.rodacultural.rodacultural.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Giovanne on 28/06/2016.
 */
public class NetworkHelper {
    private static final String TAG = NetworkHelper.class.getSimpleName();

    private static NetworkHelper instance;
    private static Context context;
    private RequestQueue requestQueue;
    private CustomRequest request;

    public static final String DOMINIO = "http://resultados.sisvida.com.br/";

    private final String API = "api/v1/";
    /*UserService*/
    private final String LOGIN = API + "login";
    private final String SIGNUP = API + "signUp";
    private final String GET_LABORATORIES = API + "laboratories";
    private final String RESULT = API + "results";
    private final String GET_CITIES = API + "state";
    private final String PROFILE = API + "profile";

    private NetworkHelper(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // Pegar o contexto da aplicação garante que a requestQueue vai ser singleton e só
            // morre quando a aplicação parar
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    //Retorna uma instância estática de NetworkHelper
    public static synchronized NetworkHelper getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkHelper(context);
        }
        return instance;
    }

    public void doLogin(String email, String password, ResponseCallback callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        execute(Request.Method.POST, params, TAG, DOMINIO + LOGIN, callback);
    }

    public void getLaboratories(String uf, String city, ResponseCallback callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("uf", uf);
        params.put("city", city);
        execute(Request.Method.GET, null, TAG, buildGetURL(DOMINIO + GET_LABORATORIES, params), callback);
    }

    public void getCities(String uf, ResponseCallback callback) {
        String url = DOMINIO + GET_CITIES + "/" + uf + "/cities";
        execute(Request.Method.GET, null, TAG, url, callback);
    }

    public void getResult(String labKey, String resultCode, ResponseCallback callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("key", labKey);
        params.put("code", resultCode);
        execute(Request.Method.POST, params, TAG, DOMINIO + RESULT, callback);
    }

    public void removeResultAccess(String resultId, ResponseCallback callback) {
        execute(Request.Method.DELETE, null, TAG, DOMINIO + RESULT + "/"+resultId, callback);
    }

    public void getAllSavedResults(ResponseCallback callback) {
        execute(Request.Method.GET, null, TAG, DOMINIO + RESULT, callback);
    }

    public void signUp(HashMap<String, String> params, ResponseCallback callback) {
        execute(Request.Method.POST, params, TAG, DOMINIO + SIGNUP, callback);
    }

    public void updateProfile(HashMap<String, String> params, ResponseCallback callback) {
        execute(Request.Method.PUT, params, TAG, DOMINIO + PROFILE, callback);
    }

    public void getProfile(ResponseCallback callback) {
        execute(Request.Method.GET, null, TAG, DOMINIO + PROFILE, callback);
    }

    private void execute(int method, final HashMap params, String tag, String url, final ResponseCallback callback) {
        request = new CustomRequest(
                method,
                url,
                params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.i("onResponse - LOG", "response: " + response);
                        if (callback != null) {
                            callback.onSuccess(response, request.getStatusCode());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.i("onResponse - LOG", "response: " + error.getMessage());
                        if (callback != null) {
                            callback.onFail(error);
                        }
                    }
                });

        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(tag);
        getRequestQueue().add(request);

    }

    private String buildGetURL(String url, HashMap<String, String> params) {
        url += "?";
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            url += pair.getKey() + "=" + pair.getValue();
            it.remove(); // avoids a ConcurrentModificationException
            if (it.hasNext()) {
                url += "&";
            }
        }
        return url;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
