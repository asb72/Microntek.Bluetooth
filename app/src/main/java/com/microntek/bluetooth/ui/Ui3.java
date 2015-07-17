package com.microntek.bluetooth.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.microntek.bluetooth.BlueToothInterface;
import com.microntek.bluetooth.R;
import com.microntek.bluetooth.UiBase;
import java.util.List;

public class Ui3 extends UiBase implements OnClickListener {
    private DialFragment dialFragment;
    private HistoryFragment historyFragment;
    private keyboardDialog kbDialog;
    private ProgressBar mBusyShow;
    private MatchFragment matchFragment;
    private MusicFragment musicFragment;
    private PhonebookFragment phonebookFragment;
    private ImageButton pubdial;
    private ImageButton pubhistory;
    private ImageButton pubmatch;
    private ImageButton pubmusic;
    private ImageButton pubphonebook;
    private ImageButton pubsetup;
    private SetupFragment setupFragment;

    private class keyboardDialog extends AlertDialog {
        public keyboardDialog(Context context) {
            super(context, 2);
        }
    }

    public Ui3(FragmentActivity fragmentActivity) {
        this.mActivity = fragmentActivity;
        try {
            this.mFunc = (BlueToothInterface) fragmentActivity;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragmentActivity.toString() + "Must implement BlueToothInterface");
        }
    }

    private void postKeyboadrShow() {
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.keyboard, null);
        this.kbDialog = new keyboardDialog(this.mActivity);
        this.kbDialog.setView(inflate);
        List phoneFirstChar = this.mFunc.getPhoneFirstChar();
        int size = phoneFirstChar.size();
        int i = 0;
        int i2 = 27;
        while (i < size) {
            int charValue = ((Character) phoneFirstChar.get(i)).charValue() - 65;
            if (charValue >= 27 || charValue == i2 || charValue < 0) {
                charValue = i2;
            } else {
                inflate.findViewById(R.id.keya + charValue).setEnabled(true);
                inflate.findViewById(R.id.keya + charValue).setOnClickListener(this);
            }
            i++;
            i2 = charValue;
        }
        this.kbDialog.show();
        this.kbDialog.getWindow().setLayout((int) this.mActivity.getResources().getDimension(2131034114), (int) this.mActivity.getResources().getDimension(2131034115));
    }

    public void DialAnswer() {
        this.dialFragment.dialAnswer();
    }

    public void SwitchToPage(int i) {
        if (this.mPage != i && i > 0) {
            int i2 = this.mPage;
            this.mPage = i;
            FragmentTransaction beginTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
            if (i2 == -1) {
                beginTransaction.setTransition(0);
            } else {
                beginTransaction.setCustomAnimations(2130968576, 2130968577);
            }
            switch (this.mPage) {
                case 1:
                    if (this.dialFragment == null) {
                        this.dialFragment = new DialFragment();
                    }
                    beginTransaction.replace(R.id.content_frame, this.dialFragment).commitAllowingStateLoss();
                    break;
                case 2:
                    if (this.phonebookFragment == null) {
                        this.phonebookFragment = new PhonebookFragment();
                    }
                    beginTransaction.replace(R.id.content_frame, this.phonebookFragment).commitAllowingStateLoss();
                    break;
                case 3:
                    if (this.historyFragment == null) {
                        this.historyFragment = new HistoryFragment();
                    }
                    beginTransaction.replace(R.id.content_frame, this.historyFragment).commitAllowingStateLoss();
                    break;
                case 4:
                    if (this.matchFragment == null) {
                        this.matchFragment = new MatchFragment();
                    }
                    beginTransaction.replace(R.id.content_frame, this.matchFragment).commitAllowingStateLoss();
                    break;
                case 5:
                    if (this.musicFragment == null) {
                        this.musicFragment = new MusicFragment();
                    }
                    beginTransaction.replace(R.id.content_frame, this.musicFragment).commitAllowingStateLoss();
                    break;
                case 6:
                    if (this.setupFragment == null) {
                        this.setupFragment = new SetupFragment();
                    }
                    beginTransaction.replace(R.id.content_frame, this.setupFragment).commitAllowingStateLoss();
                    break;
                default:
                    return;
            }
            if (this.mPage != 1) {
                this.pubdial.setSelected(false);
            } else {
                this.pubdial.setSelected(true);
            }
            if (this.mPage != 2) {
                this.pubphonebook.setSelected(false);
            } else {
                this.pubphonebook.setSelected(true);
            }
            if (this.mPage != 3) {
                this.pubhistory.setSelected(false);
            } else {
                this.pubhistory.setSelected(true);
            }
            if (this.mPage != 4) {
                this.pubmatch.setSelected(false);
            } else {
                this.pubmatch.setSelected(true);
            }
            if (this.mPage != 5) {
                this.pubmusic.setSelected(false);
            } else {
                this.pubmusic.setSelected(true);
            }
            if (this.mPage != 6) {
                this.pubsetup.setSelected(false);
            } else {
                this.pubsetup.setSelected(true);
            }
        }
    }

    public void clearDialNumbers() {
        this.dialFragment.clearDialNumbers();
    }

    public int getLayout() {
        return R.layout.main;
    }

    public int getUiType() {
        return 0;
    }

    public void init() {
        this.mBusyShow = (ProgressBar) this.mActivity.findViewById(R.id.busyshow);
        this.pubdial = (ImageButton) this.mActivity.findViewById(R.id.pub_dial);
        this.pubphonebook = (ImageButton) this.mActivity.findViewById(R.id.pub_phonebook);
        this.pubhistory = (ImageButton) this.mActivity.findViewById(R.id.pub_history);
        this.pubmatch = (ImageButton) this.mActivity.findViewById(R.id.pub_match);
        this.pubmusic = (ImageButton) this.mActivity.findViewById(R.id.pub_music);
        this.pubsetup = (ImageButton) this.mActivity.findViewById(R.id.pub_setup);
        this.pubdial.setEnabled(false);
        this.pubphonebook.setEnabled(false);
        this.pubhistory.setEnabled(false);
        this.pubmusic.setEnabled(false);
        this.pubdial.setOnClickListener(this);
        this.pubphonebook.setOnClickListener(this);
        this.pubhistory.setOnClickListener(this);
        this.pubmatch.setOnClickListener(this);
        this.pubmusic.setOnClickListener(this);
        this.pubsetup.setOnClickListener(this);
    }

    public void onClick(View view) {
        int bTState;
        switch (view.getId()) {
            case R.id.keya:
            case R.id.keyb:
            case R.id.keyc:
            case R.id.keyd:
            case R.id.keye:
            case R.id.keyf:
            case R.id.keyg:
            case R.id.keyh:
            case R.id.keyi:
            case R.id.keyj:
            case R.id.keyk:
            case R.id.keyl:
            case R.id.keym:
            case R.id.keyn:
            case R.id.keyo:
            case R.id.keyp:
            case R.id.keyq:
            case R.id.keyr:
            case R.id.keys:
            case R.id.keyt:
            case R.id.keyu:
            case R.id.keyv:
            case R.id.keyw:
            case R.id.keyx:
            case R.id.keyy:
            case R.id.keyz:
                this.phonebookFragment.search(((TextView) view).getText().charAt(0));
                this.kbDialog.dismiss();
            case R.id.pub_dial:
                SwitchToPage(1);
                this.mBusyShow.setVisibility(View.INVISIBLE);
            case R.id.pub_phonebook:
                bTState = this.mFunc.getBTState();
                if (bTState != 3 && bTState != 2 && bTState != 5) {
                    SwitchToPage(2);
                    if ((this.mFunc.getBusy() & 1) != 0) {
                        this.mBusyShow.setVisibility(View.VISIBLE);
                    } else {
                        this.mBusyShow.setVisibility(View.INVISIBLE);
                    }
                }
            case R.id.pub_history:
                bTState = this.mFunc.getBTState();
                if (bTState != 3 && bTState != 2 && bTState != 5) {
                    SwitchToPage(3);
                    this.mBusyShow.setVisibility(View.INVISIBLE);
                }
            case R.id.pub_match:
                bTState = this.mFunc.getBTState();
                if (bTState != 3 && bTState != 2 && bTState != 5) {
                    this.mFunc.syncMatchList(false);
                    SwitchToPage(4);
                    if ((this.mFunc.getBusy() & 2) != 0) {
                        this.mBusyShow.setVisibility(View.VISIBLE);
                    } else {
                        this.mBusyShow.setVisibility(View.INVISIBLE);
                    }
                }
            case R.id.pub_music:
                bTState = this.mFunc.getBTState();
                if (bTState != 3 && bTState != 2 && bTState != 5) {
                    SwitchToPage(5);
                    this.mBusyShow.setVisibility(View.INVISIBLE);
                }
            case R.id.pub_setup:
                bTState = this.mFunc.getBTState();
                if (bTState != 3 && bTState != 2 && bTState != 5) {
                    SwitchToPage(6);
                    this.mBusyShow.setVisibility(View.INVISIBLE);
                }
            default:
        }
    }

    public void searchpan() {
        postKeyboadrShow();
    }

    public void setBusyShow(boolean z) {
        if (z) {
            this.mBusyShow.setVisibility(View.VISIBLE);
        } else {
            this.mBusyShow.setVisibility(View.INVISIBLE);
        }
    }

    public void updateBtStatus() {
        if (this.mFunc.getBTState() == 0) {
            this.pubdial.setEnabled(false);
            this.pubhistory.setEnabled(false);
            this.pubphonebook.setEnabled(false);
            this.pubmusic.setEnabled(false);
        } else {
            if (this.mFunc.getAVState() == 0) {
                this.pubmusic.setEnabled(false);
            } else {
                this.pubmusic.setEnabled(true);
            }
            this.pubdial.setEnabled(true);
            this.pubhistory.setEnabled(true);
            this.pubphonebook.setEnabled(true);
        }
        updatePageView();
    }

    public void updateHue() {
    }

    public void updatePageView() {
        if (this.mPage == 4) {
            this.matchFragment.updateView();
        } else if (this.mPage == 1) {
            this.dialFragment.updateView();
        } else if (this.mPage == 2) {
            this.phonebookFragment.updateView();
        } else if (this.mPage == 3) {
            this.historyFragment.updateView();
        }
    }

    public void updateTalkingTime(int i) {
        if (this.dialFragment != null) {
            this.dialFragment.updateTalkingTime(i);
        }
    }
}
