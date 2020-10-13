package com.parkwaydrive.vod4u;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.branch.referral.Branch;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override public void onStart() {
        super.onStart();
        try{
            // Branch logging for debugging
            Branch.enableLogging();

            // Branch object initialization
            Branch.getAutoInstance(this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
