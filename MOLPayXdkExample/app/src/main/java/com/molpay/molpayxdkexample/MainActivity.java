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
    String orderId;
    String billName;
    String Country;
    String Currency;
    String MobileNum;
    String TtlAmount;


    public void restartmolpay(HashMap<String, Object> paymentDetails) {
        Intent intent = new Intent(MainActivity.this, MOLPayActivity.class);
        intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, paymentDetails);
        startActivityForResult(intent, MOLPayActivity.MOLPayXDK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize payment details
        HashMap<String, Object> paymentDetails = new HashMap<>();
        // Optional, REQUIRED when use online Sandbox environment and account credentials.
//        paymentDetails.put(MOLPayActivity.mp_dev_mode, false);

        // Mandatory String. Values obtained from MOLPay.
        paymentDetails.put(MOLPayActivity.mp_username, "RMSxdk_2022");
        paymentDetails.put(MOLPayActivity.mp_password, "RMSpwd@2022");
        paymentDetails.put(MOLPayActivity.mp_merchant_ID, "rmsxdk_mobile_Dev");
        paymentDetails.put(MOLPayActivity.mp_app_name, "mobile");
        paymentDetails.put(MOLPayActivity.mp_verification_key, "ee738b541eff7b6b495e44771f71c0ec");

        // Mandatory String. Payment values.
        paymentDetails.put(MOLPayActivity.mp_amount, "1.10"); // Minimum 1.01
        paymentDetails.put(MOLPayActivity.mp_order_ID, Calendar.getInstance().getTimeInMillis());
        paymentDetails.put(MOLPayActivity.mp_currency, "MYR");
        paymentDetails.put(MOLPayActivity.mp_country, "MY");

        // Optional, but required payment values. User input will be required when values not passed.
        // Use 'multi' for all available channels option. For individual channel     seletion, please refer to https://github.com/MOLPay/molpay-mobile-xdk-examples/blob/master/channel_list.tsv.
        paymentDetails.put(MOLPayActivity.mp_channel, "multi");
        paymentDetails.put(MOLPayActivity.mp_bill_description, "billdesc");
        paymentDetails.put(MOLPayActivity.mp_bill_name, "billname");
        paymentDetails.put(MOLPayActivity.mp_bill_email, "email@domain.com");
        paymentDetails.put(MOLPayActivity.mp_bill_mobile, "+1234567");

        // Optional, allow channel selection.
        // paymentDetails.put(MOLPayActivity.mp_channel_editing, false);

        // Optional, allow billing information editing.
//        paymentDetails.put(MOLPayActivity.mp_editing_enabled, true);

        // Optional, for Escrow.
        // paymentDetails.put(MOLPayActivity.mp_is_escrow, ""); // Put "1" to enable escrow

        // Optional, for credit card BIN restrictions and campaigns.
//        String binlock[] = {"414170","414171"};
        // paymentDetails.put(MOLPayActivity.mp_bin_lock, binlock);

        // Optional, for mp_bin_lock alert error.
        // paymentDetails.put(MOLPayActivity.mp_bin_lock_err_msg, "Only UOB allowed");

        // WARNING! FOR TRANSACTION QUERY USE ONLY, DO NOT USE THIS ON PAYMENT PROCESS.
        // Optional, provide a valid cash channel transaction id here will display a payment instruction screen. Required if mp_request_type is 'Receipt'.
        // paymentDetails.put(MOLPayActivity.mp_transaction_id, "");
        // Optional, use 'Receipt' for Cash channels, and 'Status' for transaction status query.
        // paymentDetails.put(MOLPayActivity.mp_request_type, "");

        // Optional, use this to customize the UI theme for the payment info screen, the original XDK custom.css file can be obtained at https://github.com/MOLPay/molpay-mobile-xdk-examples/blob/master/custom.css.
        // paymentDetails.put(MOLPayActivity.mp_custom_css_url, "file:///android_asset/custom.css");

        // Optional, set the token id to nominate a preferred token as the default selection, set "new" to allow new card only.
//        paymentDetails.put(MOLPayActivity.mp_preferred_token, "new");

        // Optional, credit card transaction type, set "AUTH" to authorize the transaction.
        // paymentDetails.put(MOLPayActivity.mp_tcctype, "");

        // Optional, required valid credit card channel, set true to process this transaction through the recurring api, please refer the MOLPay Recurring API pdf.
        // paymentDetails.put(MOLPayActivity.mp_is_recurring, false);

        // Optional, show nominated channels.
//        String allowedchannels[] = {"credit","credit3"};
        // paymentDetails.put(MOLPayActivity.mp_allowed_channels, allowedchannels);

        // Optional, simulate offline payment, set boolean value to enable.
        // paymentDetails.put(MOLPayActivity.mp_sandbox_mode, true);

        // Optional, required a valid mp_channel value, this will skip the payment info page and go direct to the payment screen.
//        paymentDetails.put(MOLPayActivity.mp_express_mode, false);

        // Optional, extended email format validation based on W3C standards.
        // paymentDetails.put(MOLPayActivity.mp_advanced_email_validation_enabled, true);

        // Optional, extended phone format validation based on Google i18n standards.
        // paymentDetails.put(MOLPayActivity.mp_advanced_phone_validation_enabled, true);

        // Optional, explicitly force disable user input.
//        paymentDetails.put(MOLPayActivity.mp_bill_name_edit_disabled, true);
//        paymentDetails.put(MOLPayActivity.mp_bill_email_edit_disabled, true);
//        paymentDetails.put(MOLPayActivity.mp_bill_mobile_edit_disabled, true);
//        paymentDetails.put(MOLPayActivity.mp_bill_description_edit_disabled, true);

        // Optional, EN, MS, VI, TH, FIL, MY, KM, ID, ZH.
        //paymentDetails.put(MOLPayActivity.mp_language, "EN");

        // Optional, Cash channel payment request expiration duration in hour.
        // paymentDetails.put(MOLPayActivity.mp_cash_waittime, 48);

        // Optional, allow bypass of 3DS on some credit card channels.
        // paymentDetails.put(MOLPayActivity.mp_non_3DS, true);

        // Optional, disable card list option.
        // paymentDetails.put(MOLPayActivity.mp_card_list_disabled, true);

        // Optional for channels restriction, this option has less priority than mp_allowed_channels.
//        String disabledChannels[] = {""};
        // paymentDetails.put(MOLPayActivity.mp_disabled_channels, disabledChannels);

        orderId = paymentDetails.get(MOLPayActivity.mp_order_ID).toString();
        billName = paymentDetails.get(MOLPayActivity.mp_bill_name).toString();
        Country = paymentDetails.get(MOLPayActivity.mp_country).toString();
        Currency = paymentDetails.get(MOLPayActivity.mp_currency).toString();
        MobileNum = paymentDetails.get(MOLPayActivity.mp_bill_mobile).toString();
        TtlAmount = paymentDetails.get(MOLPayActivity.mp_amount).toString();

        setContentView(R.layout.activity_main);

        TextView orderIdTextView = findViewById(R.id.order_id);
        orderIdTextView.setText(orderId);

        TextView billNameTextView = findViewById(R.id.bill_name);
        billNameTextView.setText(billName);

        TextView CountryTextView = findViewById(R.id.country);
        CountryTextView.setText(Country);

        TextView CurrencyTextView = findViewById(R.id.currency);
        CurrencyTextView.setText(Currency);

        TextView MobileNumTextView = findViewById(R.id.mobile);
        MobileNumTextView.setText(MobileNum);

        TextView TtlAmountTextView = findViewById(R.id.amount);
        TtlAmountTextView.setText(TtlAmount);


        Button btnXdk = (Button) findViewById(R.id.xdk_button);
        btnXdk.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                restartmolpay(paymentDetails);
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
