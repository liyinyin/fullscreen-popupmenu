package com.lion.pupupmenu;

import com.lion.homewatcher.HomeWatcher;
import com.lion.homewatcher.HomeWatcher.OnHomePressedListener;
import com.lion.popupmenu.R;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;

public class FullScreenPopupMenu implements PopupMenuListener {
	private OnItemClickListener mListener;
	private PopupWindow mPopupMenuBg;
	private PopupWindow mPopupMenu;
	private View mView;
	private HomeWatcher mHomeWatcher;

	/**
	 * 
	 * @param context
	 * @param menuItemListener The PopupMenu item click listener.
	 * @param menuAdapter The PopupMenu's (ListView) adapter.
	 * @param view The parentView for PopupMenu when it calls showAtLocation method.
	 */
	public FullScreenPopupMenu(final Activity context,
			final OnItemClickListener menuItemListener, final BaseAdapter menuAdapter, final View parentView) {
		mListener = menuItemListener;
		mView = parentView;
		mHomeWatcher = new HomeWatcher(context);
		mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener(){

			@Override
			public void onHomePressed() {
				dismissPopupMenu();
			}

			@Override
			public void onHomeLongPressed() {
			}
			
		});

		initPopupMenuBg(context);
		initPopupMenu(context, menuItemListener, menuAdapter);
	}

	private void initPopupMenuBg(final Activity context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.defaultpopupmenubg, null);
		mPopupMenuBg = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, false);
		mPopupMenuBg.setFocusable(false);
		mPopupMenuBg.setOutsideTouchable(false);
		mPopupMenuBg.setBackgroundDrawable(null);
		mPopupMenuBg.setAnimationStyle(R.style.default_popup_menu_bg_style);
		mPopupMenuBg.update();
	}

	private void initPopupMenu(final Activity context,
			final OnItemClickListener listener, final BaseAdapter adapter) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.popupmenu, null);
		mPopupMenu = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, false);
		mPopupMenu.setFocusable(true);
		mPopupMenu.setOutsideTouchable(true);
		mPopupMenu.setBackgroundDrawable(null);
		mPopupMenu.setAnimationStyle(R.style.default_popup_menu_style);
		mPopupMenu.update();

		view.setFocusableInTouchMode(true);
		view.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_MENU:
					case KeyEvent.KEYCODE_BACK:
						dismissPopupMenu();
						return true;
					}
				}
				return false;
			}
		});
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				dismissPopupMenu();
				return true;
			}
		});
		ListView listView = (ListView) view.findViewById(R.id.menuList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dismissPopupMenu();
				mListener.onItemClick(parent, view, position, id);
			}
		});
	}

	public void startHomeWatcher() {
		mHomeWatcher.startWatch();
	}

	public void stopHomeWatcher() {
		mHomeWatcher.stopWatch();
	}

	private void dismissPopupMenu() {
		if (mPopupMenu.isShowing()) {
			mPopupMenu.dismiss();
			mPopupMenuBg.dismiss();
		}
	}

	private void reversePopupMenuState() {
		if (mPopupMenu.isShowing()) {
			mPopupMenu.dismiss();
			mPopupMenuBg.dismiss();
		} else {
			mPopupMenuBg.showAtLocation(mView, Gravity.CENTER, 0, 0);
			mPopupMenu.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
		}
	}

	@Override
	public void onHomePressed() {
		dismissPopupMenu();
	}

	@Override
	public void onKeycodeMenuPressed() {
		reversePopupMenuState();
	}

}
