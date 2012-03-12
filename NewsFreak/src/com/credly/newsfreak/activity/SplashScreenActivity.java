/**
 * 
 */
package com.credly.newsfreak.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.credly.newsfreak.R;
import com.credly.newsfreak.utils.PopulateModel;

/**
 * @author Karthy
 * 
 */
public class SplashScreenActivity extends Activity
{
	//private TextView title = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		//title = (TextView) findViewById(id.textView1);
		//title.setText("Credly Inc");
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		creatingSplashScreen();

	}

	private void createFirstScreen()

	{
		startActivity(new Intent(SplashScreenActivity.this,
				NFDemoActivity.class));
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

	}

	private void creatingSplashScreen()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				PopulateModel.populateNewsList("topnews","Top News");
				createFirstScreen();
				SplashScreenActivity.this
						.setProgressBarIndeterminateVisibility(false);
			}
		}).start();
	}
}