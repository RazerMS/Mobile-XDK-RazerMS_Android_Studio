package com.molpay.molpayxdkexample;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.molpay.molpayxdk.MOLPayActivity;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MOLPayActivity.MOLPayXDK && resultCode == RESULT_OK){
            Log.d(MOLPayActivity.MOLPAY, "MOLPay result = " + data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
            TextView tw = (TextView)findViewById(R.id.resultTV);
            tw.setText(data.getStringExtra(MOLPayActivity.MOLPayTransactionResult));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HashMap<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put(MOLPayActivity.mp_amount, "1.10");
        paymentDetails.put(MOLPayActivity.mp_username, "");
        paymentDetails.put(MOLPayActivity.mp_password, "");
        paymentDetails.put(MOLPayActivity.mp_merchant_ID, "");
        paymentDetails.put(MOLPayActivity.mp_app_name, "");
        paymentDetails.put(MOLPayActivity.mp_order_ID, Calendar.getInstance().getTimeInMillis());
        paymentDetails.put(MOLPayActivity.mp_currency, "MYR");
        paymentDetails.put(MOLPayActivity.mp_country, "MY");
        paymentDetails.put(MOLPayActivity.mp_verification_key, "");
        paymentDetails.put(MOLPayActivity.mp_channel, "multi");
        paymentDetails.put(MOLPayActivity.mp_bill_description, "description");
        paymentDetails.put(MOLPayActivity.mp_bill_name, "name");
        paymentDetails.put(MOLPayActivity.mp_bill_email, "example@email.com");
        paymentDetails.put(MOLPayActivity.mp_bill_mobile, "+601234567789");
        
        //paymentDetails.put(MOLPayActivity.mp_channel_editing, false);
        //paymentDetails.put(MOLPayActivity.mp_editing_enabled, true);
        //paymentDetails.put(MOLPayActivity.mp_transaction_id, "");
        //paymentDetails.put(MOLPayActivity.mp_request_type, "");

        //String binlock[] = {"123456","234567"};
        //paymentDetails.put(MOLPayActivity.mp_bin_lock, binlock);
        //paymentDetails.put(MOLPayActivity.mp_bin_lock_err_msg, "Wrong BIN format");

        //paymentDetails.put(MOLPayActivity.mp_is_escrow, "");
        //paymentDetails.put(MOLPayActivity.mp_filter, "1");
        //paymentDetails.put(MOLPayActivity.mp_custom_css_url, "file:///android_asset/custom.css");
        //paymentDetails.put(MOLPayActivity.mp_preferred_token, "");
        //paymentDetails.put(MOLPayActivity.mp_tcctype, ""); // SALS // AUTH
        //paymentDetails.put(MOLPayActivity.mp_is_recurring, false);
        //String allowedchannels[] = {"credit","credit3"};
        //paymentDetails.put(MOLPayActivity.mp_allowed_channels, allowedchannels);
        //paymentDetails.put(MOLPayActivity.mp_sandbox_mode, true);
        //paymentDetails.put(MOLPayActivity.mp_express_mode, true);
        //paymentDetails.put(MOLPayActivity.mp_advanced_email_validation_enabled, true);
        //paymentDetails.put(MOLPayActivity.mp_advanced_phone_validation_enabled, true);
        //paymentDetails.put(MOLPayActivity.mp_bill_name_edit_disabled, false);
        //paymentDetails.put(MOLPayActivity.mp_bill_email_edit_disabled, false);
        //paymentDetails.put(MOLPayActivity.mp_bill_mobile_edit_disabled, false);
        //paymentDetails.put(MOLPayActivity.mp_bill_description_edit_disabled, false);
        //paymentDetails.put(MOLPayActivity.mp_language, "EN");
        //paymentDetails.put(MOLPayActivity.mp_dev_mode, true);
        //paymentDetails.put(MOLPayActivity.mp_cash_waittime, 24);
        //paymentDetails.put(MOLPayActivity.mp_non_3DS, true);
        //paymentDetails.put(MOLPayActivity.mp_card_list_disabled, true);

        //String disabledChannels[] = {"credit"};
        //paymentDetails.put(MOLPayActivity.mp_disabled_channels, disabledChannels);

        Intent intent = new Intent(MainActivity.this, MOLPayActivity.class);
        intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, paymentDetails);
        startActivityForResult(intent, MOLPayActivity.MOLPayXDK);
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
