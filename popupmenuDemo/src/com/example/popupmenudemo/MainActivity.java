package com.example.popupmenudemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lion.pupupmenu.FullScreenPopupMenu;
import com.lion.pupupmenu.PopupMenuArrayAdapter;

public class MainActivity extends Activity implements OnItemClickListener {
	private FullScreenPopupMenu mPopupMenu;
	ArrayList<String> array = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		array.add("start");
		array.add("middle");
		array.add("end");

//		PopupMenuArrayAdapter adapter = new PopupMenuArrayAdapter(this, array);
		ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(this, R.layout.defaultpopupmenuitem, R.id.menuName, array);
		
		View parentView = findViewById(R.id.main);
		mPopupMenu = new FullScreenPopupMenu(this, this, menuAdapter, parentView);
		mPopupMenu.startHomeWatcher();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			mPopupMenu.onKeycodeMenuPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		mPopupMenu.stopHomeWatcher();
		super.onDestroy();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, array.get(position), Toast.LENGTH_SHORT).show();
	}

}
