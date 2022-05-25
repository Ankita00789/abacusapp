package com.example.abacusup.Custom.util;

import android.app.Activity;

import com.example.abacusup.Custom.GenResult;
import com.example.abacusup.Custom.KeyValuePair;
import com.example.abacusup.Custom.RegisterData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonProcessor {

    // Methods to Serialize Objects
    public static String parsePostDataToJson(RegisterData postData) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", postData.name);
            jsonObject.put("mobile_no", postData.mobile_no);
            jsonObject.put("email_id", postData.email_id);
            jsonObject.put("university_name", postData.university_name);
            jsonObject.put("college_name", postData.college_name);
            jsonObject.put("father_name", postData.father_name);
            jsonObject.put("dob", postData.dob);
            jsonObject.put("district_name", postData.district_name);
            jsonObject.put("total", postData.total);
            return jsonObject.toString();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String parseStringArrayToJson(String[] strings) {
        try {
            JSONArray jsonArray = new JSONArray();
            if (strings != null) {
                for (String s : strings) {
                    jsonArray.put(s);
                }
                return jsonArray.toString();
            } else
                return null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String parseArrayListStringToJson(ArrayList<String> stringArrayList) {
        try {
            JSONArray jsonArray = new JSONArray();
            if (stringArrayList != null) {
                for (String s : stringArrayList) {
                    jsonArray.put(s);
                }
                return jsonArray.toString();
            } else
                return null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String parseGenResultToJson(GenResult genResult) {
        try {
            JSONObject jsonObject = new JSONObject();
            if (genResult != null) {
                jsonObject.put("Status", genResult.getStatus());
                jsonObject.put("Message", genResult.getMessage());
                return jsonObject.toString();
            }
            return null;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//    public static String

    //Methods to Deserialize JSON String
    public static GenResult parseGenResultFromJson(String json, Activity activity) {
        GenResult genResult = new GenResult();
        try {
            JSONObject jsonObject = new JSONObject(json);
            genResult.setStatus(jsonObject.getString("Status"));
            genResult.setMessage(jsonObject.getString("Message"));
            return genResult;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String[] parseStringArrayFromJson(String json, Activity activity) {
        String[] strings;
        try {
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            strings = new String[length];
            for (int i = 0; i < length; i++) {
                strings[i] = jsonArray.getString(i);
            }
            return strings;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static KeyValuePair[] parseKVPairFromJson(String json) {
        KeyValuePair[] keyValuePairs;
        try {
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            keyValuePairs = new KeyValuePair[length];
            JSONObject jsonObject;
            for (int i = 0; i < length; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                keyValuePairs[i] = new KeyValuePair();
                keyValuePairs[i].Key = jsonObject.getString("Key");
                keyValuePairs[i].Value = jsonObject.getString("Value");
            }
            return keyValuePairs;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> parseArrayListFromJson(String json) {
        ArrayList<String> arrayList;
        try {
            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();
            arrayList = new ArrayList<>();
            String s;
            for (int i = 0; i < length; i++) {
                s = jsonArray.getString(i);
                arrayList.add(s);
            }
            return arrayList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
