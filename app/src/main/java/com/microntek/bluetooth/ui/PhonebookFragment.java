package com.microntek.bluetooth.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.microntek.bluetooth.BlueToothInterface;
import com.microntek.bluetooth.R;

import java.util.ArrayList;
import java.util.List;

public class PhonebookFragment extends Fragment implements OnClickListener {
    private OnItemClickListener listClick;
    private PhoneBookAdapter mAdapter;
    private ListView mListView;
    private EditText mPhonebookFliter;
    private BlueToothInterface phonebookinterface;

    /* renamed from: com.microntek.bluetooth.ui.PhonebookFragment.1 */
    class C00171 implements TextWatcher {
        C00171() {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable)) {
                PhonebookFragment.this.mPhonebookFliter.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            } else {
                PhonebookFragment.this.mPhonebookFliter.setCompoundDrawablesWithIntrinsicBounds(null, null, PhonebookFragment.this.getActivity().getResources().getDrawable(R.drawable.search_clear), null);
            }
            PhonebookFragment.this.mAdapter.setPhoneBookFilter(editable.toString());
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    /* renamed from: com.microntek.bluetooth.ui.PhonebookFragment.2 */
    class C00182 implements OnTouchListener {
        C00182() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 1:
                    if (((int) motionEvent.getX()) <= view.getWidth() - 38 || TextUtils.isEmpty(PhonebookFragment.this.mPhonebookFliter.getText())) {
                        return false;
                    }
                    PhonebookFragment.this.mPhonebookFliter.setText("");
                    int inputType = PhonebookFragment.this.mPhonebookFliter.getInputType();
                    PhonebookFragment.this.mPhonebookFliter.setInputType(0);
                    PhonebookFragment.this.mPhonebookFliter.onTouchEvent(motionEvent);
                    PhonebookFragment.this.mPhonebookFliter.setInputType(inputType);
                    return true;
                default:
                    return false;
            }
        }
    }

    /* renamed from: com.microntek.bluetooth.ui.PhonebookFragment.3 */
    class C00193 implements OnItemClickListener {
        C00193() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            PhonebookFragment.this.mAdapter.setSelectItem(i);
        }
    }

    private class PhoneBookAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<String> mList;
        private int seleteItem;

        public PhoneBookAdapter(Context context) {
            this.mList = new ArrayList();
            int size = PhonebookFragment.this.phonebookinterface.getPhoneList().size();
            for (int i = 0; i < size; i++) {
                this.mList.add(PhonebookFragment.this.phonebookinterface.getPhoneList().get(i));
            }
            this.mInflater = LayoutInflater.from(context);
            this.seleteItem = 0;
        }

        private String pickupName(String str) {
            int indexOf = str.indexOf(94);
            return (indexOf == -1 || indexOf == 0 || str.indexOf(94, indexOf + 1) == -1) ? null : str.substring(0, indexOf);
        }

        private String pickupNumber(String str) {
            int indexOf = str.indexOf(94);
            if (indexOf != -1) {
                int indexOf2 = str.indexOf(94, indexOf + 1);
                if (indexOf2 != -1) {
                    return str.substring(indexOf + 1, indexOf2);
                }
            }
            return null;
        }

        public int getCount() {
            return this.mList.size();
        }

        public Object getItem(int i) {
            return this.mList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public String getNumber() {
            int count = getCount();
            return (count == 0 || this.seleteItem >= count) ? null : pickupNumber((String) this.mList.get(this.seleteItem));
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str;
            int indexOf;
            Object obj = null;
            View inflate = view == null ? this.mInflater.inflate(R.layout.pblist, null) : view;
            if (i != 0) {
                str = (String) getItem(i - 1);
                indexOf = str.indexOf(94);
                if (indexOf >= 0) {
                    obj = str.substring(0, indexOf);
                }
            }
            str = (String) getItem(i);
            indexOf = str.indexOf(94);
            if (indexOf != -1) {
                int indexOf2 = str.indexOf(94, indexOf + 1);
                if (indexOf2 != -1) {
                    CharSequence substring = str.substring(0, indexOf);
                    CharSequence substring2 = str.substring(indexOf + 1, indexOf2);
                    TextView textView = (TextView) inflate.findViewById(R.id.tv_friendname);
                    View findViewById = inflate.findViewById(R.id.iv_sep);
                    if (obj == null || !substring.equals(obj)) {
                        textView.setText(substring);
                        findViewById.setVisibility(View.VISIBLE);
                    } else {
                        textView.setText("");
                        findViewById.setVisibility(View.INVISIBLE);
                    }
                    ((TextView) inflate.findViewById(R.id.tv_friendnumber)).setText(substring2);
                    textView = (TextView) inflate.findViewById(R.id.tv_firstchar);
                    if (PhonebookFragment.this.phonebookinterface.getPhoneList().size() == this.mList.size()) {
                        char charValue = ((Character) PhonebookFragment.this.phonebookinterface.getPhoneFirstChar().get(i)).charValue();
                        if (charValue < 'A' || charValue > 'Z') {
                            textView.setVisibility(View.INVISIBLE);
                        } else if (i == 0) {
                            textView.setText(Character.toString(charValue));
                            textView.setVisibility(View.VISIBLE);
                        } else if (charValue > ((Character) PhonebookFragment.this.phonebookinterface.getPhoneFirstChar().get(i - 1)).charValue()) {
                            textView.setText(Character.toString(charValue));
                            textView.setVisibility(View.VISIBLE);
                        } else {
                            textView.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        textView.setVisibility(View.INVISIBLE);
                    }
                    if (i == this.seleteItem) {
                        inflate.findViewById(R.id.iv_pbbg).setBackgroundResource(R.drawable.history_listbg);
                        return inflate;
                    }
                    inflate.findViewById(R.id.iv_pbbg).setBackgroundResource(R.drawable.list_selector);
                    return inflate;
                }
            }
            return inflate;
        }

        public void setPhoneBookFilter(String str) {
            int size = PhonebookFragment.this.phonebookinterface.getPhoneList().size();
            this.mList = new ArrayList();
            int i;
            if (str == null || str.equals("")) {
                for (i = 0; i < size; i++) {
                    this.mList.add(PhonebookFragment.this.phonebookinterface.getPhoneList().get(i));
                }
            } else {
                int i2 = 1;
                byte[] bytes = str.getBytes();
                i = 0;
                while (i < bytes.length) {
                    if (bytes[i] < 48 || bytes[i] > 57) {
                        i2 = 0;
                        break;
                    }
                    i++;
                }
                for (int i3 = 0; i3 < size; i3++) {
                    String str2 = (String) PhonebookFragment.this.phonebookinterface.getPhoneList().get(i3);
                    String pickupNumber = i2 != 0 ? pickupNumber(str2) : pickupName(str2);
                    if (pickupNumber != null && pickupNumber.toUpperCase().contains(str.toUpperCase())) {
                        this.mList.add(str2);
                    }
                }
            }
            this.seleteItem = 0;
            notifyDataSetChanged();
        }

        public void setSelectItem(int i) {
            this.seleteItem = i;
            notifyDataSetChanged();
        }

        public int smoothToChar(char c) {
            int size = PhonebookFragment.this.phonebookinterface.getPhoneList().size();
            int i = 0;
            while (i < size) {
                if (((Character) PhonebookFragment.this.phonebookinterface.getPhoneFirstChar().get(i)).charValue() == c) {
                    setSelectItem(i);
                    break;
                }
                i++;
            }
            return i;
        }
    }

    public PhonebookFragment() {
        this.phonebookinterface = null;
        this.listClick = new C00193();
    }

    private boolean dial() {
        String number = this.mAdapter.getNumber();
        if (number == null) {
            return false;
        }
        this.phonebookinterface.dialOut(number);
        return true;
    }

    private void init() {
        if (this.phonebookinterface != null) {
            this.mAdapter = new PhoneBookAdapter(getActivity());
            this.mListView.setAdapter(this.mAdapter);
            this.mPhonebookFliter.setText("");
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mPhonebookFliter = (EditText) getActivity().findViewById(R.id.editPhonebookFilter);
        this.mPhonebookFliter.addTextChangedListener(new C00171());
        this.mPhonebookFliter.setOnTouchListener(new C00182());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.phonebookinterface = (BlueToothInterface) activity;
        } catch (Exception e) {
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pbsearch:
                this.mPhonebookFliter.setText("");
                if (this.phonebookinterface != null) {
                    this.phonebookinterface.searchKeyboard();
                }
            case R.id.pbdial:
                dial();
            case R.id.pbsync:
                this.mPhonebookFliter.setText("");
                if (this.phonebookinterface != null) {
                    this.phonebookinterface.syncPhonebook();
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.phonebook, viewGroup, false);
        this.mListView = (ListView) inflate.findViewById(R.id.phonebooklist);
        this.mListView.setOnItemClickListener(this.listClick);
        ImageButton imageButton = (ImageButton) inflate.findViewById(R.id.pbsync);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(R.id.pbdial);
        ((ImageButton) inflate.findViewById(R.id.pbsearch)).setOnClickListener(this);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        init();
    }

    public void search(char c) {
        int smoothToChar = this.mAdapter.smoothToChar(c);
        if (this.mListView != null && smoothToChar != -1) {
            this.mAdapter.setSelectItem(smoothToChar);
            this.mListView.setSelection(smoothToChar);
        }
    }

    public void updateView() {
        if (this.phonebookinterface != null) {
            this.mAdapter = new PhoneBookAdapter(getActivity());
            Log.i("LIU", "size " + this.phonebookinterface.getPhoneList().size() + " " + this.mAdapter.getCount());
            this.mListView.setAdapter(this.mAdapter);
            if (this.phonebookinterface.getServiceState()) {
                this.mAdapter.setSelectItem(0);
            }
        }
    }
}
