package com.microntek.bluetooth.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.microntek.bluetooth.BlueToothInterface;
import com.microntek.bluetooth.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements OnClickListener {
    private BlueToothInterface historyinterface;
    private ImageView inCallView;
    private OnItemClickListener listClick;
    private HistoryAdapter mAdapter;
    private ListView mListView;
    private ImageView outCallView;
    private ImageView rejectView;

    /* renamed from: com.microntek.bluetooth.ui.HistoryFragment.1 */
    class C00141 implements OnItemClickListener {
        C00141() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            HistoryFragment.this.mAdapter.setSelectItem(i);
        }
    }

    private class HistoryAdapter extends BaseAdapter {
        private int filterType;
        private List<Integer> listMapIndex;
        private LayoutInflater mInflater;
        private List<String> mList;
        private int seleteItem;

        public HistoryAdapter(Context context) {
            this.seleteItem = 0;
            this.filterType = 1;
            this.mInflater = LayoutInflater.from(context);
            this.seleteItem = 0;
            this.mList = new ArrayList();
            this.listMapIndex = new ArrayList();
        }

        private void smoothToCenter() {
            int firstVisiblePosition = HistoryFragment.this.mListView.getFirstVisiblePosition() + 2;
            if (this.seleteItem > firstVisiblePosition) {
                HistoryFragment.this.mListView.smoothScrollToPosition(this.seleteItem + 2);
            } else if (this.seleteItem <= firstVisiblePosition) {
                HistoryFragment.this.mListView.smoothScrollToPosition(this.seleteItem + -2 > 0 ? this.seleteItem - 2 : 0);
            }
        }

        public int getCount() {
            return this.mList.size();
        }

        public int getFilterType() {
            return this.filterType;
        }

        public Object getItem(int i) {
            return this.mList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemIdx(int i) {
            return ((Integer) this.listMapIndex.get(i)).intValue();
        }

        public String getNumbers() {
            if (this.seleteItem < this.mList.size()) {
                String str = (String) this.mList.get(this.seleteItem);
                int indexOf = str.indexOf(94);
                if (indexOf != -1) {
                    int indexOf2 = str.indexOf(94, indexOf + 1);
                    if (indexOf2 != -1) {
                        return str.substring(indexOf + 1, indexOf2);
                    }
                }
            }
            return null;
        }

        public int getSelected() {
            return this.seleteItem;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mInflater.inflate(R.layout.hislist, null);
            }
            String str = (String) getItem(i);
            int indexOf = str.indexOf(94);
            if (indexOf != -1) {
                int indexOf2 = str.indexOf(94, indexOf + 1);
                if (indexOf2 != -1) {
                    int indexOf3 = str.indexOf(94, indexOf2 + 1);
                    if (indexOf3 != -1) {
                        int indexOf4 = str.indexOf(94, indexOf3 + 1);
                        if (indexOf4 != -1) {
                            str.substring(0, indexOf);
                            CharSequence substring = str.substring(indexOf + 1, indexOf2);
                            ((TextView) view.findViewById(R.id.tv_hisname)).setText(HistoryFragment.this.historyinterface.getNameOfNumbers((String) substring));
                            ((TextView) view.findViewById(R.id.tv_hisnumber)).setText(substring);
                            CharSequence substring2 = str.substring(indexOf2 + 1, indexOf3);
                            CharSequence substring3 = str.substring(indexOf3 + 1, indexOf4);
                            ((TextView) view.findViewById(R.id.tv_listday)).setText(substring2);
                            ((TextView) view.findViewById(R.id.tv_listtime)).setText(substring3);
                            if (i == this.seleteItem) {
                                view.findViewById(R.id.iv_hisbg).setVisibility(View.VISIBLE);
                            } else {
                                view.findViewById(R.id.iv_hisbg).setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            }
            return view;
        }

        public void setFilterType(int i) {
            if (i != -1) {
                this.filterType = i;
            }
            long connectPhoneMACaddr = HistoryFragment.this.historyinterface.getConnectPhoneMACaddr();
            int size = HistoryFragment.this.historyinterface.getPhoneHistoryList().size();
            this.mList.clear();
            this.listMapIndex.clear();
            for (int i2 = size - 1; i2 >= 0; i2--) {
                String str = (String) HistoryFragment.this.historyinterface.getPhoneHistoryList().get(i2);
                if (this.filterType == 0 || str.charAt(str.length() - 1) - 48 == this.filterType) {
                    long parseLong;
                    str = (String) HistoryFragment.this.historyinterface.getPhoneHistoryList().get(i2);
                    try {
                        parseLong = Long.parseLong(str.substring(0, str.indexOf(94)), 16);
                    } catch (Exception e) {
                        parseLong = -1;
                    }
                    if (parseLong == connectPhoneMACaddr) {
                        this.mList.add(str);
                        this.listMapIndex.add(Integer.valueOf(i2));
                    }
                }
            }
            if (i != -1) {
                setSelectItem(0);
            } else if (this.mList.size() > this.seleteItem) {
                setSelectItem(this.seleteItem);
            } else if (this.mList.size() == 0) {
                setSelectItem(0);
            } else {
                setSelectItem(this.mList.size() - 1);
            }
        }

        public void setSelectItem(int i) {
            this.seleteItem = i;
            notifyDataSetChanged();
            smoothToCenter();
        }
    }

    public HistoryFragment() {
        this.historyinterface = null;
        this.listClick = new C00141();
    }

    private void delete(boolean z) {
        if (this.mAdapter.getCount() != 0 && this.mAdapter.getSelected() >= 0) {
            if (z) {
                this.historyinterface.deleteHistoryAll();
            } else {
                this.historyinterface.deleteHistory(this.mAdapter.getItemIdx(this.mAdapter.getSelected()));
            }
            setFilter(-1);
        }
    }

    private boolean dial() {
        if (!(this.mAdapter.getCount() == 0 || this.mAdapter.getSelected() < 0 || this.historyinterface == null)) {
            String numbers = this.mAdapter.getNumbers();
            if (numbers != null) {
                this.historyinterface.dialOut(numbers);
                return true;
            }
        }
        return false;
    }

    private void init() {
        if (this.historyinterface != null) {
            this.historyinterface.updateHistoryList();
            setFilter(-1);
        }
    }

    private void setFilter(int i) {
        this.mAdapter.setFilterType(i);
        switch (this.mAdapter.getFilterType()) {
            case 1:
                this.outCallView.setSelected(true);
                this.inCallView.setSelected(false);
                this.rejectView.setSelected(false);
                this.outCallView.setEnabled(false);
                this.inCallView.setEnabled(true);
                this.rejectView.setEnabled(true);
            case 2:
                this.outCallView.setSelected(false);
                this.inCallView.setSelected(true);
                this.rejectView.setSelected(false);
                this.outCallView.setEnabled(true);
                this.inCallView.setEnabled(false);
                this.rejectView.setEnabled(true);
            case 3:
                this.outCallView.setSelected(false);
                this.inCallView.setSelected(false);
                this.rejectView.setSelected(true);
                this.outCallView.setEnabled(true);
                this.inCallView.setEnabled(true);
                this.rejectView.setEnabled(false);
            default:
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.historyinterface = (BlueToothInterface) activity;
        } catch (Exception e) {
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.historyincall:
                setFilter(2);
            case R.id.historyoutcall:
                setFilter(1);
            case R.id.historyunanswercall:
                setFilter(3);
            case R.id.historydial:
                dial();
            case R.id.historydelete:
                delete(false);
            case R.id.historydeleteall:
                delete(true);
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.history, viewGroup, false);
        this.outCallView = (ImageView) inflate.findViewById(R.id.historyoutcall);
        this.inCallView = (ImageView) inflate.findViewById(R.id.historyincall);
        this.rejectView = (ImageView) inflate.findViewById(R.id.historyunanswercall);
        this.mListView = (ListView) inflate.findViewById(R.id.historylist);
        this.mListView.setOnItemClickListener(this.listClick);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.historydial);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(R.id.historydelete);
        ImageButton imageButton3 = (ImageButton) inflate.findViewById(R.id.historydeleteall);
        this.outCallView.setOnClickListener(this);
        this.inCallView.setOnClickListener(this);
        this.rejectView.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        this.mAdapter = new HistoryAdapter(getActivity());
        this.mListView.setAdapter(this.mAdapter);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        init();
    }

    public void updateView() {
    }
}
