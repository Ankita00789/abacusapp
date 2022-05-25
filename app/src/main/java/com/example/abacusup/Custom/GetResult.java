package com.example.abacusup.Custom;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

public class GetResult extends AsyncTask<HashMap, Void, String> {
    private static final String BASE_URL = "";
    private WeakReference<ArrayAdapter<String>> mArrayAdapter;
    private WeakReference<HashMap<String, String>> table;
    private WeakReference<ProgressDialog> mProgressBar;
    private ArrayList<String> district;
    private WeakReference<AppCompatSpinner> spinner;
    private String header;

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
            data.mobile_no = hashMaps[0].get("mobile_no").toString();
            data.email_id = hashMaps[0].get("email_id").toString();
            data.university_name = hashMaps[0].get("university_name").toString();
            data.college_name = hashMaps[0].get("college_name").toString();
            if (BASE_URL.split(":")[0].equals("http"))
                Result = httpUtil.Post(BASE_URL, data);
            else
                Result = httpUtil.HttpsPost(BASE_URL, data);
        } else if (hashMaps[0].get("method").equals("GET")) {
            hashMaps[0].remove("method");
            String args = "";
            if (hashMaps[0].size() != 2) {
                /*if (hashMaps[0].containsKey("id"))
                    args += "/" + hashMaps[0].get("id").toString();
                if (hashMaps[0].containsKey("serviceKey"))
                    args += "/" + hashMaps[0].get("serviceKey").toString();
                if (hashMaps[0].containsKey("value"))
                    args += "/" + hashMaps[0].get("value").toString();*/
            } else {
                /*args = "/" + hashMaps[0].get("id").toString()
                        + "?serviceKey="
                        + hashMaps[0].get("serviceKey").toString();*/
            }
            if (BASE_URL.split(":")[0].equals("http"))
                Result = httpUtil.Get(BASE_URL + args);
            else
                Result = httpUtil.HttpsGet(BASE_URL + args);
        }
        Result = "{\"Result\":" + Result + "}";
        return Result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //mResultText.get().setText(s);
        try {
            if (s != null) {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray itemsArray = jsonObject.getJSONArray("Result");
                int i = 0;
                String item;
                district = new ArrayList<>();
                //RijndaelSecurity rijndaelSecurity = new RijndaelSecurity();
                while (i < itemsArray.length()) {
                    // Get the current item information.
                    try {
                        item = itemsArray.get(i).toString();
                        if (item != null) {
                            if(item.contains("Failure")){
                                break;
                            }
                            //item = rijndaelSecurity.Decrypt(item, "UP_GOVT.key");
                            String key = item.split("\\|")[0].split("=")[1];
                            item = item.split("\\|")[1].split("=")[1];
                            if (!item.equals("Invalid data.")) {
                                table.get().put(item, key);
                                district.add(item);
                            }
                        } else {
                            // mResultText.get().setText("No Results");
                        }
                    } catch (Exception e) {
                        //mResultText.get().setText(e.getMessage());
                        e.printStackTrace();
                        Log.d("GetResult", e.getMessage());
                    }

                    // Move to the next item.
                    i++;
                }
                if (district.contains("No data found.")) {
                    district.remove("No data found.");
                    table.get().remove("No data found.");
                }
                mArrayAdapter.get().addAll(district);
//                if (spinner != null && header != null) {
//                    if (header.equals(MainActivity.getMainContext().getString(R.string.selectAgency))) {
//                        mArrayAdapter.get().insert(header, 0);
//                        spinner.get().setAdapter(mArrayAdapter.get());
//                        if (spinner.get().getAdapter().getCount() > 1) {
//                            spinner.get().setSelection(1);
//                            spinner.get().setEnabled(false);
//                        }
//                    } else if (header.equals(MainActivity.getMainContext().getString(R.string.select_vnumber))) {
//                        mArrayAdapter.get().insert(header, 0);
//                        int length = mArrayAdapter.get().getCount();
//                        if (length > 1) {
//                            mArrayAdapter.get().insert(MainActivity.getMainContext().getString(R.string.select_vnumber_other), length);
//                        }
//                        spinner.get().setAdapter(mArrayAdapter.get());
//                    }
//                }
            }
        } catch (Exception e) {
            //mResultText.get().setText(e.getMessage() + "\n Result String: " + s);
            e.printStackTrace();
            Log.d("GetResult", e.getMessage());
        }
        if (mProgressBar != null) {
            mProgressBar.get().hide();
        }
    }
}
