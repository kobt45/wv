package com.parkwaydrive.vod4u;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;



public class MainApp extends AppCompatActivity {

    String t2 = "";
    int x = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        while (x != 0 && x != 1) {
//            try {
//                t2 = new fetchData2().execute().get();
//                x = t2.charAt(0) - '0';
//                //char c = t2.charAt(0);
//
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        if (x == 0) {
//            try {
//                Intent k = new Intent(MainApp.this, MainActivity.class);
//                startActivity(k);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                Intent k = new Intent(MainApp.this, MainWV.class);
//                startActivity(k);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override public void onStart() {
        super.onStart();
        try {
            Branch.sessionBuilder(this).withCallback(new Branch.BranchReferralInitListener() {
                @Override
                public void onInitFinished(JSONObject referringParams, BranchError error) {
                    if (error == null) {

//                        // option 1: log data
//                        Log.i("BRANCH SDK", referringParams.toString());
//
//                        // option 2: save data to be used later
//                        SharedPreferences preferences = MainApp.this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
//                        preferences.edit().putString("branchData", referringParams.toString()).apply();

                        // option 3: navigate to page
                        Intent intent = new Intent(MainApp.this, Main2Activity.class);
                        startActivity(intent);

//                        // option 4: display data
//                        Toast.makeText(MainApp.this, referringParams.toString(), Toast.LENGTH_LONG).show();

                    } else {
                        Log.i("BRANCH SDK", error.getMessage());
                    }
                }
            }).withData(this.getIntent().getData()).init();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

//        while (x != 0 && x != 1) {
//            try {
//                t2 = new fetchData2().execute().get();
//                x = t2.charAt(0) - '0';
//                //char c = t2.charAt(0);
//
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        if (x == 0) {
//            try {
//                Intent k = new Intent(MainApp.this, MainActivity.class);
//                startActivity(k);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                Intent k = new Intent(MainApp.this, MainWV.class);
//                startActivity(k);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            setIntent(intent);
            // if activity is in foreground (or in backstack but partially visible) launching the same
            // activity will skip onStart, handle this case with reInitSession
            Branch.sessionBuilder(this).withCallback(branchReferralInitListener).reInit();
        }
        catch (Exception ignored) { }

    }

    private Branch.BranchReferralInitListener branchReferralInitListener = new Branch.BranchReferralInitListener() {
        @Override
        public void onInitFinished(JSONObject linkProperties, BranchError error) {
            // do stuff with deep link data (nav to page, display content, etc)
        }
    };

}