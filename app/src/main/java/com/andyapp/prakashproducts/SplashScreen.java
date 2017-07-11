package com.andyapp.prakashproducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.andyapp.prakashproducts.Utils.ApiBuilder;
import com.andyapp.prakashproducts.Utils.FontUtils;
import com.andyapp.prakashproducts.VolleyParser.ResponseListener;
import com.andyapp.prakashproducts.VolleyParser.VolleyRequest;
import com.andyapp.prakashproducts.VolleyParser.VolleyUtils;
import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

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
        VolleyRequest.Builder.Init().setMethod(Request.Method.GET).
                setUrl(ApiBuilder.getBuilder().JSON_URL).
                setHeader(VolleyUtils.getEmptyHeaders()).
                setJsonBody(null).
                setTag("item_request").
                setTime(VolleyUtils.RequestTimeout.VERY_HIGH).
                setResponseListener(new ResponseListener() {
                    @Override
                    public void onProgressStart() {
                        Log.e("onSuccess()", "On progress Start");
                    }

                    @Override
                    public void onProgressEnd() {
                        Log.e("onSuccess()", "On progress End");
                    }

                    @Override
                    public void onFailed() {
                        Log.e("onSuccess()", "On progress Fail");
                    }

                    @Override
                    public void onFailed(int statusCode) {
                        Log.e("onSuccess()", String.valueOf(statusCode));
                    }

                    @Override
                    public void onFailed(String message) {
                        Log.e("onSuccess()", message);
                    }

                    @Override
                    public void onSuccess(String response, Object holdBack) {
                        Log.e("onSuccess()", "response- " + response);
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                        ApiBuilder.getBuilder().setResponse(response);
                        SplashScreen.this.finish();
                    }
                }).build();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppController.getInstance().cancelPendingRequests("item_request");
    }
}
