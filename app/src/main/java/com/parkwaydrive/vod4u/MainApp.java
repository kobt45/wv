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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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