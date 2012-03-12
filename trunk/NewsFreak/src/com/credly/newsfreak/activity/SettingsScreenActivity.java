/**
 * 
 */
package com.credly.newsfreak.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.credly.newsfreak.R;
import com.credly.newsfreak.R.id;
import com.credly.newsfreak.adapter.SettingsAdapter;
import com.credly.newsfreak.libs.ActionBar;
import com.credly.newsfreak.libs.ActionBar.Action;
import com.credly.newsfreak.model.SettingsArray;

/**
 * @author Karthy
 * 
 */
public class SettingsScreenActivity extends Activity
{
	public ListView listView;
	public static ProgressDialog pd;

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		ArrayList<SettingsArray> settingsArray = new ArrayList<SettingsArray>();
		settingsArray.add(new SettingsArray(R.drawable.info, "About"));
		settingsArray.add(new SettingsArray(R.drawable.info, "Version"));
		settingsArray.add(new SettingsArray(R.drawable.info, "Settings"));
		settingsArray.add(new SettingsArray(R.drawable.info, "Rate"));
		settingsArray.add(new SettingsArray(R.drawable.info, "Feedback"));

		final ActionBar actionBar = (ActionBar) findViewById(R.id.settingsScreenActionbar);
		actionBar.setTitle("Settings");
		actionBar.setHomeAction(new Action()
		{
			@Override
			public void performAction(View view)
			{
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();

			}

			@Override
			public int getDrawable()
			{
				return R.drawable.home;
			}
		});

		listView = (ListView) findViewById(id.settingslistview);
		SettingsAdapter adapter = new SettingsAdapter(this, settingsArray);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(
					@SuppressWarnings("rawtypes") AdapterView parent,
					View view, int position, long id)
			{
				Toast.makeText(getApplicationContext(), position+"", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(SettingsScreenActivity.this,
						AboutScreenActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);

			}
		});
	}

	private void goBack()
	{
		finish();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			goBack();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

}
