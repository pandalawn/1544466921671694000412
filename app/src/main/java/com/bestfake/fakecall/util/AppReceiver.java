package com.bestfake.fakecall.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bestfake.fakecall.facebook.FBVideoCallActivity;
import com.bestfake.fakecall.facebook.FBVoiceCallActivity;
import com.bestfake.fakecall.telegram.TeleVideoCallActivity;
import com.bestfake.fakecall.telegram.TeleVoiceCallActivity;
import com.bestfake.fakecall.whatapp.WAVideoCallActivity;
import com.bestfake.fakecall.whatapp.WAVoiceCallActivity;

import static com.bestfake.fakecall.activity.DetailFakeActivity.rd_form;
import static com.bestfake.fakecall.activity.DetailFakeActivity.rd_vid;

public class AppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (rd_form ==1) {
            if (rd_vid ==2){
                Intent intent2 = new Intent(context, WAVoiceCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            } else if (rd_vid==1){
                Intent intent2 = new Intent(context, WAVideoCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            } else {
                Intent intent2 = new Intent(context, WAVideoCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }
        } else if (rd_form==2){
            if (rd_vid ==2){
                Intent intent2 = new Intent(context, FBVoiceCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            } else  if (rd_vid ==1){
                Intent intent2 = new Intent(context, FBVideoCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            } else {
                Intent intent2 = new Intent(context, FBVideoCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }
        }else if (rd_form==3) {
            if (rd_vid == 2) {
                Intent intent2 = new Intent(context, TeleVoiceCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            } else if (rd_vid == 1) {
                Intent intent2 = new Intent(context, TeleVideoCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            } else {
                Intent intent2 = new Intent(context, TeleVideoCallActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }
        }

    }

}
