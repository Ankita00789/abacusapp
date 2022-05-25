package com.example.abacusup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.abacusup.Custom.DataProcessor;
import com.example.abacusup.Custom.FacultyDetailModel;
import com.example.abacusup.Custom.GenResult;
import com.example.abacusup.Custom.GetResponseStudent;
import com.example.abacusup.Custom.GetResponseUniv;
import com.example.abacusup.Custom.SampleApplication;
import com.example.abacusup.Custom.UserData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UniversityReport extends AppCompatActivity {
    private static Context context;
    ProgressDialog mProgressBar;
    TableLayout tbtdetails;
    public static ArrayList<FacultyDetailModel> receiveQuantities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_report);
        getSupportActionBar().hide();
        setContext(this);
        mProgressBar = new ProgressDialog(this);
        mProgressBar = new ProgressDialog(UniversityReport.this);
        tbtdetails = (TableLayout)findViewById(R.id.table_RODetail);
        SubmitChildInfo();
    }
    private void SubmitChildInfo() {
        try {

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("method", "GET");
            ConnectivityManager connMgr = (ConnectivityManager)
                    UniversityReport.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.isConnected()) {
                String res = new GetResponseUniv().execute(hashMap).get();
                String jsonArray = DataProcessor.getData2(res, 0, this);
                try {
                    JSONObject jsonResponse = new JSONObject(jsonArray);
                    JSONArray jsonMainNode = jsonResponse.optJSONArray("UnivDetails");

                    UserData userdata = new UserData();
                    GenResult insertResult = new GenResult();
                    receiveQuantities = new ArrayList<FacultyDetailModel>();
//                    mDBHelper = new DatabaseHelper(this);
//                    DbAccessLayer myDB = new DbAccessLayer(mDBHelper);
                    for(int i = 0; i<jsonMainNode.length();i++) {
                        FacultyDetailModel Registrationmodel = new FacultyDetailModel();
                        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                        String id = jsonChildNode.optString("id");
                        String district_name = jsonChildNode.optString("district_name");
                        String university_name = jsonChildNode.optString("university_name");


                        Registrationmodel.setId(id);
                        Registrationmodel.setDistName(district_name);
                        Registrationmodel.setUnivName(university_name);
                        receiveQuantities.add(Registrationmodel);
                        //myDB.getSchoolInfo(Registrationmodel);

                    }

                    TableRow row = new TableRow(this);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    TableRow.LayoutParams tr_lp1 =
                            new TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.WRAP_CONTENT);
                    TableRow.LayoutParams tr_lp2 =
                            new TableRow.LayoutParams(
                                    TableRow.LayoutParams.MATCH_PARENT,
                                    TableRow.LayoutParams.WRAP_CONTENT);
                    TableRow.LayoutParams tr_lp3 =
                            new TableRow.LayoutParams(
                                    TableRow.LayoutParams.WRAP_CONTENT,
                                    TableRow.LayoutParams.MATCH_PARENT);


                    TextView serial = new TextView(this);
                    TextView commodity = new TextView(this);
                    TextView scheme = new TextView(this);

                    TextView dob = new TextView(this);
                    TextView textSpacer = null;
                    serial.setText("Sr.No.");
                    //serial.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    serial.setLayoutParams(tr_lp1);
                    serial.setGravity(Gravity.LEFT);
                    serial.setPadding(5, 15, 0, 15);
                    serial.setBackgroundColor(Color.parseColor("#666699"));
                    serial.setTextColor(Color.parseColor("#ffffff"));

                    commodity.setText("District Name");
                    //commodity.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    commodity.setLayoutParams(tr_lp2);
                    commodity.setGravity(Gravity.LEFT);
                    commodity.setPadding(5, 15, 0, 15);
                    commodity.setBackgroundColor(Color.parseColor("#666699"));
                    commodity.setTextColor(Color.parseColor("#ffffff"));

                    scheme.setText("University Name");
                    //scheme.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    scheme.setLayoutParams(tr_lp3);
                    scheme.setGravity(Gravity.LEFT);
                    scheme.setPadding(5, 15, 0, 15);
                    scheme.setBackgroundColor(Color.parseColor("#666699"));
                    scheme.setTextColor(Color.parseColor("#ffffff"));




                    row.addView(serial);
                    row.addView(commodity);
                    row.addView(scheme);

                    int b;
                    String sr, comm, sc, bgColor, textColor = "#131313", borderColor = "#111111";
                    if (tbtdetails != null) {
                        tbtdetails.removeAllViews();
                        tbtdetails.setStretchAllColumns(true);
                        tbtdetails.addView(row, 0);
                        tbtdetails.setBackgroundColor(Color.parseColor(borderColor));
                        if (receiveQuantities.size() > 0) {
                            for (int i = 0; i < receiveQuantities.size(); i++) {
                                if ((i % 2) != 0) {
                                    bgColor = "#e7e7e7";
                                } else {
                                    bgColor = "#fdfdfd";
                                }
                                if ((receiveQuantities.size() - i) == 1) {
                                    b = 2;
                                } else {
                                    b = 1;
                                }
                                row = new TableRow(this);
                                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                                        TableLayout.LayoutParams.WRAP_CONTENT));
                                tr_lp1.setMargins(1, 0, 0, b);
                                tr_lp2.setMargins(1, 0, 0, b);
                                tr_lp3.setMargins(1, 0, 0, b);


                                serial = new TextView(this);
                                serial.setLayoutParams(tr_lp1);
                                serial.setGravity(Gravity.LEFT);
                                serial.setPadding(5, 15, 0, 15);
                                //serial.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                serial.setBackgroundColor(Color.parseColor(bgColor));
                                serial.setTextColor(Color.parseColor(textColor));

                                commodity = new TextView(this);
                                commodity.setLayoutParams(tr_lp2);
                                commodity.setGravity(Gravity.LEFT);
                                commodity.setPadding(15, 15, 0, 15);
                                commodity.setBackgroundColor(Color.parseColor(bgColor));
                                commodity.setTextColor(Color.parseColor(textColor));

                                scheme = new TextView(this);
                                scheme.setLayoutParams(tr_lp3);
                                scheme.setGravity(Gravity.LEFT);
                                scheme.setPadding(15, 15, 0, 15);
                                scheme.setBackgroundColor(Color.parseColor(bgColor));
                                scheme.setTextColor(Color.parseColor(textColor));



                                sr = ((Object) (i + 1)).toString();
                                comm = receiveQuantities.get(i).getDistName();
                                sc = receiveQuantities.get(i).getUnivName();

                               /* rcv = ((Object) receiveQuantities.get(i).getReceived_Qty()).toString();
                                rcv = String.format("%.5f", Float.parseFloat(rcv));*/
                                serial.setText(sr);
                                commodity.setText(comm);
                                scheme.setText(sc);

                                row.addView(serial);
                                row.addView(commodity);
                                row.addView(scheme);


                                tbtdetails.addView(row, i + 1);
                            }
                        }
                    }

                }
                catch (Exception ex) {
                    mProgressBar.dismiss();
                    SampleApplication.displayToast("Some error occur please try again.", this);
                    ex.printStackTrace();
                }
            } else {
                mProgressBar.dismiss();
                SampleApplication.showMessage("Failure", "Please check your network connection and try again.", this);
            }

        } catch (Exception ex) {
            mProgressBar.dismiss();
            SampleApplication.displayToast("Some error occur please try again.", this);
            ex.printStackTrace();
        }
    }

    private void setContext(Context c) {
        context = c;
    }

    public static Context getMainContext() {
        return context;
    }
}