package com.microntek.bluetooth.ui;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.microntek.bluetooth.R;
import com.microntek.bluetooth.notification.MainApplication;
import java.text.MessageFormat;

public class btNotificationView extends FrameLayout {
    private OnClickListener btnClick;
    private View kblLayout;
    private Context mContext;
    private long mDialTime;
    private TextView mTalkTime;
    private MainApplication mp;
    private TextView numberreport;
    private ImageView statereport;

    /* renamed from: com.microntek.bluetooth.ui.btNotificationView.1 */
    class C00211 implements OnClickListener {
        C00211() {
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.num1:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 1);
                case R.id.num2:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 2);
                case R.id.num3:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 3);
                case R.id.num4:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 4);
                case R.id.num5:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 5);
                case R.id.num6:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 6);
                case R.id.num7:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 7);
                case R.id.num8:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 8);
                case R.id.num9:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 9);
                case R.id.num0:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 0);
                case R.id.numx:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 10);
                case R.id.numj:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 11);
                case R.id.kbswitch:
                    if (btNotificationView.this.kblLayout.getVisibility() == 0) {
                        btNotificationView.this.kblLayout.setVisibility(View.GONE);
                    } else {
                        btNotificationView.this.kblLayout.setVisibility(View.VISIBLE);
                    }
                case R.id.voiceswitch:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 18);
                case R.id.dial:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 16);
                case R.id.handup:
                    btNotificationView.this.mp.report(btNotificationView.this.mContext, (byte) 17);
                default:
            }
        }
    }

    public btNotificationView(Context context) {
        super(context);
        this.mDialTime = 0;
        this.btnClick = new C00211();
        this.mContext = context;
        this.mp = (MainApplication) this.mContext.getApplicationContext();
        View inflate = View.inflate(context, R.layout.notification, null);
        ((ImageButton) inflate.findViewById(R.id.voiceswitch)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.dial)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.handup)).setOnClickListener(this.btnClick);
        this.statereport = (ImageView) inflate.findViewById(R.id.btstate);
        this.numberreport = (TextView) inflate.findViewById(R.id.btnumber);
        this.kblLayout = inflate.findViewById(R.id.keyboardext);
        ((ImageButton) inflate.findViewById(R.id.kbswitch)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num0)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num0)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num1)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num2)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num3)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num4)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num5)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num6)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num7)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num8)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.num9)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.numx)).setOnClickListener(this.btnClick);
        ((ImageButton) inflate.findViewById(R.id.numj)).setOnClickListener(this.btnClick);
        this.mTalkTime = (TextView) inflate.findViewById(R.id.dialtime);
        addView(inflate);
    }

    private String getTimeFormatValue(long j) {
        return MessageFormat.format("{0,number,00}:{1,number,00}:{2,number,00}", new Object[]{Long.valueOf((j / 60) / 60), Long.valueOf((j / 60) % 60), Long.valueOf(j % 60)});
    }

    public void updateNumber(String str) {
        this.numberreport.setText(str);
    }

    public void updateStatus(int i) {
        this.mTalkTime.setVisibility(View.INVISIBLE);
        if (i == 2) {
            this.statereport.getBackground().setLevel(1);
        } else if (i == 3) {
            this.statereport.getBackground().setLevel(0);
        } else if (i == 5) {
            this.mTalkTime.setText("");
            this.mTalkTime.setVisibility(View.VISIBLE);
            this.statereport.getBackground().setLevel(2);
        }
    }

    public void updateTalkingTime(int i) {
        this.mTalkTime.setText(getTimeFormatValue((long) i));
    }
}
