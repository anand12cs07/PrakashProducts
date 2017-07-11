package com.andyapp.prakashproducts.VolleyParser;


public interface ServiceResponse {

    void onFail();

    void onFail(int statusCode, String message);

    void onSuccess(int statusCode, String message);
}




