package com.joaquimley.smsparsing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * A broadcast receiver who listens for incoming SMS
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            String smsSender = "";
            String smsBody = "";

            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsBody += smsMessage.getMessageBody();
                smsSender = smsMessage.getOriginatingAddress();

            }


            if (smsBody.startsWith(SmsHelper.SMS_CONDITION)) {
                Log.d(TAG, "Sms with condition detected");
                Toast.makeText(context, "BroadcastReceiver caught conditional SMS: " + smsBody, Toast.LENGTH_LONG).show();
            }
            Log.d(TAG, "SMS detected: From " + smsSender + " With text " + smsBody);
            if(!smsBody.equals("")) {

                Log.d("smsbody",smsBody);
                Log.d("sender",smsSender);
                intent.setClassName("com.joaquimley.smsparsing","com.joaquimley.smsparsing.Main2Activity");
                intent.putExtra("text", smsBody);
                intent.putExtra("sender", smsSender);
                //intent.setData(Uri.parse("http://wwww.google.com"));
                context.startActivity(intent);
            }
        }
    }



}
