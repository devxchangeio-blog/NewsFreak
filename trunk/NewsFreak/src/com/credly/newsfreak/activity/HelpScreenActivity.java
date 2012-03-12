/**
 * 
 */
package com.credly.newsfreak.activity;

import com.credly.newsfreak.R;
import com.credly.newsfreak.R.id;
import com.credly.newsfreak.constants.Constants;
import com.credly.newsfreak.libs.ActionBar;
import com.credly.newsfreak.libs.ActionBar.Action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author Karthy
 * 
 */
public class HelpScreenActivity extends Activity
{
	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		final ActionBar actionBar = (ActionBar) findViewById(R.id.aboutScreenActionbar);
		actionBar.setTitle("Help");
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

		TextView topic = (TextView) findViewById(id.aboutdescription);
		topic.setText(Constants.ABOUT_NEW_FREAK);

		TextView date = (TextView) findViewById(id.abouttitle);
		date.setText("New Freak - Version 1.0.0\n\nAbout");

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
