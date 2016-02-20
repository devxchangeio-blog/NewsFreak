package com.credly.newsfreak.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.credly.newsfreak.R;
import com.credly.newsfreak.model.Stories;
import com.credly.newsfreak.utils.ImageLoader;
/**
 * 
 * @author Karthy
 *
 */
public class SearchAdapter extends ArrayAdapter<Stories>
{

	private static Activity activity;
	private final ArrayList<Stories> names;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;

	public SearchAdapter(Activity a, ArrayList<Stories> name) {

		super(a, R.layout.listview, name);
		activity = a;
		names = name;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	public int getCount()
	{
		return names.size();
	}

	/*
	 * public Object getItem(int position) { return position; }
	 */

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.listview, null);

		TextView title = (TextView) vi.findViewById(R.id.title);
		TextView date = (TextView) vi.findViewById(R.id.date);
		title.setText(names.get(position).getTitle());
		date.setText(names.get(position).getTopic() + " "
				+ names.get(position).getDate_created());

		ImageView image = (ImageView) vi.findViewById(R.id.image);
			imageLoader.DisplayImage(names.get(position).getThumbnails(),
					activity, image);
		return vi;
	}
}