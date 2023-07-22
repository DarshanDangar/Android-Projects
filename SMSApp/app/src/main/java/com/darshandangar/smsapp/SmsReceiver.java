package com.darshandangar.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        Object[] smsObj = (Object[]) bundle.get("pdus");

        for (Object obj : smsObj){
            SmsMessage message = SmsMessage.createFromPdu((byte[]) obj);

            String mono = message.getDisplayOriginatingAddress();
            String msg = message.getDisplayMessageBody();

            Log.d("MsgDeatils","MobNo:"+mono + ",Msg:"+msg);

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+919687528489","null","Hello",
                    "null","null");
        }
    }
}
