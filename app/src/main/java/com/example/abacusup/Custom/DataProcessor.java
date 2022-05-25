package com.example.abacusup.Custom;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataProcessor {

    public static ArrayList<String> processReceivedDataResponse(String s,Activity activity) {
        String item = "";
        ArrayList<String> items = new ArrayList<>();
        try {
            if (s != null) {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray itemsArray = jsonObject.getJSONArray("Result");
                //jsonObject
                int i = 0;
                //RijndaelSecurity rijndaelSecurity = new RijndaelSecurity();
                while (i < itemsArray.length()) {
                    // Get the current item information.
                    try {
                        item = itemsArray.get(i).toString();
                        if (item != null) {
                            //item = rijndaelSecurity.Decrypt(item, "admiguru.key",activity);
                            items.add(item);
                        } else {
                            item = "No Data.";
                            items.add(item);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        Log.d("GetResult", e.getMessage());
                        return null;
                    }

                    // Move to the next item.
                    i++;
                }
            }
        } catch (Exception e) {
            //mResultText.get().setText(e.getMessage() + "\n Result String: " + s);
            e.printStackTrace();
            Log.d("GetResult", e.getMessage());
        }

        return items;
    }

    public static String getData2(String s, int dataAtIndex, Activity activity) {
        String item = "";
        try {
            if (s != null) {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray itemsArray = jsonObject.getJSONArray("Result");
                int i = 0;
                //RijndaelSecurity rijndaelSecurity = new RijndaelSecurity();
                while (dataAtIndex < itemsArray.length()) {
                    // Get the current item information.
                    try {
                        item = itemsArray.get(dataAtIndex).toString();
                        if (item != null) {
                            //item = rijndaelSecurity.Decrypt(item, "admiguru.key", activity);
                            item = item.split("\\|")[0];
                            break;
                        } else {
                            item = "No Data.";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                        Log.d("GetResult", e.getMessage());
                        return "Error.";
                    }

                    // Move to the next item.
                    i++;
                }
            }
        } catch (Exception e) {
            //mResultText.get().setText(e.getMessage() + "\n Result String: " + s);
            e.printStackTrace();
            Log.d("GetResult", e.getMessage());
        }

        return item;
    }

    /*public static RegistrationDetailsModel processReceiveData(String s) {
        RegistrationDetailsModel rq = new RegistrationDetailsModel();

        String id = jsonChildNode.optString("id");
        String personname = jsonChildNode.optString("name");
        String personmobile = jsonChildNode.optString("mobile");
        String personemaile = jsonChildNode.optString("email");
        String personaddress = jsonChildNode.optString("homeaddress");

        Registrationmodel.setId(id);
        Registrationmodel.setName(personname);
        Registrationmodel.setMobile(personmobile);
        Registrationmodel.setEmail(personemaile);
        Registrationmodel.setAddress(personaddress);

        String[] field = s.split("\\|");
        if (field.length > 4) {
            rq.setRO_No(field[0].split("=")[1]);
            rq.setReceive_ID(field[1].split("=")[1]);
            rq.setRO_Received_date(field[2].split("=")[1]);
            rq.setCommodity_name_Hindi(field[3].split("=")[1]);
            rq.setSchemeNameHindi(field[4].split("=")[1]);
            rq.setNet_Qty(Float.parseFloat(field[5].split("=")[1]));
            rq.setReceived_Qty(Float.parseFloat(field[6].split("=")[1]));
            rq.setRemaining_Qty(Float.parseFloat(field[7].split("=")[1]));
        } else
            return null;
        return rq;
    }*/
}
