package cn.edu.gdmec.android.mobileguard.m2theftguard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.edu.gdmec.android.mobileguard.App;


public  class BootCompleteReceiver extends BroadcastReceiver{
    private static final String TAG=BootCompleteReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ((App)(context.getApplicationContext())).correctSIM();
    }
}
