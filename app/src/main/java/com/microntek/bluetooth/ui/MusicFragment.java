package com.microntek.bluetooth.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.microntek.bluetooth.BlueToothInterface;
import com.microntek.bluetooth.R;

public class MusicFragment extends Fragment implements OnClickListener {
    private BlueToothInterface musicinterface;

    public MusicFragment() {
        this.musicinterface = null;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.musicinterface = (BlueToothInterface) activity;
        } catch (Exception e) {
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.music_pre:
                if (this.musicinterface != null) {
                    this.musicinterface.musicPrev();
                }
            case R.id.music_play:
                if (this.musicinterface != null) {
                    this.musicinterface.musicPlayPause();
                }
            case R.id.music_next:
                if (this.musicinterface != null) {
                    this.musicinterface.musicNext();
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.music, viewGroup, false);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.music_play);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(R.id.music_next);
        ((ImageButton) inflate.findViewById(R.id.music_pre)).setOnClickListener(this);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        return inflate;
    }
}
