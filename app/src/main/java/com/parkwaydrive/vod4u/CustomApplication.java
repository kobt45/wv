package com.parkwaydrive.vod4u;

import android.app.Application;

import io.branch.referral.Branch;
import io.branch.referral.BranchApp;

public class CustomApplication extends BranchApp {

    @Override
    public void onCreate() {
        super.onCreate();
        // Branch logging for debugging
        Branch.enableLogging();
        // Branch object initialization
        Branch.getAutoInstance(this);
    }
}