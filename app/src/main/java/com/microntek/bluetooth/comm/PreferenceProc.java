package com.microntek.bluetooth.comm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import java.util.List;

public class PreferenceProc {
    boolean hasDeleteHistoryFlag;
    private Context mContext;
    private SharedPreferences mPreferences;

    public PreferenceProc(Context context) {
        this.hasDeleteHistoryFlag = false;
        this.mContext = context;
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(this.mContext);
    }

    public void loadPhoneBook(long j, List<String> list) {
        int i = 0;
        String toHexString = Long.toHexString(j);
        String str = "DirSize" + toHexString;
        list.clear();
        int i2 = this.mPreferences.getInt(str, 0);
        while (i < i2) {
            String string = this.mPreferences.getString("DirItem" + toHexString + i, null);
            if (string != null) {
                list.add(string);
            }
            i++;
        }
    }

    public void savePhoneBook(long j, List<String> list) {
        String toHexString = Long.toHexString(j);
        String str = "DirSize" + toHexString;
        Editor edit = this.mPreferences.edit();
        int size = list.size();
        edit.putInt(str, size);
        for (int i = 0; i < size; i++) {
            edit.putString("DirItem" + toHexString + i, (String) list.get(i));
        }
        edit.commit();
    }
}
