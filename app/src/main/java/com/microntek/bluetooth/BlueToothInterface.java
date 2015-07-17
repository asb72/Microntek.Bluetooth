package com.microntek.bluetooth;

import java.util.List;

public interface BlueToothInterface {
    void answerCall();

    void connectDevice(String str);

    void deleteDevice(String str);

    void deleteHistory(int i);

    void deleteHistoryAll();

    void dialOut(char c);

    void dialOut(String str);

    void disconnectDevice(String str);

    int getAVState();

    boolean getAutoAnswer();

    boolean getAutoConnect();

    int getBTState();

    int getBusy();

    long getConnectPhoneMACaddr();

    List<String> getDeviceList();

    String getDeviceName();

    String getDialOutNumbers();

    List<String> getMatchList();

    String getNameOfNumbers(String str);

    String getPassword();

    List<Character> getPhoneFirstChar();

    List<String> getPhoneHistoryList();

    List<String> getPhoneList();

    String getPhoneNumbers();

    boolean getServiceState();

    void hangupCall();

    boolean isOBDDevice(String str);

    void musicNext();

    void musicPlayPause();

    void musicPrev();

    void postBusyShow();

    void scanStart();

    void scanStop();

    void searchKeyboard();

    boolean setAutoAnswer();

    boolean setAutoConnect();

    void setBusyShow(boolean z);

    void setDeviceName(String str);

    void setPassword(String str);

    void switchPhoneVoice();

    void syncMatchList(boolean z);

    void syncPhonebook();

    void updateHistoryList();
}
