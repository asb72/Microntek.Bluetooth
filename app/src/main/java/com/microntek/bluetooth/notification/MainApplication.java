package com.microntek.bluetooth.notification;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager.LayoutParams;
import com.microntek.bluetooth.ui.btNotificationView;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {
    public boolean activeFlag;
    long btdevaddr;
    String callname;
    String callnumber;
    int calltype;
    boolean inphoneFlag;
    public boolean loadPhoneBookFlag;
    public boolean mBtActive;
    public boolean mBtVisible;
    btNotificationView noticeView;
    List<String> phonebook;
    private LayoutParams wmLayoutParams;

    public MainApplication() {
        this.wmLayoutParams = new LayoutParams();
        this.inphoneFlag = false;
        this.activeFlag = false;
        this.loadPhoneBookFlag = false;
        this.phonebook = new ArrayList();
        this.mBtActive = false;
        this.mBtVisible = false;
        this.calltype = 0;
        this.btdevaddr = 0;
        this.callnumber = "";
        this.callname = "";
        this.noticeView = null;
    }

    public LayoutParams getwmParams() {
        return this.wmLayoutParams;
    }

    public void report(Context context, byte b) {
        Intent intent = new Intent("com.microntek.bt.action");
        intent.putExtra("state", b);
        context.sendBroadcast(intent);
    }
}
