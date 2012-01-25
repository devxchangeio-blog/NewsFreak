package com.credly.newsfreak.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.credly.newsfreak.R;
import com.credly.newsfreak.R.id;
import com.credly.newsfreak.adapter.LazyAdapter;
import com.credly.newsfreak.constants.Constants;
import com.credly.newsfreak.libs.ActionBar;
import com.credly.newsfreak.libs.ActionBar.Action;
import com.credly.newsfreak.libs.ActionItem;
import com.credly.newsfreak.libs.QuickAction;
import com.credly.newsfreak.model.Stories;
import com.credly.newsfreak.utils.PopulateModel;

/**
 * 
 * @author Karthy
 * 
 */
public class NFDemoActivity extends Activity
{

	private TextView topic;
	static ProgressDialog pd;
	ListView listView;
	static ActionItem aItem;
	private static final int ID_BUSINESS = 3;
	private static final int ID_ENTERTAINMENT = 4;
	private static final int ID_GAMING = 5;
	private static final int ID_LIFESTYLE = 6;
	private static final int ID_OFFBEAT = 3;
	private static final int ID_POLITICS = 4;
	private static final int ID_SCIENCE = 5;
	private static final int ID_SPORTS = 6;
	private static final int ID_TECHNOLOGY = 5;
	private static final int ID_WORLD_NEWS = 6;

	public static String TOP_NEWS = "Top News";
	public static String U_TOP_NEWS = "Up Coming News";
	public static String BUSINESS = "Business";
	public static String ENTERTAINMENT = "Entertainment";
	public static String GAMING = "Gaming";
	public static String LIFESTYLE = "Lifestyle";
	public static String OFFBEAT = "Offbeat";
	public static String POLITICS = "Politics";
	public static String SCIENCE = "Science";
	public static String SPORTS = "Sports";
	public static String TECHNOLOGY = "Technology";
	public static String WORLD_NEWS = "WorldNews";
	public static LazyAdapter adapter;

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		topic = (TextView) findViewById(id.topic);
		topic.setText(Constants.TOP_NEWS);

		final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle("News Freak");

		ActionItem businessItem = new ActionItem(ID_BUSINESS, BUSINESS);
		ActionItem entItem = new ActionItem(ID_ENTERTAINMENT, ENTERTAINMENT);
		ActionItem gameItem = new ActionItem(ID_GAMING, GAMING);
		ActionItem lifeStyleItem = new ActionItem(ID_LIFESTYLE, LIFESTYLE);
		ActionItem offbetaItem = new ActionItem(ID_OFFBEAT, OFFBEAT);
		ActionItem politicsItem = new ActionItem(ID_POLITICS, POLITICS);
		ActionItem scienceItem = new ActionItem(ID_SCIENCE, SCIENCE);
		ActionItem sportsItem = new ActionItem(ID_SPORTS, SPORTS);

		ActionItem techItem = new ActionItem(ID_TECHNOLOGY, TECHNOLOGY);
		ActionItem worldItem = new ActionItem(ID_WORLD_NEWS, WORLD_NEWS);

		final QuickAction quickAction = new QuickAction(this,
				QuickAction.VERTICAL);

		// add action items into QuickAction
		quickAction.addActionItem(businessItem);
		quickAction.addActionItem(entItem);
		quickAction.addActionItem(gameItem);
		quickAction.addActionItem(lifeStyleItem);
		quickAction.addActionItem(offbetaItem);
		quickAction.addActionItem(politicsItem);
		quickAction.addActionItem(scienceItem);
		quickAction.addActionItem(sportsItem);
		quickAction.addActionItem(techItem);
		quickAction.addActionItem(worldItem);

		// Set listener for action item clicked
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener()
				{
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId)
					{
						final ActionItem actionItem = quickAction
								.getActionItem(pos);
						pd = ProgressDialog.show(NFDemoActivity.this, "",
								"Loading...");
						new Thread(new Runnable()
						{
							public void run()
							{

								PopulateModel.populateNewsList(actionItem.getTitle().toLowerCase(),topic.getText().toString());
								handler.sendEmptyMessage(0);

							}
						}).start();

					}
				});

		quickAction.setOnDismissListener(new QuickAction.OnDismissListener()
		{
			@Override
			public void onDismiss()
			{
			}
		});

		actionBar.addAction(new Action()
		{
			@Override
			public void performAction(View view)
			{
				pd = ProgressDialog.show(NFDemoActivity.this, "", "Loading...");

				new Thread(new Runnable()
				{
					public void run()
					{

						PopulateModel.populateNewsList("topnews",topic
								.getText().toString());
						handler.sendEmptyMessage(0);

					}
				}).start();

			}

			@Override
			public int getDrawable()
			{
				return R.drawable.action_bar_refresh;
			}
		});

		actionBar.addAction(new Action()
		{
			@Override
			public void performAction(View view)
			{
				quickAction.show(view);
			}

			@Override
			public int getDrawable()
			{
				return R.drawable.network;
			}
		});

		ActionItem topnewsItem = new ActionItem(1, "Top News");
		ActionItem newswireItem = new ActionItem(2, "News Wire");
		//ActionItem newsroomItem = new ActionItem(3, "News Room");

		final QuickAction qAction = new QuickAction(this,
				QuickAction.HORIZONTAL);

		// add action items into QuickAction
		qAction.addActionItem(topnewsItem);
		qAction.addActionItem(newswireItem);
		//qAction.addActionItem(newsroomItem);

		qAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener()
		{
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId)
			{
				aItem = qAction.getActionItem(pos);
				pd = ProgressDialog.show(NFDemoActivity.this, "", "Loading...");

				topic.setText(aItem.getTitle());

				new Thread(new Runnable()
				{
					public void run()
					{

						PopulateModel.populateNewsList("topnews",
								aItem.getTitle());
						handler.sendEmptyMessage(0);

					}
				}).start();

			}
		});

		quickAction.setOnDismissListener(new QuickAction.OnDismissListener()
		{
			@Override
			public void onDismiss()
			{
				/*
				 * Toast.makeText(getApplicationContext(), "Dismissed",
				 * Toast.LENGTH_SHORT).show();
				 */
			}
		});

		actionBar.setHomeAction(new Action()
		{
			@Override
			public void performAction(View view)
			{
				qAction.show(view);
			}

			@Override
			public int getDrawable()
			{
				return R.drawable.newsletter;
			}
		});

		// topic = (TextView) findViewById(id.topic);
		// topic.setText(Constants.TOP_NEWS);

		listView = (ListView) findViewById(id.list);
		populateListView("topnews");

		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(
					@SuppressWarnings("rawtypes") AdapterView parent,
					View view, int position, long id)
			{
				Stories stories = (Stories) listView.getAdapter().getItem(
						position);
				pd = ProgressDialog.show(NFDemoActivity.this, "", "Loading...");
				gotoNextScreen(stories);

			}
		});

	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context)
	{
		Intent i = new Intent(context, NFDemoActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return i;
	}

	/**
	 * 
	 * @param screen
	 */
	private void populateListView(String screen)
	{
		if (screen.equals("topnews"))
		{
			adapter = new LazyAdapter(this, PopulateModel.newsList);
			listView.setAdapter(adapter);
		}

	}

	/**
	 * 
	 * @param stories
	 */
	private void gotoNextScreen(final Stories stories)
	{

		new Thread(new Runnable()
		{
			public void run()
			{
				Intent intent = new Intent(NFDemoActivity.this,
						NewsDetailScreenActivity.class);
				intent.putExtra("title", stories.getTitle());
				intent.putExtra("story", stories.getDescription());
				intent.putExtra("url", stories.getLargeImage());
				intent.putExtra("link", stories.getUrl());
				intent.putExtra("topic", stories.getTopic());
				intent.putExtra("date", stories.getDate_created());
				intent.putExtra("stopic", topic.getText());
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

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			pd.dismiss();
			populateListView("topnews");

		}
	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		final String topic = item.getTitle().toString().toLowerCase();

		if (topic.equals("help"))
		{
			Intent intent = new Intent(NFDemoActivity.this,
					AboutScreenActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_left);

		} else if (topic.equals("settings"))
		{

			Intent intent = new Intent(NFDemoActivity.this,
					SettingsScreenActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_left);

		}
		return super.onOptionsItemSelected(item);

	}

	@Override
	public void onDestroy()
	{
		adapter.imageLoader.stopThread();
		listView.setAdapter(null);
		super.onDestroy();
	}

}