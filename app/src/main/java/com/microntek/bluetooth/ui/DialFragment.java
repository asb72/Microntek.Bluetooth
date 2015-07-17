package com.microntek.bluetooth.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.microntek.bluetooth.BlueToothInterface;
import com.microntek.bluetooth.R;

import java.text.MessageFormat;

public class DialFragment extends Fragment implements OnClickListener, OnLongClickListener {
    private BlueToothInterface dialinterface;
    private StringBuffer dialoutNumbers;
    private TextView numberText;
    private ImageView phoneState;
    private TextView timeText;

    public DialFragment() {
        this.dialinterface = null;
        this.dialoutNumbers = new StringBuffer(32);
    }

    private String getTimeFormatValue(long j) {
        return MessageFormat.format("{0,number,00}:{1,number,00}:{2,number,00}", new Object[]{Long.valueOf((j / 60) / 60), Long.valueOf((j / 60) % 60), Long.valueOf(j % 60)});
    }

    private void init() {
        if (this.dialinterface != null) {
            updateView();
        }
    }

    private void pressBack() {
        if (this.dialinterface != null && this.dialinterface.getBTState() == 1 && this.dialoutNumbers.length() > 0) {
            this.dialoutNumbers.deleteCharAt(this.dialoutNumbers.length() - 1);
            updateDialNumberShow(this.dialoutNumbers.toString());
        }
    }

    private void pressNumber(char c) {
        if (this.dialinterface != null) {
            int bTState = this.dialinterface.getBTState();
            if (bTState != 3 && bTState != 2) {
                if (bTState == 1 && this.dialoutNumbers.length() > 24) {
                    return;
                }
                if (bTState == 5) {
                    this.dialinterface.dialOut(c);
                    return;
                }
                this.dialoutNumbers.append(c);
                updateDialNumberShow(this.dialoutNumbers.toString());
            }
        }
    }

    public void clearDialNumbers() {
        if (this.dialinterface != null) {
            try {
                this.dialoutNumbers.delete(0, this.dialoutNumbers.capacity());
            } catch (Exception e) {
            }
            updateDialNumberShow("");
        }
    }

    public void dialAnswer() {
        if (this.dialinterface != null) {
            int bTState = this.dialinterface.getBTState();
            if (bTState == 1) {
                if (this.dialoutNumbers.length() > 0) {
                    this.dialinterface.dialOut(this.dialoutNumbers.toString());
                    updateDialNumberShow(this.dialinterface.getNameOfNumbers(this.dialoutNumbers.toString()));
                } else if (this.dialinterface.getDialOutNumbers() != null) {
                    this.dialoutNumbers.append(this.dialinterface.getDialOutNumbers());
                    updateDialNumberShow(this.dialoutNumbers.toString());
                }
            } else if (bTState == 3) {
                this.dialinterface.answerCall();
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.dialinterface = (BlueToothInterface) activity;
        } catch (Exception e) {
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dial_back:
                pressBack();
            case R.id.dial_num1:
                pressNumber('1');
            case R.id.dial_num2:
                pressNumber('2');
            case R.id.dial_num3:
                pressNumber('3');
            case R.id.dial_num4:
                pressNumber('4');
            case R.id.dial_num5:
                pressNumber('5');
            case R.id.dial_num6:
                pressNumber('6');
            case R.id.dial_num7:
                pressNumber('7');
            case R.id.dial_num8:
                pressNumber('8');
            case R.id.dial_num9:
                pressNumber('9');
            case R.id.dial_numx:
                pressNumber('*');
            case R.id.dial_num0:
                pressNumber('0');
            case R.id.dial_nums:
                pressNumber('#');
            case R.id.dial_dialout:
                dialAnswer();
            case R.id.dial_handup:
                if (this.dialinterface != null) {
                    this.dialinterface.hangupCall();
                }
            case R.id.dial_voiceswitch:
                if (this.dialinterface != null) {
                    this.dialinterface.switchPhoneVoice();
                }
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.dial, viewGroup, false);
        this.numberText = (TextView) inflate.findViewById(R.id.dial_numbers);
        this.phoneState = (ImageView) inflate.findViewById(R.id.dial_status);
        this.timeText = (TextView) inflate.findViewById(R.id.dial_time);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.dial_back);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.dial_dialout);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(R.id.dial_handup);
        ImageButton imageButton3 = (ImageButton) inflate.findViewById(R.id.dial_voiceswitch);
        ImageButton imageButton4 = (ImageButton) inflate.findViewById(R.id.dial_num0);
        ImageButton imageButton5 = (ImageButton) inflate.findViewById(R.id.dial_num1);
        ImageButton imageButton6 = (ImageButton) inflate.findViewById(R.id.dial_num2);
        ImageButton imageButton7 = (ImageButton) inflate.findViewById(R.id.dial_num3);
        ImageButton imageButton8 = (ImageButton) inflate.findViewById(R.id.dial_num4);
        ImageButton imageButton9 = (ImageButton) inflate.findViewById(R.id.dial_num5);
        ImageButton imageButton10 = (ImageButton) inflate.findViewById(R.id.dial_num6);
        ImageButton imageButton11 = (ImageButton) inflate.findViewById(R.id.dial_num7);
        ImageButton imageButton12 = (ImageButton) inflate.findViewById(R.id.dial_num8);
        ImageButton imageButton13 = (ImageButton) inflate.findViewById(R.id.dial_num9);
        ImageButton imageButton14 = (ImageButton) inflate.findViewById(R.id.dial_numx);
        ImageButton imageButton15 = (ImageButton) inflate.findViewById(R.id.dial_nums);
        imageView.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        imageButton5.setOnClickListener(this);
        imageButton6.setOnClickListener(this);
        imageButton7.setOnClickListener(this);
        imageButton8.setOnClickListener(this);
        imageButton9.setOnClickListener(this);
        imageButton10.setOnClickListener(this);
        imageButton11.setOnClickListener(this);
        imageButton12.setOnClickListener(this);
        imageButton13.setOnClickListener(this);
        imageButton14.setOnClickListener(this);
        imageButton15.setOnClickListener(this);
        imageButton4.setOnLongClickListener(this);
        imageView.setOnLongClickListener(this);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.dial_back:
                if (this.dialinterface.getBTState() <= 1) {
                    clearDialNumbers();
                    break;
                }
                break;
            case R.id.dial_num0:
                pressNumber('+');
                return true;
        }
        return false;
    }

    public void onResume() {
        super.onResume();
        init();
    }

    public void updateDialNumberShow(String str) {
        if (this.dialinterface != null && this.numberText != null && str != null) {
            if (str.length() > 14) {
                this.numberText.setText(str.substring(str.length() - 14));
            } else {
                this.numberText.setText(str);
            }
        }
    }

    public void updateTalkingTime(int i) {
        if (this.timeText != null) {
            this.timeText.setText(getTimeFormatValue((long) i));
        }
    }

    public void updateView() {
        if (this.dialinterface != null) {
            int bTState = this.dialinterface.getBTState();
            String phoneNumbers = this.dialinterface.getPhoneNumbers();
            if (bTState == 5) {
                this.phoneState.getBackground().setLevel(2);
                this.phoneState.setVisibility(View.VISIBLE);
                this.timeText.setVisibility(View.VISIBLE);
                updateDialNumberShow(this.dialinterface.getNameOfNumbers(phoneNumbers));
            } else if (bTState == 3) {
                this.phoneState.getBackground().setLevel(0);
                this.phoneState.setVisibility(View.VISIBLE);
                updateDialNumberShow(this.dialinterface.getNameOfNumbers(phoneNumbers));
            } else if (bTState == 2) {
                this.phoneState.getBackground().setLevel(1);
                this.phoneState.setVisibility(View.VISIBLE);
                updateDialNumberShow(this.dialinterface.getNameOfNumbers(phoneNumbers));
            } else {
                this.phoneState.setVisibility(View.INVISIBLE);
                this.timeText.setText("");
                this.timeText.setVisibility(View.INVISIBLE);
            }
        }
    }
}
