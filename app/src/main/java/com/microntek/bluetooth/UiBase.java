package com.microntek.bluetooth;

import android.support.v4.app.FragmentActivity;

public abstract class UiBase {
    protected FragmentActivity mActivity;
    protected BlueToothInterface mFunc;
    public int mPage;

    public UiBase() {
        this.mPage = -1;
    }

    public abstract void DialAnswer();

    public abstract void SwitchToPage(int i);

    public abstract void clearDialNumbers();

    public abstract int getLayout();

    public abstract int getUiType();

    public abstract void init();

    public abstract void searchpan();

    public abstract void setBusyShow(boolean z);

    public abstract void updateBtStatus();

    public abstract void updateHue();

    public abstract void updatePageView();

    public abstract void updateTalkingTime(int i);
}
