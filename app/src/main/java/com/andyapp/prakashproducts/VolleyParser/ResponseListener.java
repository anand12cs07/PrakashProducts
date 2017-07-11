package com.andyapp.prakashproducts.VolleyParser;


public interface ResponseListener {

    void onProgressStart();

    void onProgressEnd();

    void onFailed();

    void onFailed(int statusCode);

    void onFailed(String message);

    void onSuccess(String message, Object holdBackObject);
}
