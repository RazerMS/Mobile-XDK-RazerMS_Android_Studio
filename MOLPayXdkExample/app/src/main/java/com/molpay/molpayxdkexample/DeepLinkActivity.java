package com.molpay.molpayxdkexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class DeepLinkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isTaskRoot()) {
            // This Activity is the only Activity, so
            //  the app wasn't running. So start the app from the
            //  beginning (redirect to MainActivity)
            Intent intent = getIntent(); // Copy the Intent used to launch me
            // Launch the real root Activity (launch Intent)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(this, MainActivity.class);
            // I'm done now, so finish()

            startActivity(intent);
            finish();
        } else {
            // App was already running, so just finish, which will drop the user
            //  in to the activity that was at the top of the task stack
            finish();
        }

    }
}
