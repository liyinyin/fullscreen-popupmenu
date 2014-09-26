package com.lion.pupupmenu;

import java.util.ArrayList;

import com.lion.popupmenu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopupMenuArrayAdapter extends BaseAdapter {

	private ArrayList<String> mArrayList;
	private Context mContext;

	public PopupMenuArrayAdapter(Context context, ArrayList<String> arrayList) {
		mContext = context;
		mArrayList = arrayList;
	}

	@Override
	public int getCount() {
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.defaultpopupmenuitem, null);
		TextView textView = (TextView) view.findViewById(R.id.menuName);
		textView.setText(((String) getItem(position)));
		return view;
	}

}
