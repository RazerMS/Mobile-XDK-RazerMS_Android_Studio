/*
 * Copyright (C) 2017 Andhie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.molpay.molpayxdkexample;

import android.content.Context;
import android.content.Intent;

import com.molpay.molpayxdk.MOLPayActivity;

import java.util.HashMap;

/**
 * A simple builder to generate payment request for {@link com.molpay.molpayxdk.MOLPayActivity}
 */
public final class MOLPay {

    public static class IntentBuilder {

        private Context context;
        private HashMap<String, Object> map = new HashMap<>();

        /**
         * Create a new IntentBuilder for launching a {@link com.molpay.molpayxdk.MOLPayActivity}
         *
         * @param context {@link Context} required to create {@link android.content.Intent}
         * @return a new IntentBuilder instance
         */
        public static IntentBuilder from(Context context) {
            return new IntentBuilder(context);
        }

        private IntentBuilder(Context context) {
            this.context = context;
        }

        public MandatoryFieldBuilder withMandatoryFields() {
            return new MandatoryFieldBuilder(this, map);
        }

        public OptionalFieldBuilder withOptionalFields() {
            return new OptionalFieldBuilder(this, map);
        }

        public Intent build() {
            Intent intent = new Intent(context, MOLPayActivity.class);
            intent.putExtra(MOLPayActivity.MOLPayPaymentDetails, map);
            return intent;
        }

    }

    public static class MandatoryFieldBuilder {

        private IntentBuilder builder;
        private HashMap<String, Object> map;

        private MandatoryFieldBuilder(IntentBuilder builder, HashMap<String, Object> map) {
            this.builder = builder;
            this.map = map;
        }

        /**
         * @param amount A value not less than '1.00'
         * @return
         */
        public MandatoryFieldBuilder withAmount(String amount) {
            map.put(MOLPayActivity.mp_amount, amount);
            return this;
        }

        public MandatoryFieldBuilder withAppUserName(String userName) {
            map.put(MOLPayActivity.mp_username, userName);
            return this;
        }

        public MandatoryFieldBuilder withAppPassword(String password) {
            map.put(MOLPayActivity.mp_password, password);
            return this;
        }

        public MandatoryFieldBuilder withMerchantID(String merchantID) {
            map.put(MOLPayActivity.mp_merchant_ID, merchantID);
            return this;
        }

        public MandatoryFieldBuilder withAppName(String appName) {
            map.put(MOLPayActivity.mp_app_name, appName);
            return this;
        }

        public MandatoryFieldBuilder withVerificationKey(String verificationKey) {
            map.put(MOLPayActivity.mp_verification_key, verificationKey);
            return this;
        }

        public MandatoryFieldBuilder withOrderId(String orderId) {
            map.put(MOLPayActivity.mp_order_ID, orderId);
            return this;
        }

        /**
         * Currency code based on ISO 4217
         *
         * @param currency valid values such as "USD", "SGD", "MYR", "RMB"
         * @return
         */
        public MandatoryFieldBuilder withCurrency(String currency) {
            map.put(MOLPayActivity.mp_currency, currency);
            return this;
        }

        /**
         * Country code based on ISO 3166-1 alpha-2
         *
         * @param country valid values such as "MY", "SG", "CN"
         * @return
         */
        public MandatoryFieldBuilder withCountry(String country) {
            map.put(MOLPayActivity.mp_country, country);
            return this;
        }

        public IntentBuilder build() {
            return builder;
        }
    }

    public static class OptionalFieldBuilder {

        private IntentBuilder builder;
        private HashMap<String, Object> map;

        private OptionalFieldBuilder(IntentBuilder builder, HashMap<String, Object> map) {
            this.builder = builder;
            this.map = map;
        }

        /**
         * Convenient method to set mp_channel to "multi"
         *
         * @return
         * @see OptionalFieldBuilder#withChannel(String)
         * @see OptionalFieldBuilder#withAllowedChannels(String[])
         */
        public OptionalFieldBuilder withMultiChannel() {
            map.put(MOLPayActivity.mp_channel, "multi");
            return this;
        }

        /**
         * Use 'multi' for all available channels option or {@link OptionalFieldBuilder#withMultiChannel()}.
         * For individual channel seletion, please refer to "Channel Parameter" in "Channel Lists"
         * in the MOLPay API Spec for Merchant pdf.
         *
         * @return
         * @see OptionalFieldBuilder#withMultiChannel()
         * @see OptionalFieldBuilder#withAllowedChannels(String[])
         */
        public OptionalFieldBuilder withChannel(String channel) {
            map.put(MOLPayActivity.mp_channel, channel);
            return this;
        }

        public OptionalFieldBuilder withBillDescription(String billDescription) {
            map.put(MOLPayActivity.mp_bill_description, billDescription);
            return this;
        }

        public OptionalFieldBuilder withBillName(String billName) {
            map.put(MOLPayActivity.mp_bill_name, billName);
            return this;
        }

        public OptionalFieldBuilder withBillEmail(String billEmail) {
            map.put(MOLPayActivity.mp_bill_email, billEmail);
            return this;
        }

        public OptionalFieldBuilder withBillMobile(String billMobile) {
            map.put(MOLPayActivity.mp_bill_mobile, billMobile);
            return this;
        }

        /**
         * Allow channel selection
         *
         * @return
         */
        public OptionalFieldBuilder withChannelEditing() {
            map.put(MOLPayActivity.mp_channel_editing, Boolean.TRUE);
            return this;
        }

        /**
         * Allow billing information editing.
         *
         * @return
         */
        public BillEditableFieldBuilder withEditingEnabled() {
            map.put(MOLPayActivity.mp_editing_enabled, Boolean.TRUE);
            return new BillEditableFieldBuilder(this, map);
        }

        public OptionalFieldBuilder isEscrow() {
            map.put(MOLPayActivity.mp_is_escrow, "1");
            return this;
        }

        /**
         * Credit Card BIN restrictions
         *
         * @param binLock e.g. {"414170","414171"}
         * @return
         * @see OptionalFieldBuilder#withBinLockErrMsg(String)
         */
        public OptionalFieldBuilder withBinLock(String[] binLock) {
            map.put(MOLPayActivity.mp_bin_lock, binLock);
            return this;
        }

        /**
         * Credit Card BIN restrictions
         *
         * @param binLockErrMsg
         * @return
         * @see OptionalFieldBuilder#withBinLock(String[])
         */
        public OptionalFieldBuilder withBinLockErrMsg(String binLockErrMsg) {
            map.put(MOLPayActivity.mp_bin_lock_err_msg, binLockErrMsg);
            return this;
        }

        /**
         * For transaction request use only, do not use this on payment process
         * Provide a valid cash channel transaction id here will display a payment instruction screen.
         *
         * @param transactionId
         * @return
         * @see OptionalFieldBuilder#withTransactionRequestType(String)
         */
        public OptionalFieldBuilder withTransactionId(String transactionId) {
            map.put(MOLPayActivity.mp_transaction_id, transactionId);
            return this;
        }

        /**
         * For transaction request use only, do not use this on payment process
         *
         * @param transactionRequestType
         * @return
         * @see OptionalFieldBuilder#withTransactionId(String)
         */
        public OptionalFieldBuilder withTransactionRequestType(String transactionRequestType) {
            map.put(MOLPayActivity.mp_request_type, transactionRequestType);
            return this;
        }

        /**
         * Use this to customize the UI theme for the payment info screen,
         * the original XDK custom.css file is provided at Example project source for reference and implementation.
         *
         * @param customCssUrl e.g. "file:///android_asset/custom.css"
         * @return
         */
        public OptionalFieldBuilder withCustomCssUrl(String customCssUrl) {
            map.put(MOLPayActivity.mp_custom_css_url, customCssUrl);
            return this;
        }

        /**
         * Set the token id to nominate a preferred token as the default selection, set "new" to allow new card only
         *
         * @param preferredToken token ID or "new"
         * @return
         */
        public OptionalFieldBuilder withPreferredToken(String preferredToken) {
            map.put(MOLPayActivity.mp_preferred_token, preferredToken);
            return this;
        }

        /**
         * @param creditCardTransactionType "SALS" = Capture Transaction (Default), set "AUTH" to authorize the transaction
         * @return
         */
        public OptionalFieldBuilder withCreditCardTransactionType(String creditCardTransactionType) {
            map.put(MOLPayActivity.mp_tcctype, creditCardTransactionType);
            return this;
        }

        /**
         * Process this transaction through the Recurring API, please refer the MOLPay Recurring API pdf
         *
         * @return
         */
        public OptionalFieldBuilder isRecurring() {
            map.put(MOLPayActivity.mp_is_recurring, Boolean.TRUE);
            return this;
        }

        /**
         * Restricts to only allowed predefined payment Channels.
         * This overrides {@link #withMultiChannel()} to only show restricted Channels.
         *
         * @param allowedChannels e.g. {"credit", "credit3", "GPayPal", "alipay"}
         * @return
         * @see OptionalFieldBuilder#withMultiChannel()
         */
        public OptionalFieldBuilder withAllowedChannels(String[] allowedChannels) {
            map.put(MOLPayActivity.mp_allowed_channels, allowedChannels);
            return this;
        }

        /**
         * Convenient method to enable Sandboxed development environment
         *
         * @return
         * @see OptionalFieldBuilder#withSandboxMode(boolean)
         */
        public OptionalFieldBuilder withSandboxMode() {
            map.put(MOLPayActivity.mp_sandbox_mode, Boolean.TRUE);
            return this;
        }

        /**
         * Enable/Disable Sandboxed development environment
         *
         * @return
         * @see OptionalFieldBuilder#withSandboxMode()
         */
        public OptionalFieldBuilder withSandboxMode(boolean enabled) {
            map.put(MOLPayActivity.mp_sandbox_mode, enabled);
            return this;
        }

        /**
         * Required a valid {@link OptionalFieldBuilder#withChannel(String)} value,
         * this will skip the payment info page and go direct to the payment screen.
         *
         * @return
         * @see OptionalFieldBuilder#withChannel(String)
         */
        public OptionalFieldBuilder withExpressMode() {
            map.put(MOLPayActivity.mp_express_mode, Boolean.TRUE);
            return this;
        }

        /**
         * Extended email format validation based on W3C standards.
         *
         * @return
         */
        public OptionalFieldBuilder withAdvancedEmailValidation() {
            map.put(MOLPayActivity.mp_advanced_email_validation_enabled, Boolean.TRUE);
            return this;
        }

        /**
         * Extended phone format validation based on Google i18n standards.
         *
         * @return
         */
        public OptionalFieldBuilder withAdvancedPhoneValidation() {
            map.put(MOLPayActivity.mp_advanced_phone_validation_enabled, Boolean.TRUE);
            return this;
        }

        public IntentBuilder build() {
            return builder;
        }
    }

    public static class BillEditableFieldBuilder {

        private OptionalFieldBuilder builder;
        private HashMap<String, Object> map;

        private BillEditableFieldBuilder(OptionalFieldBuilder builder, HashMap<String, Object> map) {
            this.builder = builder;
            this.map = map;
        }

        /**
         * Explicitly disable billing name editing
         *
         * @return
         */
        public BillEditableFieldBuilder withBillNameEditDisabled() {
            map.put(MOLPayActivity.mp_bill_name_edit_disabled, Boolean.TRUE);
            return this;
        }

        /**
         * Explicitly disable billing email editing
         *
         * @return
         */
        public BillEditableFieldBuilder withBillEmailEditDisabled() {
            map.put(MOLPayActivity.mp_bill_email_edit_disabled, Boolean.TRUE);
            return this;
        }

        /**
         * Explicitly disable billing mobile editing
         *
         * @return
         */
        public BillEditableFieldBuilder withBillMobileEditDisabled() {
            map.put(MOLPayActivity.mp_bill_mobile_edit_disabled, Boolean.TRUE);
            return this;
        }

        /**
         * Explicitly disable billing description editing
         *
         * @return
         */
        public BillEditableFieldBuilder withBillDescriptionEditDisabled() {
            map.put(MOLPayActivity.mp_bill_description_edit_disabled, Boolean.TRUE);
            return this;
        }

        public OptionalFieldBuilder build() {
            return builder;
        }
    }

}
