package com.molpay.molpayxdkexample;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.molpay.molpayxdk.MOLPayActivity;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private void restartmolpay() {
        HashMap<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put(MOLPayActivity.mp_amount, "1.10");

        paymentDetails.put(MOLPayActivity.mp_username, "RMSxdk_2022");
        paymentDetails.put(MOLPayActivity.mp_password, "RMSpwd@2022");
        paymentDetails.put(MOLPayActivity.mp_merchant_ID, "rmsxdk_mobile_Dev");
        paymentDetails.put(MOLPayActivity.mp_app_name, "mobile");
        paymentDetails.put(MOLPayActivity.mp_verification_key, "ee738b541eff7b6b495e44771f71c0ec");

        paymentDetails.put(MOLPayActivity.mp_order_ID, Calendar.getInstance().getTimeInMillis());
        paymentDetails.put(MOLPayActivity.mp_currency, "MYR");
        paymentDetails.put(MOLPayActivity.mp_country, "MY");
        paymentDetails.put(MOLPayActivity.mp_channel, "multi");
        paymentDetails.put(MOLPayActivity.mp_bill_description, "bill description");
        paymentDetails.put(MOLPayActivity.mp_bill_name, "bill name");
        paymentDetails.put(MOLPayActivity.mp_bill_email, "example@gmail.com");
        paymentDetails.put(MOLPayActivity.mp_bill_mobile, "01234567888");
        paymentDetails.put(MOLPayActivity.mp_channel_editing, false);
        paymentDetails.put(MOLPayActivity.mp_editing_enabled, true);
        paymentDetails.put(MOLPayActivity.mp_express_mode, false);
        paymentDetails.put(MOLPayActivity.mp_dev_mode, false);
        paymentDetails.put(MOLPayActivity.mp_preferred_token, "new");

        Intent intent = new Intent(MainActivity.this, MOLPayActivity.class);
        intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, paymentDetails);
        startActivityForResult(intent, MOLPayActivity.MOLPayXDK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MOLPayActivity.MOLPayXDK && resultCode == RESULT_OK){
            Log.d(MOLPayActivity.MOLPAY, "MOLPay result = "+data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
            TextView tw = (TextView)findViewById(R.id.resultTV);
            tw.setText(data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnXdk = (Button) findViewById(R.id.xdk_button);
        btnXdk.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                restartmolpay();

            }
        });

    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // closebtn clicked


        return super.onOptionsItemSelected(item);
    }
}
