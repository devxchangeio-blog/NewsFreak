/**
 * 
 */
package com.credly.newsfreak.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.credly.newsfreak.R;

/**
 * @author Karthy
 * 
 */
public class NewsWebViewActivity extends Activity
{
	private WebView webView;
	static ProgressDialog pd;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newswebview);
		pd = ProgressDialog.show(NewsWebViewActivity.this, "", "Loading...");
		webView = (WebView) findViewById(R.id.newsweb);
		gotoNextScreen();

	}

	/** Opens the URL in a browser */
	private void openURL()
	{
		webView.loadUrl((String) getIntent().getStringExtra("weburl"));
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

	private void gotoNextScreen()
	{

		new Thread(new Runnable()
		{
			public void run()
			{
				openURL();
				pd.dismiss();
			}
		}).start();

	}
}
