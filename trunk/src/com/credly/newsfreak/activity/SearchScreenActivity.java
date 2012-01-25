/**
 * 
 */
package com.credly.newsfreak.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.credly.newsfreak.R;
import com.credly.newsfreak.R.id;
import com.credly.newsfreak.adapter.LazyAdapter;
import com.credly.newsfreak.model.Stories;
import com.credly.newsfreak.utils.PopulateModel;

/**
 * @author Karthy
 * 
 */
public class SearchScreenActivity extends Activity
{
	private ImageButton searchBtn;
	private ImageButton homeButton;
	private TextView searchTextView;
	private String query;
	public LazyAdapter adapter;
	public ListView listView;
	public ProgressDialog pd;
	public ArrayList<Stories> searchednewsList;

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchscreen);
		searchBtn = (ImageButton) findViewById(id.searchbutton);
		searchTextView = (TextView) findViewById(id.searchEditText);
		listView = (ListView) findViewById(id.searchlist);

		searchBtn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
						.getWindowToken(), 0);

				query = searchTextView.getText().toString();
				pd = ProgressDialog.show(SearchScreenActivity.this, "",
						"Loading...");

				new Thread(new Runnable()
				{
					public void run()
					{
						searchednewsList = PopulateModel.populateNewsList(
								query, "Search News");
						handler.sendEmptyMessage(0);

					}
				}).start();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(
					@SuppressWarnings("rawtypes") AdapterView parent,
					View view, int position, long id)
			{
				Stories stories = (Stories) listView.getAdapter().getItem(
						position);
				pd = ProgressDialog.show(SearchScreenActivity.this, "",
						"Loading...");
				gotoNextScreen(stories);

			}
		});

		homeButton = (ImageButton) findViewById(id.homeButton);
		homeButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				goBack();

			}
		});

	}

	private void gotoNextScreen(final Stories stories)
	{

		new Thread(new Runnable()
		{
			public void run()
			{
				Intent intent = new Intent(SearchScreenActivity.this,
						NewsDetailScreenActivity.class);
				intent.putExtra("title", stories.getTitle());
				intent.putExtra("story", stories.getDescription());
				intent.putExtra("url", stories.getLargeImage());
				intent.putExtra("link", stories.getUrl());
				intent.putExtra("topic", stories.getTopic());
				intent.putExtra("date", stories.getDate_created());
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in_left,
						R.anim.slide_out_left);
				pd.dismiss();
			}
		}).start();

	}

	/**
	 * 
	 * @param screen
	 */
	private void populateListView()
	{

		adapter = new LazyAdapter(this, searchednewsList);
		listView.setAdapter(adapter);

	}

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			pd.dismiss();
			populateListView();

		}
	};

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
