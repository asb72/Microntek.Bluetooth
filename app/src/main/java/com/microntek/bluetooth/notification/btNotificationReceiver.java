package com.microntek.bluetooth.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import com.microntek.bluetooth.BlueToothActivity;
import com.microntek.bluetooth.comm.PreferenceProc;
import com.microntek.bluetooth.ui.btNotificationView;
import java.util.List;

public class btNotificationReceiver extends BroadcastReceiver {
    private String getNumName(List<String> list, String str) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str2 = (String) list.get(i);
            int indexOf = str2.indexOf(94);
            if (indexOf != -1) {
                int indexOf2 = str2.indexOf(94, indexOf + 1);
                if (indexOf2 != -1 && str2.substring(indexOf + 1, indexOf2).equals(str)) {
                    return str2.substring(0, indexOf);
                }
            }
        }
        return str;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        MainApplication mainApplication = (MainApplication) context.getApplicationContext();
        if (action.equals("com.microntek.bt.report")) {
            if (intent.hasExtra("talking_time") && mainApplication.activeFlag) {
                mainApplication.noticeView.updateTalkingTime(intent.getIntExtra("talking_time", 0));
            }
            if (intent.hasExtra("connect_state")) {
                int intExtra = intent.getIntExtra("connect_state", 0);
                String numName;
                if (mainApplication.inphoneFlag) {
                    if (intExtra != 3 && intExtra != 2 && intExtra != 5) {
                        if (mainApplication.activeFlag) {
                            stopFlashget(mainApplication, context);
                        }
                        mainApplication.inphoneFlag = false;
                    } else if (mainApplication.activeFlag) {
                        mainApplication.callnumber = intent.getStringExtra("phone_number");
                        numName = getNumName(mainApplication.phonebook, mainApplication.callnumber);
                        mainApplication.noticeView.updateStatus(intExtra);
                        mainApplication.noticeView.updateNumber(numName);
                    }
                } else if (intExtra == 5 || intExtra == 3 || intExtra == 2) {
                    if (intent.hasExtra("phone_display")) {
                        numName = intent.getStringExtra("phone_display");
                        if (numName.equals("activity")) {
                            Intent intent2 = new Intent();
                            intent2.setClass(context, BlueToothActivity.class);
                            intent2.putExtra("nowapplication", 0);
                            intent2.addFlags(807534592);
                            context.startActivity(intent2);
                        } else {
                            mainApplication.btdevaddr = intent.getLongExtra("phone_mac", 0);
                            updateBTPhoneBook(context, mainApplication.btdevaddr, mainApplication.phonebook);
                            mainApplication.callnumber = intent.getStringExtra("phone_number");
                            String numName2 = getNumName(mainApplication.phonebook, mainApplication.callnumber);
                            startFlashget(mainApplication, context);
                            mainApplication.noticeView.updateNumber(numName2);
                            mainApplication.noticeView.updateStatus(intExtra);
                            if (numName.equals("flashget.show")) {
                                mainApplication.noticeView.setVisibility(View.VISIBLE);
                            } else {
                                mainApplication.noticeView.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                    mainApplication.inphoneFlag = true;
                }
            }
        } else if (action.equals("com.microntek.irkeyDown")) {
            if (mainApplication.activeFlag && !mainApplication.mBtActive) {
                switch (intent.getIntExtra("keyCode", 0)) {
                    case 69:
                        mainApplication.report(context, (byte) 16);
                    case 70:
                        mainApplication.report(context, (byte) 17);
                    case 80:
                        mainApplication.report(context, (byte) 19);
                    default:
                }
            }
        } else if (action.equals("com.microntek.smallbtoff")) {
            if (!mainApplication.activeFlag) {
                return;
            }
            if (mainApplication.mBtVisible) {
                stopFlashget(mainApplication, context);
            } else {
                mainApplication.noticeView.setVisibility(View.INVISIBLE);
            }
        } else if (!action.equals("com.microntek.smallbton") || !mainApplication.activeFlag) {
        } else {
            if (mainApplication.mBtVisible) {
                stopFlashget(mainApplication, context);
            } else {
                mainApplication.noticeView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void startFlashget(MainApplication mainApplication, Context context) {
        mainApplication.noticeView = new btNotificationView(context);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutParams layoutParams = mainApplication.getwmParams();
/*FIXME
        layoutParams.type = 2003;
        layoutParams.format = 1;
        layoutParams.flags = 40;
        layoutParams.gravity = 81;
  */
        layoutParams.width = -1;
        layoutParams.height = -2;
        windowManager.addView(mainApplication.noticeView, layoutParams);
        mainApplication.activeFlag = true;
    }

    public void stopFlashget(MainApplication mainApplication, Context context) {
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).removeView(mainApplication.noticeView);
        mainApplication.activeFlag = false;
    }

    void updateBTPhoneBook(Context context, long j, List<String> list) {
        if (j != 0) {
            try {
                new PreferenceProc(context.createPackageContext("com.microntek.bluetooth", Context.CONTEXT_IGNORE_SECURITY)).loadPhoneBook(j, list);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
