package com.andyapp.prakashproducts.VolleyParser;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.andyapp.prakashproducts.AppController;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class VolleyRequest extends Request<String> {
    private final Map<String, String> headers;
    private final Response.Listener<String> listener;
    private final String body;
    private int statusCode = 0;
    private Response.ErrorListener errorListener;

    private VolleyRequest(int method, String url, Map<String, String> headers, String body, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.body = body;
        this.headers = headers;
        this.listener = listener;
        this.errorListener = errorListener;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(String response) {
        if (listener != null) {
            ((VolleyResponse) listener).setStatusCode(statusCode);
            listener.onResponse(response);
        }
    }


    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return body == null ? null : body.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, "utf-8");
            return null;
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            statusCode = response.statusCode;
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        if (errorListener != null) {
            ((VolleyErr) errorListener).setStatusCode(statusCode);
        }

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }


    private static class VolleyResponse implements Response.Listener<String> {
        ResponseListener responseListener;
        Object holdBackObject;
        int statusCode;

        private VolleyResponse(ResponseListener responseListener, Object holdBackObject) {
            this.responseListener = responseListener;
            this.holdBackObject = holdBackObject;
        }

        private void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        @Override
        public void onResponse(String response) {
            if (responseListener != null) {
                responseListener.onProgressEnd();
                if (statusCode == 200) {
                    responseListener.onSuccess(response, holdBackObject);
                } else {
                    try {
                        Log.e("response", response);
                        JSONArray errorArray = new JSONArray(response);
                        String message = errorArray.getString(0);
                        responseListener.onFailed(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        responseListener.onFailed(statusCode);
                    }
                }
            }


        }
    }

    private static class VolleyErr implements Response.ErrorListener {
        ResponseListener responseListener;
        int statusCode;

        private VolleyErr(ResponseListener responseListener) {
            this.responseListener = responseListener;
        }

        private void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            if (responseListener != null) {
                responseListener.onProgressEnd();
                if (statusCode == 401 || error instanceof AuthFailureError) {
                    responseListener.onFailed("Wrong user name or password");
                } else if (statusCode == 500) {
                    responseListener.onFailed("Due to overload, server failed to process your request");
                } else {
                    String message = error.getLocalizedMessage();
                    if (TextUtils.isEmpty(message))
                        responseListener.onFailed();
                    else
                        responseListener.onFailed(message);
                }
            }

        }
    }


    public static class Builder {
        int method;
        String url;
        Map<String, String> headers;
        String body;
        String tag;
        VolleyUtils.RequestTimeout requestTimeout;
        ResponseListener responseListener;
        Object holdBackObject;

        public static Builder Init() {
            return new Builder();
        }

        public Builder setMethod(int method) {
            this.method = method;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setHeader(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setJsonBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setTime(VolleyUtils.RequestTimeout requestTimeout) {
            this.requestTimeout = requestTimeout;
            return this;
        }

        public Builder setResponseListener(ResponseListener responseListener) {
            this.responseListener = responseListener;
            return this;
        }

        public Builder setHoldbackObject(Object holdbackObject) {
            this.holdBackObject = holdbackObject;
            return this;
        }

        public void build() {
            if (responseListener != null)
                responseListener.onProgressStart();

            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append("\nBuild api");
            stringBuffer.append("\nMethod > ").append(method);
            stringBuffer.append("\nurl > ").append(url);
            stringBuffer.append("\nheaders > ").append(headers.toString());
            stringBuffer.append("\nbody > ").append(body);


            Log.e("VolleyReq", stringBuffer.toString());

            VolleyRequest volleyRequest = new VolleyRequest(method, url, headers, body, new VolleyResponse(responseListener, this.holdBackObject), new VolleyErr(responseListener));
            volleyRequest.setShouldCache(false);
            volleyRequest.setRetryPolicy(VolleyUtils.getRetryPolicy(requestTimeout));
            AppController.getInstance().addToRequestQueue(volleyRequest, tag);
        }

    }

}
