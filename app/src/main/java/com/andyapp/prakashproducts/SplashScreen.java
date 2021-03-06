package com.andyapp.prakashproducts;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.andyapp.prakashproducts.Utils.ApiBuilder;
import com.andyapp.prakashproducts.Utils.ConnectivityReceiver;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashScreen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private TextView title;
    private ProgressBar progressBar;
    private ConnectivityReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        title = (TextView) findViewById(R.id.splash_title);
        progressBar = (ProgressBar) findViewById(R.id.splash_progress);
        progressBar.setVisibility(View.GONE);
        initVolley();
    }

    private void initVolley() {
        progressBar.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(ApiBuilder.getBuilder().JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("OnSuccess >", response);
                ApiBuilder.getBuilder().setResponse(response);
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(SplashScreen.this, HomeActivity.class));

                SplashScreen.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                if (!ConnectivityReceiver.isConnected())
                    showSnack();
            }
        });
        AppController.getInstance().addToRequestQueue(request, "item_request");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void showSnack() {
        Snackbar snackbar = Snackbar.make(title, "Check your Internet Connection !!!", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        if (receiver == null)
            receiver = new ConnectivityReceiver();
        filter.addAction("com.andyapp.prakashproducts.Utils.ConnectivityReceiver");
        registerReceiver(receiver, filter);
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppController.getInstance().cancelPendingRequests("item_request");
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        AppController.getInstance().cancelPendingRequests("item_request");
        if (isConnected)
            initVolley();
    }
}
