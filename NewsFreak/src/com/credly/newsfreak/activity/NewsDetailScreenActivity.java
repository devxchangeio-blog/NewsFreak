/**
 * 
 */
package com.credly.newsfreak.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.credly.newsfreak.R;
import com.credly.newsfreak.R.id;
import com.credly.newsfreak.libs.ActionBar;
import com.credly.newsfreak.libs.ActionBar.Action;
import com.credly.newsfreak.libs.ActionBar.IntentAction;

/**
 * @author Karthy
 * 
 */
public class NewsDetailScreenActivity extends Activity
{
	static ProgressDialog pd;

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		String screen = getIntent().getStringExtra("url");

		if (screen!=null)
		{
			setContentView(R.layout.newdetailsscreen);
		} else
		{
			setContentView(R.layout.newdetailsscreenwithoutimage);
		}

		final ActionBar actionBar = (ActionBar) findViewById(R.id.detailActionbar);
		actionBar.setTitle((String) getIntent().getStringExtra("stopic"));
		actionBar.setHomeAction(new Action()
		{
			@Override
			public void performAction(View view)
			{
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				goBack();

			}

			@Override
			public int getDrawable()
			{
				return R.drawable.home;
			}
		});

		final Action shareAction = new IntentAction(this, createShareIntent(),
				R.drawable.ic_title_share_default);
		actionBar.addAction(shareAction);
		TextView title = (TextView) findViewById(id.newstitle);
		title.setText((String) getIntent().getStringExtra("title"));

		title.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View view)
			{
				pd = ProgressDialog.show(NewsDetailScreenActivity.this, "",
						"Loading...");
				gotoNextScreen();

			}
		});

		TextView story = (TextView) findViewById(id.newsdescription);
		story.setText((String) getIntent().getStringExtra("story"));

		TextView date = (TextView) findViewById(id.newsdate);
		date.setText((String) getIntent().getStringExtra("topic") + " "
				+ (String) getIntent().getStringExtra("date"));

		if (screen!=null)
		{

			ImageView imageView = (ImageView) findViewById(id.detialImage);
			imageView.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View view)
				{
					pd = ProgressDialog.show(NewsDetailScreenActivity.this, "",
							"Loading...");
					gotoNextScreen();

				}
			});
			InputStream is;
			try
			{
				is = (InputStream) new URL((String) getIntent().getStringExtra(
						"url")).getContent();
				Drawable drawable = Drawable.createFromStream(is, "src name");
				imageView.setImageDrawable(drawable);

			} catch (MalformedURLException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

	private void gotoNextScreen()
	{

		new Thread(new Runnable()
		{
			public void run()
			{
				Intent intent = new Intent(NewsDetailScreenActivity.this,
						NewsWebViewActivity.class);
				intent.putExtra("weburl",
						(String) getIntent().getStringExtra("link"));
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				pd.dismiss();
			}
		}).start();

	}

	/**
	 * 
	 */
	public boolean onCreateOptionsMenu(Menu menu)
	{
		new MenuInflater(getApplication()).inflate(R.menu.menu, menu);
		return (super.onPrepareOptionsMenu(menu));

	}

	private Intent createShareIntent()
	{
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT,
				(String) getIntent().getStringExtra("title") + "\n"
						+ (String) getIntent().getStringExtra("link"));
		return Intent.createChooser(intent, "Share");
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
