package com.parkwaydrive.vod4u;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.Branch.BranchReferralInitListener;
import io.branch.referral.BranchError;
import io.branch.referral.util.LinkProperties;

public class MainApp extends AppCompatActivity {

    String t2 = "";
    int x = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ProgressDialog dialog = ProgressDialog.show(MainApp.this, "", "Loading...", true);

        Branch.getInstance().setIdentity("844169986787267145");
        Branch.getInstance().enableFacebookAppLinkCheck();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Branch.getInstance().initSession(new Branch.BranchUniversalReferralInitListener() {
            @Override
            public void onInitFinished(BranchUniversalObject branchUniversalObject, LinkProperties linkProperties, BranchError error) {
                if (error != null) {
                    Log.i("BranchTestBed", "branch init failed. Caused by -" + error.getMessage());
                } else {
                    Log.i("BranchTestBed", "branch init complete!");
                    if (branchUniversalObject != null) {
                        Log.i("BranchTestBed", "title " + branchUniversalObject.getTitle());
                        Log.i("BranchTestBed", "CanonicalIdentifier " + branchUniversalObject.getCanonicalIdentifier());
                        Log.i("ContentMetaData", "metadata " + branchUniversalObject.getContentMetadata().convertToJson());

                    }
                    if (linkProperties != null) {
                        Log.i("BranchTestBed", "Channel " + linkProperties.getChannel());
                        Log.i("BranchTestBed", "control params " + linkProperties.getControlParams());
                    }
                }


                // QA purpose only
                // TrackingControlTestRoutines.runTrackingControlTest(MainActivity.this);
                // BUOTestRoutines.TestBUOFunctionalities(MainActivity.this);

            }
        }, this.getIntent().getData(), this);

        // Branch integration validation: Validate Branch integration with your app
        // NOTE : The below method will run few checks for verifying correctness of the Branch integration.
        // Please look for "BranchSDK_Doctor" in the logcat to see the results.
        // IMP : Do not make this call in your production app

        //IntegrationValidator.validate(MainApp.this);

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        Branch.getInstance().reInitSession(this, new BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error != null) {
                    Log.i("BranchTestBed", error.getMessage());
                } else if (referringParams != null) {
                    Log.i("BranchTestBed", referringParams.toString());
                }
            }
        });
    }


    private Branch.BranchReferralInitListener branchReferralInitListener = new Branch.BranchReferralInitListener() {
        @Override
        public void onInitFinished(JSONObject linkProperties, BranchError error) {
            // do stuff with deep link data (nav to page, display content, etc)
            if (error == null) {
                Log.d("Response: ", linkProperties.toString());
            }
        }
    };



    public void onDestroy() {
        super.onDestroy();
        // logout
        Branch.getInstance().logout();
    }


}