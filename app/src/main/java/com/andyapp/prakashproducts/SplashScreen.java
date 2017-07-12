package com.andyapp.prakashproducts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.andyapp.prakashproducts.Utils.ApiBuilder;
import com.andyapp.prakashproducts.Utils.FontUtils;
import com.andyapp.prakashproducts.VolleyParser.ResponseListener;
import com.andyapp.prakashproducts.VolleyParser.VolleyRequest;
import com.andyapp.prakashproducts.VolleyParser.VolleyUtils;
import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        TextView title = (TextView)findViewById(R.id.splash_title);
        ImageView root = (ImageView)findViewById(R.id.splash_root);
        Glide.with(this).load(R.drawable.splashscreenbg).into(root);
        title.setTypeface(FontUtils.getInstance().getTypeFace(FontUtils.NOVECENTOSANWIDE,this));

        initVolley();
    }

    private void initVolley() {
        StringRequest request = new StringRequest(ApiBuilder.getBuilder().JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("OnSuccess >", response);
                ApiBuilder.getBuilder().setResponse(response);
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                SplashScreen.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (!ConnectivityReceiver.isConnected())
                    showSnack();
            }
        });
        AppController.getInstance().addToRequestQueue(request,"item_request");
    }

    private void showSnack() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.splash_title),"Check your Internet Connection!!!",Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppController.getInstance().cancelPendingRequests("item_request");
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected)
            initVolley();
    }
}
