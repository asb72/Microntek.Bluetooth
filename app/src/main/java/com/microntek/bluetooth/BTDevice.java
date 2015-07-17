package com.microntek.bluetooth;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.microntek.mtcser.BTServiceInf;
import android.microntek.mtcser.BTServiceInf.Stub;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings.System;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BTDevice {
    private boolean autoanswerflag;
    private boolean autoconnectflag;
    private int avState;
    private BTServiceInf btInterface;
    private int busyState;
    private String callInNumbers;
    private long connectDeviceMAC;
    public List<String> deviceList;
    private String dialoutNumbers;
    private int hfState;
    public List<String> historyList;
    private Context mContext;
    public List<String> matchList;
    private String moduleName;
    private String modulePassword;
    private String phoneNumbers;
    private Handler reportHandler;
    public List<String> reportPhonebookList;
    private ServiceConnection serviceConnection;

    /* renamed from: com.microntek.bluetooth.BTDevice.1 */
    class C00091 implements ServiceConnection {
        C00091() {
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BTDevice.this.btInterface = Stub.asInterface(iBinder);
            try {
                BTDevice.this.btInterface.init();
            } catch (Exception e) {
            }
            BTDevice.this.busyState = 0;
            if (BTDevice.this.btInterface != null) {
                try {
                    BTDevice.this.btInterface.syncMatchList();
                    BTDevice.this.hfState = BTDevice.this.btInterface.getBTState();
                    BTDevice.this.avState = BTDevice.this.btInterface.getAVState();
                    BTDevice.this.autoconnectflag = BTDevice.this.btInterface.getAutoConnect();
                    BTDevice.this.autoanswerflag = BTDevice.this.btInterface.getAutoAnswer();
                    BTDevice.this.dialoutNumbers = BTDevice.this.btInterface.getDialOutNum();
                    BTDevice.this.callInNumbers = BTDevice.this.btInterface.getCallInNum();
                    BTDevice.this.phoneNumbers = BTDevice.this.btInterface.getPhoneNum();
                    BTDevice.this.connectDeviceMAC = BTDevice.this.btInterface.getNowDevAddr();
                } catch (Exception e2) {
                }
            }
            BTDevice.this.reportHandler.sendMessageDelayed(BTDevice.this.reportHandler.obtainMessage(65538, BTDevice.this.hfState, BTDevice.this.avState, BTDevice.this.phoneNumbers), 300);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            BTDevice.this.btInterface = null;
        }
    }

    @SuppressLint({"NewApi"})
    public BTDevice(Context context, Handler handler) {
        this.serviceConnection = new C00091();
        this.reportHandler = handler;
        this.mContext = context;
        this.hfState = 0;
        this.avState = 0;
        this.moduleName = "";
        this.modulePassword = "";
        this.busyState = 0;
        this.autoconnectflag = true;
        this.autoanswerflag = false;
        this.btInterface = null;
        this.connectDeviceMAC = 0;
        this.reportPhonebookList = new ArrayList();
        this.matchList = load("MTC.BT.matchList");
        this.deviceList = load("MTC.BT.deviceList");
    }

    private int getListLocation(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (((String) list.get(i)).startsWith(str)) {
                return i;
            }
        }
        return -1;
    }

    private List<String> load(String str) {
        List<String> arrayList = new ArrayList();
        String string = System.getString(this.mContext.getContentResolver(), str);
        if (string != null) {
            String[] split = string.split("\n");
            for (int i = 0; i < split.length; i++) {
                if (split[i].length() > 0) {
                    arrayList.add(split[i]);
                }
            }
        }
        return arrayList;
    }

    public void addPhonebook(String str) {
        this.reportPhonebookList.add(str);
    }

    public void answerCall() {
        try {
            this.btInterface.answerCall();
        } catch (Exception e) {
        }
    }

    public void connectDevice(String str) {
        String substring = str.substring(0, 12);
        if (!isOBDDevice(str)) {
            try {
                this.btInterface.connectBT(substring);
            } catch (Exception e) {
            }
        } else if (getListLocation(this.matchList, substring) == -1) {
            try {
                this.btInterface.connectOBD(substring);
            } catch (Exception e2) {
            }
        }
    }

    public void connectOffClearState() {
        this.busyState = 0;
        this.connectDeviceMAC = 0;
        this.dialoutNumbers = null;
        this.callInNumbers = null;
    }

    public void deleteDevice(String str) {
        String substring = str.substring(0, 12);
        if (isOBDDevice(str)) {
            try {
                this.btInterface.deleteOBD(substring);
                return;
            } catch (Exception e) {
                return;
            }
        }
        try {
            this.btInterface.deleteBT(substring);
        } catch (Exception e2) {
        }
    }

    public void deleteHistory(int i) {
        if (i < this.historyList.size()) {
            this.historyList.remove(i);
            try {
                this.btInterface.deleteHistory(i);
            } catch (Exception e) {
            }
        }
    }

    public void deleteHistoryAll() {
        if (this.historyList.size() > 0) {
            this.historyList.clear();
            try {
                this.btInterface.deleteHistoryAll();
            } catch (Exception e) {
            }
        }
    }

    public void dialOut(char c) {
        try {
            this.btInterface.dialOutSub(c);
        } catch (Exception e) {
        }
    }

    public void dialOut(String str) {
        this.dialoutNumbers = str;
        try {
            this.btInterface.dialOut(str);
        } catch (Exception e) {
        }
    }

    public void disconnectDevice(String str) {
        String substring = str.substring(0, 12);
        if (isOBDDevice(str)) {
            try {
                this.btInterface.disconnectOBD(substring);
                return;
            } catch (Exception e) {
                return;
            }
        }
        try {
            this.btInterface.disconnectBT(substring);
        } catch (Exception e2) {
        }
    }

    public int getAVState() {
        return this.avState;
    }

    public boolean getAutoAnswer() {
        try {
            return this.btInterface.getAutoAnswer();
        } catch (Exception e) {
            return this.autoanswerflag;
        }
    }

    public boolean getAutoConnect() {
        try {
            return this.btInterface.getAutoConnect();
        } catch (Exception e) {
            return this.autoconnectflag;
        }
    }

    public int getBTState() {
        return this.hfState;
    }

    public int getBusy() {
        return this.busyState;
    }

    public long getConnectPhoneMACaddr() {
        return this.connectDeviceMAC;
    }

    public String getDeviceName() {
        try {
            this.moduleName = this.btInterface.getModuleName();
        } catch (Exception e) {
        }
        return this.moduleName;
    }

    public String getDialOutNumbers() {
        return this.dialoutNumbers;
    }

    public String getPassword() {
        try {
            this.modulePassword = this.btInterface.getModulePassword();
        } catch (Exception e) {
        }
        return this.modulePassword;
    }

    public String getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void handupCall() {
        if (this.hfState == 2 || this.hfState == 5) {
            try {
                this.btInterface.hangupCall();
            } catch (Exception e) {
            }
        } else if (this.hfState == 3) {
            try {
                this.btInterface.rejectCall();
            } catch (Exception e2) {
            }
        }
    }

    public boolean isOBDDevice(String str) {
        return str != null && str.toUpperCase().contains("OBD");
    }

    public void musicNext() {
        try {
            this.btInterface.avPlayNext();
        } catch (Exception e) {
        }
    }

    public void musicPlayPause() {
        try {
            this.btInterface.avPlayPause();
        } catch (Exception e) {
        }
    }

    public void musicPrev() {
        try {
            this.btInterface.avPlayPrev();
        } catch (Exception e) {
        }
    }

    public void musicStop() {
        try {
            this.btInterface.avPlayStop();
        } catch (Exception e) {
        }
    }

    public void scanFinish() {
        this.busyState &= -3;
    }

    public void scanStart() {
        this.busyState |= 2;
        try {
            this.btInterface.scanStart();
        } catch (Exception e) {
        }
    }

    public void scanStop() {
        this.busyState &= -3;
        try {
            this.btInterface.scanStop();
        } catch (Exception e) {
        }
    }

    public void setConnectedMac(String str) {
        this.connectDeviceMAC = Long.valueOf(str, 16).longValue();
    }

    public void setDeviceName(String str) {
        try {
            this.btInterface.setModuleName(str);
        } catch (Exception e) {
        }
    }

    public void setMusicMute(boolean z) {
        if (z) {
            try {
                this.btInterface.musicMute();
                return;
            } catch (Exception e) {
                return;
            }
        }
        try {
            this.btInterface.musicUnmute();
        } catch (Exception e2) {
        }
    }

    public void setPassword(String str) {
        try {
            this.btInterface.setModulePassword(str);
        } catch (Exception e) {
        }
    }

    void start() {
        this.mContext.bindService(new Intent("com.microntek.btserver"), this.serviceConnection, 1);
    }

    public void stateChange(int i, int i2, String str) {
        if (i == 1 && this.hfState > 1) {
            this.reportHandler.sendMessageDelayed(this.reportHandler.obtainMessage(65545), 200);
        }
        this.hfState = i;
        this.avState = i2;
        if (this.hfState == 0) {
            this.busyState = 0;
        } else if (this.hfState == 5) {
            this.busyState |= 4;
        } else {
            this.busyState &= -5;
        }
        if (this.hfState == 3) {
            this.callInNumbers = str;
            this.phoneNumbers = str;
        } else if (this.hfState == 2) {
            this.dialoutNumbers = str;
            this.phoneNumbers = str;
        } else if (this.hfState == 5) {
            this.phoneNumbers = str;
        }
    }

    void stop() {
        this.mContext.unbindService(this.serviceConnection);
    }

    public boolean switchAutoAnswer() {
        this.autoanswerflag = !this.autoanswerflag;
        try {
            this.btInterface.setAutoAnswer(this.autoanswerflag);
        } catch (Exception e) {
        }
        return this.autoanswerflag;
    }

    public boolean switchAutoConnect() {
        this.autoconnectflag = !this.autoconnectflag;
        try {
            this.btInterface.setAutoConnect(this.autoconnectflag);
        } catch (Exception e) {
        }
        return this.autoconnectflag;
    }

    public void switchPhoneVoice() {
        try {
            this.btInterface.switchVoice();
        } catch (Exception e) {
        }
    }

    public void syncMatchList(boolean z) {
        try {
            this.btInterface.syncMatchList();
        } catch (Exception e) {
        }
    }

    public void syncPhonebook() {
        this.busyState |= 1;
        this.reportPhonebookList.clear();
        try {
            this.btInterface.syncPhonebook();
        } catch (Exception e) {
        }
    }

    public void syncPhonebookEnd() {
        this.busyState &= -2;
    }

    public void updateHistoryList() {
        this.historyList = load("MTC.BT.logList");
    }

    public void updateList() {
        Collection load = load("MTC.BT.matchList");
        this.matchList.clear();
        this.matchList.addAll(load);
        load = load("MTC.BT.deviceList");
        this.deviceList.clear();
        this.deviceList.addAll(load);
    }
}
