package com.microntek.bluetooth.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.microntek.bluetooth.BlueToothInterface;
import com.microntek.bluetooth.R;

import java.util.ArrayList;
import java.util.List;

public class MatchFragment extends Fragment implements OnClickListener, OnChildClickListener {
    private MatchAdapter mAdapter;
    private ExpandableListView mListView;
    private boolean mShowDialog;
    private BlueToothInterface matchinterface;

    /* renamed from: com.microntek.bluetooth.ui.MatchFragment.1 */
    class C00151 implements DialogInterface.OnClickListener {
        C00151() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            MatchFragment.this.connectDevice();
        }
    }

    /* renamed from: com.microntek.bluetooth.ui.MatchFragment.2 */
    class C00162 implements DialogInterface.OnClickListener {
        C00162() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            MatchFragment.this.disconnect();
        }
    }

    class MatchAdapter extends BaseExpandableListAdapter {
        private List<List<String>> childArray;
        private List<String> groupArray;
        private Context mContext;
        private int mCurChild;
        private int mCurGroup;
        private LayoutInflater mInflater;

        public MatchAdapter(Context context) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
            this.groupArray = new ArrayList();
            this.childArray = new ArrayList();
            this.groupArray.add(this.mContext.getString(R.string.matchdevice));
            this.groupArray.add(this.mContext.getString(R.string.searchdevice));
            this.childArray.add(MatchFragment.this.matchinterface.getMatchList());
            this.childArray.add(MatchFragment.this.matchinterface.getDeviceList());
            this.mCurGroup = 0;
            this.mCurChild = -1;
        }

        private TextView getGenericView(String str, boolean z) {
            LayoutParams layoutParams = new AbsListView.LayoutParams(-1, -2);
            TextView textView = new TextView(this.mContext);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(19);
            textView.setPadding(20, 0, 0, 0);
            textView.setText(str);
            if (z) {
                textView.setTextColor(-1);
                textView.setBackgroundResource(R.drawable.match_titlebg);
                textView.setClickable(true);
            }
            return textView;
        }

        public Object getChild() {
            return getChild(this.mCurGroup, this.mCurChild);
        }

        public Object getChild(int i, int i2) {
            return (i >= this.childArray.size() || i < 0) ? null : (i2 >= ((List) this.childArray.get(i)).size() || i2 < 0) ? null : ((List) this.childArray.get(i)).get(i2);
        }

        public long getChildId(int i, int i2) {
            return (long) i2;
        }

        public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            CharSequence charSequence;
            String obj;
            if (view == null) {
                view = this.mInflater.inflate(R.layout.matchlist, null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.mobile_icon);
            TextView textView = (TextView) view.findViewById(R.id.mobile_name);
            TextView textView2 = (TextView) view.findViewById(R.id.mobile_macaddr);
            String str = (String) getChild(i, i2);
            long j = 0;
            if (str != null) {
                String substring = str.substring(12);
                str = str.substring(0, 12);
                j = Long.parseLong(str, 16);
                String str2 = substring;
                charSequence = str;
                obj = str2;
            } else {
                obj = null;
                charSequence = null;
            }
            Log.i("LIU", "name |" + obj + "|" + charSequence);
            textView.setText((String)obj);
            textView2.setText(charSequence);
            boolean isOBDDevice = MatchFragment.this.matchinterface.isOBDDevice(obj);
            if (isOBDDevice) {
                imageView.setBackgroundResource(R.drawable.match_iconobd);
            } else {
                imageView.setBackgroundResource(R.drawable.match_iconphone);
            }
            if (i != 0) {
                textView.setTextColor(-1);
                textView2.setTextColor(-1);
            } else if (MatchFragment.this.matchinterface.getConnectPhoneMACaddr() == j || isOBDDevice) {
                textView.setTextColor(-16711936);
                textView2.setTextColor(-16711936);
            } else {
                textView.setTextColor(-1);
                textView2.setTextColor(-1);
            }
            if (i == this.mCurGroup && i2 == this.mCurChild) {
                view.findViewById(R.id.iv_matchbg).setVisibility(View.VISIBLE);
            } else {
                view.findViewById(R.id.iv_matchbg).setVisibility(View.INVISIBLE);
            }
            return view;
        }

        public int getChildrenCount(int i) {
            return ((List) this.childArray.get(i)).size();
        }

        public Object getGroup(int i) {
            return this.groupArray.get(i);
        }

        public int getGroupCount() {
            return this.groupArray.size();
        }

        public long getGroupId(int i) {
            return (long) i;
        }

        public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
            return getGenericView((String) this.groupArray.get(i), true);
        }

        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int i, int i2) {
            return true;
        }

        public void setSelectItem(int i, int i2) {
            this.mCurGroup = i;
            this.mCurChild = i2;
            notifyDataSetChanged();
        }
    }

    public MatchFragment() {
        this.matchinterface = null;
        this.mShowDialog = false;
    }

    private void connectDevice() {
        String str = (String) this.mAdapter.getChild();
        if (str != null) {
            this.matchinterface.connectDevice(str);
        }
    }

    private void delete() {
        String str = (String) this.mAdapter.getChild();
        if (str != null) {
            this.matchinterface.deleteDevice(str);
        }
    }

    private void disconnect() {
        String str = (String) this.mAdapter.getChild();
        if (str != null) {
            this.matchinterface.disconnectDevice(str);
        }
    }

    private void init() {
        if (this.matchinterface != null) {
            this.mAdapter = new MatchAdapter(getActivity());
            this.mListView.setAdapter(this.mAdapter);
            int count = this.mListView.getCount();
            for (int i = 0; i < count; i++) {
                this.mListView.expandGroup(i);
            }
            this.mListView.setGroupIndicator(null);
            updateView();
        }
    }

    private void scanDevice() {
        if (this.matchinterface != null) {
            int busy = this.matchinterface.getBusy();
            if (busy == 0) {
                this.matchinterface.setBusyShow(true);
                this.matchinterface.scanStart();
                this.mAdapter.notifyDataSetChanged();
            } else if ((busy & 2) != 0) {
                this.matchinterface.scanStop();
            } else {
                this.matchinterface.postBusyShow();
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        String parameters = ((AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE)).getParameters("sta_mcu_version=");
        if (parameters != null && parameters.startsWith("MTCB-RM-")) {
            this.mShowDialog = true;
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.matchinterface = (BlueToothInterface) activity;
        } catch (Exception e) {
        }
    }

    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
        this.mAdapter.setSelectItem(i, i2);
        return false;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.matchsearch:
                scanDevice();
            case R.id.matchconnect:
                if (this.mShowDialog) {
                    new Builder(getActivity()).setTitle(R.string.bluetooth_device_context_connect).setPositiveButton(android.R.string.yes, new C00151()).setNegativeButton(android.R.string.no, null).setCancelable(false).show();
                } else {
                    connectDevice();
                }
            case R.id.matchdisconnect:
                if (this.mShowDialog) {
                    new Builder(getActivity()).setTitle(R.string.bluetooth_device_context_disconnect).setPositiveButton(android.R.string.yes, new C00162()).setNegativeButton(android.R.string.no, null).setCancelable(false).show();
                } else {
                    disconnect();
                }
            case R.id.matchdelete:
                delete();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.match, viewGroup, false);
        this.mListView = (ExpandableListView) inflate.findViewById(R.id.matchlist);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.matchconnect);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(R.id.matchdisconnect);
        ImageButton imageButton3 = (ImageButton) inflate.findViewById(R.id.matchdelete);
        ((ImageButton) inflate.findViewById(R.id.matchsearch)).setOnClickListener(this);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        this.mListView.setOnChildClickListener(this);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        init();
    }

    public void updateView() {
        if (this.matchinterface != null && this.matchinterface.getServiceState() && this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
