package com.example.abacusup.Custom;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class GetResponseStudent extends AsyncTask<HashMap, Void, String> {
    private static final String BASE_URL = SampleApplication.BASE_URL;

    private WeakReference<ProgressDialog> mProgressDialog;

    public GetResponseStudent() {
        this.mProgressDialog = null;
    }

    @Override
    protected String doInBackground(HashMap... hashMaps) {
        String Result = "";
        NetworkUtils httpUtil = new NetworkUtils();
        httpUtil.setEncoding(Xml.Encoding.UTF_8);
        httpUtil.setContentType(NetworkUtils.ContentType.CONTENT_JSON);
        if (hashMaps[0].get("method").equals("POST")) {
            hashMaps[0].remove("method");
            RegisterData data = new RegisterData();
            data.name = hashMaps[0].get("name").toString();
            data.father_name = hashMaps[0].get("father_name").toString();
            data.email_id = hashMaps[0].get("email").toString();
            data.dob = hashMaps[0].get("dob").toString();
            if (BASE_URL.split(":")[0].equals("http"))
                Result = httpUtil.Post(BASE_URL, data);
            else
                Result = httpUtil.HttpsPost(BASE_URL, data);
        } else if (hashMaps[0].get("method").equals("GET")) {
            hashMaps[0].remove("method");
            String args = "";
            if (hashMaps[0].size() != 2) {
                if (hashMaps[0].containsKey("name"))
                    args += "/" + hashMaps[0].get("name").toString();
                if (hashMaps[0].containsKey("father_name"))
                    args += "/" + hashMaps[0].get("father_name").toString();
                if (hashMaps[0].containsKey("email"))
                    args += "/" + hashMaps[0].get("email").toString();
                if (hashMaps[0].containsKey("dob"))
                    args += "/" + hashMaps[0].get("dob").toString();

            } else {
               /* args = "/" + hashMaps[0].get("id").toString()
                        + "?serviceKey="
                        + hashMaps[0].get("serviceKey").toString();*/
            }
            if (BASE_URL.split(":")[0].equals("http")) {
                Result = httpUtil.Get(BASE_URL + args);

            }
            else
                Result = httpUtil.HttpsGet(BASE_URL + args);
        }
        Result = "{\"Result\":" + Result + "}";
        return Result;
    }

    @Override
    protected void onPostExecute(String s) {
        if (mProgressDialog != null) {
            mProgressDialog.get().hide();
        }
    }
}
