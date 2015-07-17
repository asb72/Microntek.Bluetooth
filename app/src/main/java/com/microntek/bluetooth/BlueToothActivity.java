package com.microntek.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import com.microntek.bluetooth.comm.HanziToPinyin;
import com.microntek.bluetooth.comm.HanziToPinyin.Token;
import com.microntek.bluetooth.comm.PreferenceProc;
import com.microntek.bluetooth.notification.MainApplication;
import com.microntek.bluetooth.ui.Ui3;
import java.util.ArrayList;
import java.util.List;

public class BlueToothActivity extends FragmentActivity implements BlueToothInterface {
    private BroadcastReceiver BTReceiver;
    private AudioManager audioManager;
    private BroadcastReceiver btServiceReceiver;
    private boolean hasLoadPreferenceFlag;
    private boolean homewakeflag;
    private BTDevice mBtDevice;
    private UiBase mUi;
    MainApplication mp;
    private BroadcastReceiver mtckeyproc;
    private List<Character> phoneBookFirstChar;
    private List<String> phoneBookList;
    private PreferenceProc preferenceProc;
    private boolean serviceConnectFlag;
    private Handler uiHandler;

    /* renamed from: com.microntek.bluetooth.BlueToothActivity.1 */
    class C00101 extends Handler {
        C00101() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 65538:
                    BlueToothActivity.this.serviceConnectFlag = true;
                    BlueToothActivity.this.handleStateChange(message.arg1, message.arg2, (String) message.obj);
                case 65545:
                    if (BlueToothActivity.this.homewakeflag) {
                        BlueToothActivity.this.homewakeflag = false;
                        BlueToothActivity.this.finish();
                    }
                case 65546:
                    if (message.arg1 == 1) {
                        BlueToothActivity.this.musicNext();
                    } else {
                        BlueToothActivity.this.musicPrev();
                    }
                default:
            }
        }
    }

    /* renamed from: com.microntek.bluetooth.BlueToothActivity.2 */
    class C00112 extends BroadcastReceiver {
        C00112() {
        }

        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("com.microntek.bt.report")) {
                return;
            }
            if (intent.hasExtra("connected_mac")) {
                BlueToothActivity.this.mBtDevice.setConnectedMac(intent.getStringExtra("connected_mac"));
                if (BlueToothActivity.this.mUi.mPage == 4) {
                    BlueToothActivity.this.mUi.updatePageView();
                }
            } else if (intent.hasExtra("phonebook_record")) {
                BlueToothActivity.this.mBtDevice.addPhonebook(intent.getStringExtra("phonebook_record"));
            } else if (intent.hasExtra("phonebook_end")) {
                BlueToothActivity.this.mBtDevice.syncPhonebookEnd();
                BlueToothActivity.this.mUi.setBusyShow(false);
                BlueToothActivity.this.assortPhoneBook();
                BlueToothActivity.this.preferenceProc.savePhoneBook(BlueToothActivity.this.mBtDevice.getConnectPhoneMACaddr(), BlueToothActivity.this.phoneBookList);
            } else if (intent.hasExtra("bt_state")) {
                switch (intent.getIntExtra("bt_state", 0)) {
                    case 90:
                        BlueToothActivity.this.mBtDevice.updateList();
                        if (BlueToothActivity.this.mUi.mPage == 4) {
                            BlueToothActivity.this.mUi.updatePageView();
                        }
                    case 91:
                        BlueToothActivity.this.mBtDevice.scanFinish();
                        BlueToothActivity.this.mUi.setBusyShow(false);
                    default:
                }
            } else if (intent.hasExtra("connect_state")) {
                int intExtra = intent.getIntExtra("connect_state", 0);
                int intExtra2 = intent.getIntExtra("connect_avstate", 0);
                String stringExtra = intent.getStringExtra("phone_number");
                BlueToothActivity.this.mBtDevice.stateChange(intExtra, intExtra2, stringExtra);
                BlueToothActivity.this.handleStateChange(intExtra, intExtra2, stringExtra);
            } else if (intent.hasExtra("talking_time")) {
                BlueToothActivity.this.mUi.updateTalkingTime(intent.getIntExtra("talking_time", 0));
            }
        }
    }

    /* renamed from: com.microntek.bluetooth.BlueToothActivity.3 */
    class C00123 extends BroadcastReceiver {
        C00123() {
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("class");
            if (!stringExtra.equals("com.microntek.bluetooth") && !stringExtra.equals("phonecallin") && !stringExtra.equals("phonecallout")) {
                BlueToothActivity.this.finish();
            }
        }
    }

    /* renamed from: com.microntek.bluetooth.BlueToothActivity.4 */
    class C00134 extends BroadcastReceiver {
        C00134() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int intExtra = intent.getIntExtra("keyCode", 0);
            if (action.equals("com.microntek.irkeyDown")) {
                Message obtainMessage;
                switch (intExtra) {
                    case 3:
                        BlueToothActivity.this.musicPlayPause();
                    case 6:
                    case 22:
                    case 45:
                    case 61:
                        BlueToothActivity.this.musicPrev();
                    case 13:
                        BlueToothActivity.this.musicStop();
                    case 14:
                    case 24:
                    case 46:
                    case 62:
                        BlueToothActivity.this.musicNext();
                    case 58:
                        BlueToothActivity.this.uiHandler.removeMessages(65546);
                        obtainMessage = BlueToothActivity.this.uiHandler.obtainMessage();
                        obtainMessage.what = 65546;
                        obtainMessage.arg1 = 2;
                        BlueToothActivity.this.uiHandler.sendMessageDelayed(obtainMessage, 300);
                    case 59:
                        BlueToothActivity.this.uiHandler.removeMessages(65546);
                        obtainMessage = BlueToothActivity.this.uiHandler.obtainMessage();
                        obtainMessage.what = 65546;
                        obtainMessage.arg1 = 1;
                        BlueToothActivity.this.uiHandler.sendMessageDelayed(obtainMessage, 300);
                    case 69:
                        if (BlueToothActivity.this.mUi.mPage == 1) {
                            BlueToothActivity.this.mUi.DialAnswer();
                        } else if (BlueToothActivity.this.mBtDevice.getBTState() != 0) {
                            BlueToothActivity.this.mUi.SwitchToPage(1);
                        }
                    case 70:
                        BlueToothActivity.this.mp.report(context, (byte) 17);
                    case 80:
                        BlueToothActivity.this.mp.report(context, (byte) 19);
                    default:
                }
            }
        }
    }

    public BlueToothActivity() {
        this.audioManager = null;
        this.uiHandler = new C00101();
        this.btServiceReceiver = new C00112();
        this.BTReceiver = new C00123();
        this.mtckeyproc = new C00134();
    }

    private void addBlueToothWidget() {
        Intent intent = new Intent("com.android.MTClauncher.action.INSTALL_WIDGETS");
        intent.putExtra("myWidget.action", 10520);
        intent.putExtra("myWidget.packageName", "com.microntek.widget.bluetooth");
        sendBroadcast(intent);
    }

    private void assortPhoneBook() {
        this.phoneBookList.clear();
        this.phoneBookFirstChar.clear();
        int size = this.mBtDevice.reportPhonebookList.size();
        for (int i = 0; i < size; i++) {
            String str = (String) this.mBtDevice.reportPhonebookList.get(i);
            if (str != null) {
                int indexOf = str.indexOf(94);
                if (!(indexOf == -1 || str.indexOf(94, indexOf + 1) == -1 || phoneBookCheckRepeat(str))) {
                    Token token = HanziToPinyin.getInstance().getToken(str.charAt(0));
                    char charAt = 2 == token.type ? token.target.charAt(0) : 1 == token.type ? Character.toUpperCase(token.source.charAt(0)) : 'u';
                    if (charAt < 'A' || charAt > 'Z') {
                        this.phoneBookList.add(str);
                        this.phoneBookFirstChar.add(Character.valueOf('u'));
                    } else {
                        int searchPhoneBookLetterIndex = searchPhoneBookLetterIndex(charAt, this.phoneBookFirstChar);
                        if (searchPhoneBookLetterIndex == -1) {
                            this.phoneBookList.add(str);
                            this.phoneBookFirstChar.add(Character.valueOf(charAt));
                        } else {
                            this.phoneBookList.add(searchPhoneBookLetterIndex, str);
                            this.phoneBookFirstChar.add(searchPhoneBookLetterIndex, Character.valueOf(charAt));
                        }
                    }
                }
            }
        }
        if (this.mUi.mPage == 2) {
            this.mUi.updatePageView();
        }
    }

    private void handleStateChange(int i, int i2, String str) {
        int i3 = 33554432;
        if (VERSION.SDK_INT == 19) {
            i3 = Integer.MIN_VALUE;
        }
        if (i == 3 || i == 2 || i == 5) {
            getWindow().addFlags(i3);
        } else {
            getWindow().clearFlags(i3);
        }
        if (i == 0) {
            this.phoneBookList.clear();
            this.phoneBookFirstChar.clear();
            this.hasLoadPreferenceFlag = false;
            this.mBtDevice.connectOffClearState();
            if (!(this.mUi.mPage == 0 || this.mUi.mPage == 4 || this.mUi.mPage == 6)) {
                if (this.mUi.getUiType() == 1) {
                    this.mUi.SwitchToPage(0);
                } else {
                    this.mUi.SwitchToPage(4);
                }
            }
        } else {
            this.mBtDevice.setMusicMute(false);
            if (!(this.hasLoadPreferenceFlag || this.mBtDevice.getConnectPhoneMACaddr() == 0)) {
                this.hasLoadPreferenceFlag = true;
                this.preferenceProc.loadPhoneBook(this.mBtDevice.getConnectPhoneMACaddr(), this.phoneBookList);
                updatePhoneBookFirstChar();
            }
            if (i == 2 || i == 3 || i == 5) {
                if (1 != this.mUi.mPage) {
                    this.mUi.SwitchToPage(1);
                }
            } else if (i == 1 && 1 == this.mUi.mPage) {
                this.mUi.clearDialNumbers();
            }
            if (this.mUi.mPage == -1) {
                if (this.mUi.getUiType() == 1) {
                    this.mUi.SwitchToPage(0);
                } else {
                    this.mUi.SwitchToPage(1);
                }
            }
        }
        if (i2 == 0) {
            removeBlueToothWidget();
            if (this.mUi.mPage == 5) {
                if (this.mUi.getUiType() == 1) {
                    this.mUi.SwitchToPage(0);
                } else {
                    this.mUi.SwitchToPage(4);
                }
            }
        } else {
            addBlueToothWidget();
        }
        this.mUi.updateBtStatus();
    }

    private boolean phoneBookCheckRepeat(String str) {
        int size = this.phoneBookList.size();
        for (int i = 0; i < size; i++) {
            if (((String) this.phoneBookList.get(i)).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void removeBlueToothWidget() {
        Intent intent = new Intent("com.android.MTClauncher.action.INSTALL_WIDGETS");
        intent.putExtra("myWidget.action", 10521);
        intent.putExtra("myWidget.packageName", "com.microntek.widget.bluetooth");
        sendBroadcast(intent);
    }

    private int searchPhoneBookLetterIndex(char c, List<Character> list) {
        int i;
        int i2 = 0;
        int size = list.size();
        if (c < 'M') {
            while (i2 < size) {
                if (((Character) list.get(i2)).charValue() > c) {
                    i = i2;
                    break;
                }
                i2++;
            }
            i = -1;
        } else {
            for (int i3 = size - 1; i3 >= 0; i3--) {
                if (((Character) list.get(i3)).charValue() <= c) {
                    i = i3 + 1;
                    break;
                }
            }
            i = 0;
        }
        return i >= size ? -1 : i;
    }

    private void updatePhoneBookFirstChar() {
        int size = this.phoneBookList.size();
        this.phoneBookFirstChar.clear();
        for (int i = 0; i < size; i++) {
            Token token = HanziToPinyin.getInstance().getToken(((String) this.phoneBookList.get(i)).charAt(0));
            char charAt = 2 == token.type ? token.target.charAt(0) : 1 == token.type ? Character.toUpperCase(token.source.charAt(0)) : 'u';
            if (charAt < 'A' || charAt > 'Z') {
                this.phoneBookFirstChar.add(Character.valueOf('u'));
            } else {
                this.phoneBookFirstChar.add(Character.valueOf(charAt));
            }
        }
    }

    public void answerCall() {
        if (this.mBtDevice != null) {
            this.mBtDevice.answerCall();
        }
    }

    public void connectDevice(String str) {
        if (this.mBtDevice.getBusy() == 0) {
            this.mBtDevice.connectDevice(str);
            this.mUi.updatePageView();
            return;
        }
        postBusyShow();
    }

    public void deleteDevice(String str) {
        this.mBtDevice.deleteDevice(str);
        this.mUi.updatePageView();
    }

    public void deleteHistory(int i) {
        this.mBtDevice.deleteHistory(i);
    }

    public void deleteHistoryAll() {
        this.mBtDevice.deleteHistoryAll();
    }

    public void dialOut(char c) {
        if (this.mBtDevice != null) {
            this.mBtDevice.dialOut(c);
        }
    }

    public void dialOut(String str) {
        if (this.mBtDevice != null) {
            this.mBtDevice.dialOut(str);
        }
    }

    public void disconnectDevice(String str) {
        if (this.mBtDevice.getBusy() == 0) {
            this.mBtDevice.disconnectDevice(str);
            this.mUi.updatePageView();
            return;
        }
        postBusyShow();
    }

    public int getAVState() {
        return this.mBtDevice != null ? this.mBtDevice.getAVState() : 0;
    }

    public boolean getAutoAnswer() {
        return this.mBtDevice != null ? this.mBtDevice.getAutoAnswer() : false;
    }

    public boolean getAutoConnect() {
        return this.mBtDevice != null ? this.mBtDevice.getAutoConnect() : false;
    }

    public int getBTState() {
        return this.mBtDevice != null ? this.mBtDevice.getBTState() : 0;
    }

    public int getBusy() {
        return this.mBtDevice != null ? this.mBtDevice.getBusy() : 0;
    }

    public long getConnectPhoneMACaddr() {
        return this.mBtDevice != null ? this.mBtDevice.getConnectPhoneMACaddr() : 0;
    }

    public List<String> getDeviceList() {
        return this.mBtDevice != null ? this.mBtDevice.deviceList : null;
    }

    public String getDeviceName() {
        return this.mBtDevice != null ? this.mBtDevice.getDeviceName() : null;
    }

    public String getDialOutNumbers() {
        return this.mBtDevice != null ? this.mBtDevice.getDialOutNumbers() : null;
    }

    public List<String> getMatchList() {
        return this.mBtDevice != null ? this.mBtDevice.matchList : null;
    }

    public String getNameOfNumbers(String str) {
        int size = this.phoneBookList.size();
        for (int i = 0; i < size; i++) {
            String str2 = (String) this.phoneBookList.get(i);
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

    public String getPassword() {
        return this.mBtDevice != null ? this.mBtDevice.getPassword() : null;
    }

    public List<Character> getPhoneFirstChar() {
        return this.phoneBookFirstChar;
    }

    public List<String> getPhoneHistoryList() {
        return this.mBtDevice.historyList;
    }

    public List<String> getPhoneList() {
        return this.phoneBookList;
    }

    public String getPhoneNumbers() {
        return this.mBtDevice != null ? this.mBtDevice.getPhoneNumbers() : null;
    }

    public boolean getServiceState() {
        return this.serviceConnectFlag;
    }

    public void hangupCall() {
        if (this.mBtDevice != null) {
            this.mBtDevice.handupCall();
        }
    }

    public boolean isOBDDevice(String str) {
        return this.mBtDevice.isOBDDevice(str);
    }

    public void musicNext() {
        this.mBtDevice.musicNext();
    }

    public void musicPlayPause() {
        this.mBtDevice.musicPlayPause();
    }

    public void musicPrev() {
        this.mBtDevice.musicPrev();
    }

    public void musicStop() {
        this.mBtDevice.musicStop();
    }

    public void onBackPressed() {
        int bTState = this.mBtDevice.getBTState();
        if (bTState != 2 && bTState != 3 && bTState != 5) {
            if (this.mUi.getUiType() != 1 || this.mUi.mPage == 0) {
                super.onBackPressed();
            } else {
                this.mUi.SwitchToPage(0);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("LIU", "onCreate");
        this.mp = (MainApplication) getApplicationContext();
        this.mp.mBtActive = true;
        startService(new Intent("com.microntek.btserver"));
        this.homewakeflag = false;
        if (getIntent().hasExtra("nowapplication")) {
            this.homewakeflag = true;
        }
        this.preferenceProc = new PreferenceProc(this);
        this.audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        this.phoneBookList = new ArrayList();
        this.phoneBookFirstChar = new ArrayList();
        this.mBtDevice = new BTDevice(this, this.uiHandler);
        this.mBtDevice.start();
        this.mUi = new Ui3(this);
        setContentView(this.mUi.getLayout());
        this.mUi.init();
        Intent intent = new Intent("com.microntek.bootcheck");
        intent.putExtra("class", "com.microntek.bluetooth");
        sendBroadcast(intent);
        this.audioManager.setParameters("av_channel_enter=gsm_bt");
        intent = new Intent("com.microntek.canbusdisplay");
        intent.putExtra("type", "bluetooth-on");
        sendBroadcast(intent);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.bt.report");
        registerReceiver(this.btServiceReceiver, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.bootcheck");
        registerReceiver(this.BTReceiver, intentFilter);
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.irkeyDown");
        registerReceiver(this.mtckeyproc, intentFilter);
    }

    protected void onDestroy() {
        unregisterReceiver(this.btServiceReceiver);
        unregisterReceiver(this.BTReceiver);
        unregisterReceiver(this.mtckeyproc);
        this.audioManager.setParameters("av_channel_exit=gsm_bt");
        this.mBtDevice.stop();
        removeBlueToothWidget();
        this.mp.mBtActive = false;
        Intent intent = new Intent("com.microntek.canbusdisplay");
        intent.putExtra("type", "bluetooth-off");
        sendBroadcast(intent);
        super.onDestroy();
    }

    protected void onRestart() {
        super.onRestart();
        this.mp.mBtVisible = true;
        sendBroadcast(new Intent("com.microntek.musicclockreset"));
        sendBroadcast(new Intent("com.microntek.smallbtoff"));
        this.mUi.updateHue();
    }

    protected void onStop() {
        this.mp.mBtVisible = false;
        super.onStop();
    }

    public void postBusyShow() {
        int busy = this.mBtDevice.getBusy();
        if ((busy & 1) != 0) {
            Toast.makeText(this, R.string.busy_loadingphonebook, 0).show();
        } else if ((busy & 2) != 0) {
            Toast.makeText(this, R.string.busy_scanningdevice, 0).show();
        } else if ((busy & 4) != 0) {
            Toast.makeText(this, R.string.busy_talking, 0).show();
        }
    }

    public void scanStart() {
        if (this.mBtDevice == null) {
            return;
        }
        if (this.mBtDevice.getBusy() == 0) {
            this.mBtDevice.scanStart();
        } else {
            postBusyShow();
        }
    }

    public void scanStop() {
        if (this.mBtDevice == null) {
            return;
        }
        if ((this.mBtDevice.getBusy() & 2) != 0) {
            this.mBtDevice.scanStop();
        } else {
            postBusyShow();
        }
    }

    public void searchKeyboard() {
        this.mUi.searchpan();
    }

    public boolean setAutoAnswer() {
        return this.mBtDevice != null ? this.mBtDevice.switchAutoAnswer() : false;
    }

    public boolean setAutoConnect() {
        return this.mBtDevice != null ? this.mBtDevice.switchAutoConnect() : false;
    }

    public void setBusyShow(boolean z) {
        this.mUi.setBusyShow(z);
    }

    public void setDeviceName(String str) {
        if (this.serviceConnectFlag && this.mBtDevice != null) {
            this.mBtDevice.setDeviceName(str);
        }
    }

    public void setPassword(String str) {
        if (this.serviceConnectFlag && this.mBtDevice != null) {
            this.mBtDevice.setPassword(str);
        }
    }

    public void switchPhoneVoice() {
        if (this.mBtDevice != null && this.mBtDevice.getBTState() == 5) {
            this.mBtDevice.switchPhoneVoice();
        }
    }

    public void syncMatchList(boolean z) {
        if (this.mBtDevice != null) {
            this.mBtDevice.syncMatchList(z);
        }
    }

    public void syncPhonebook() {
        if (this.mBtDevice.getBusy() == 0) {
            this.mBtDevice.syncPhonebook();
            this.mUi.setBusyShow(true);
            return;
        }
        postBusyShow();
    }

    public void updateHistoryList() {
        this.mBtDevice.updateHistoryList();
    }
}
