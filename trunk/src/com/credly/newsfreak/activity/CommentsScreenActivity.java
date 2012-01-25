/**
 * 
 */
package com.credly.newsfreak.activity;

import com.credly.newsfreak.R;
import com.credly.newsfreak.libs.ActionBar;
import com.credly.newsfreak.libs.ActionBar.Action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/**
 * @author Karthy
 * 
 */
public class CommentsScreenActivity extends Activity
{
	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comments);

		final ActionBar actionBar = (ActionBar) findViewById(R.id.commentsScreenActionbar);
		actionBar.setTitle("Comments");
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
