/**
 * 
 */
package com.credly.newsfreak.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.credly.newsfreak.R;
import com.credly.newsfreak.model.SettingsArray;

/**
 * @author Karthy
 * 
 */
public class SettingsAdapter extends ArrayAdapter<SettingsArray>
{
	private final Activity context;
	private final ArrayList<SettingsArray> names;
	static ImageView imageView;

	public SettingsAdapter(Activity context, ArrayList<SettingsArray> names) {
		super(context, R.layout.settingslistview, names);
		this.context = context;
		this.names = names;
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


	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.settingslistview, null, true);
		TextView title = (TextView) rowView.findViewById(R.id.settinglisttext);
		title.setText(names.get(position).title);
		TextView version = (TextView) rowView.findViewById(R.id.versionText);
		imageView = (ImageView) rowView.findViewById(R.id.rightArrow);
		String str=names.get(position).title;
		if (str.equals("Version"))
		{
			version.setText("1.0.0");
			version.setVisibility(1);
			imageView.setVisibility(0);
		} else
		{
			imageView.setVisibility(1);
			version.setVisibility(0);
		}
		
		

		return rowView;
	}

}