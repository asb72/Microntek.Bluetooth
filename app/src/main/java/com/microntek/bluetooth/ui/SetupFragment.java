package com.microntek.bluetooth.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.microntek.bluetooth.BlueToothInterface;
import com.microntek.bluetooth.R;

public class SetupFragment extends Fragment implements OnClickListener {
    private ImageView autoAnswer;
    private ImageView autoConnect;
    private EditText btName;
    private EditText btPassword;
    private OnEditorActionListener editorAction;
    private BlueToothInterface setupinterface;

    /* renamed from: com.microntek.bluetooth.ui.SetupFragment.1 */
    class C00201 implements OnEditorActionListener {
        C00201() {
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6 && SetupFragment.this.setupinterface != null) {
                SetupFragment.this.setupinterface.setDeviceName(SetupFragment.this.btName.getText().toString());
                SetupFragment.this.setupinterface.setPassword(SetupFragment.this.btPassword.getText().toString());
            }
            return false;
        }
    }

    public SetupFragment() {
        this.setupinterface = null;
        this.editorAction = new C00201();
    }

    private void init() {
        int i = 0;
        if (this.setupinterface != null) {
            this.btName.setText(this.setupinterface.getDeviceName());
            this.btPassword.setText(this.setupinterface.getPassword());
            this.autoAnswer.getBackground().setLevel(this.setupinterface.getAutoAnswer() ? 1 : 0);
            Drawable background = this.autoConnect.getBackground();
            if (this.setupinterface.getAutoConnect()) {
                i = 1;
            }
            background.setLevel(i);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.setupinterface = (BlueToothInterface) activity;
        } catch (Exception e) {
        }
    }

    public void onClick(View view) {
        int i = 0;
        boolean autoAnswer;
        Drawable background;
        switch (view.getId()) {
            case R.id.autoanswercheck:
                if (this.setupinterface != null) {
                    autoAnswer = this.setupinterface.setAutoAnswer();
                    background = view.getBackground();
                    if (autoAnswer) {
                        i = 1;
                    }
                    background.setLevel(i);
                }
            case R.id.autoconnectcheck:
                if (this.setupinterface != null) {
                    autoAnswer = this.setupinterface.setAutoConnect();
                    background = view.getBackground();
                    if (autoAnswer) {
                        i = 1;
                    }
                    background.setLevel(i);
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.setup, viewGroup, false);
        this.btName = (EditText) inflate.findViewById(R.id.btname);
        this.btPassword = (EditText) inflate.findViewById(R.id.btpinpassword);
        this.autoAnswer = (ImageView) inflate.findViewById(R.id.autoanswercheck);
        this.autoConnect = (ImageView) inflate.findViewById(R.id.autoconnectcheck);
        this.btName.setOnEditorActionListener(this.editorAction);
        this.btPassword.setOnEditorActionListener(this.editorAction);
        this.autoAnswer.setOnClickListener(this);
        this.autoConnect.setOnClickListener(this);
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onResume() {
        super.onResume();
        init();
    }
}
