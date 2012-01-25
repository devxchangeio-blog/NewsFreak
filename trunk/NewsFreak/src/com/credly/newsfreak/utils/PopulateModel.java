package com.credly.newsfreak.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.credly.newsfreak.constants.Constants;
import com.credly.newsfreak.model.Stories;
import com.credly.newsfreak.remoteservice.HttpPostService;

/**
 * 
 * @author Karthy
 * 
 */
public class PopulateModel
{
	public static ArrayList<Stories> newsList;
	public static ArrayList<Stories> searchednewsList;

	public static ArrayList<Stories> populateList(String topic, String url,
			boolean isSearchFlag)
	{

		HttpPostService testHttpPost = new HttpPostService();
		newsList = new ArrayList<Stories>();
		String result = null;

		try
		{
			if (isSearchFlag)
			{
				result = testHttpPost.executeSearchHttpPost(topic, url);
			} else
			{
				result = testHttpPost.executeHttpPost(topic, url);
			}

			JSONObject object = (JSONObject) new JSONTokener(result)
					.nextValue();
			JSONArray storiesArray = object.getJSONArray("stories");

			for (int i = 0; i < storiesArray.length(); i++)
			{
				Stories stories = new Stories();
				if (storiesArray.getJSONObject(i).has("title"))
				{
					stories.setTitle(storiesArray.getJSONObject(i)
							.getString("title").toString());
				}

				if (storiesArray.getJSONObject(i).has("description"))
				{
					stories.setDescription(storiesArray.getJSONObject(i)
							.getString("description").toString());
				}

				if (storiesArray.getJSONObject(i).has("url"))
				{
					stories.setUrl(storiesArray.getJSONObject(i)
							.getString("url").toString());
				}

				if (storiesArray.getJSONObject(i).has("date_created"))
				{
					long l = Long.parseLong(storiesArray.getJSONObject(i)
							.getString("date_created").toString());
					stories.setDate_created(TimestampConvertor
							.usingDateFormatterWithTimeZone(l));
				}

				if (storiesArray.getJSONObject(i).has("topic"))
				{
					stories.setTopic(storiesArray.getJSONObject(i)
							.getJSONObject("topic").getString("name"));
				}

				if (storiesArray.getJSONObject(i).has("thumbnails"))
				{
					if (storiesArray.getJSONObject(i)
							.getJSONObject("thumbnails").has("small"))
					{
						stories.setThumbnails(storiesArray.getJSONObject(i)
								.getJSONObject("thumbnails").getString("small"));

					}
					if (storiesArray.getJSONObject(i)
							.getJSONObject("thumbnails").has("large"))
					{
						stories.setLargeImage(storiesArray.getJSONObject(i)
								.getJSONObject("thumbnails").getString("large"));
					}

				}
				/*if (storiesArray.getJSONObject(i).has("link"))
				{
					stories.setLink(storiesArray.getJSONObject(i)
							.getJSONObject("link").toString());
					
				}*/
				
				newsList.add(stories);

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return newsList;
	}

	public static ArrayList<Stories> populateNewsList(String topic, String type)
	{
		if (type == "Top News")
		{
			newsList = populateList(topic, Constants.TOP_NEWS_URL, false);

		} else if (type == "News Wire")
		{
			newsList = populateList(topic, Constants.NEWS_WIRE_URL, false);

		} else if (type == "Search News")
		{
			searchednewsList = populateList(topic, Constants.SEARCH_NEWS_URL,
					true);
			return searchednewsList;
		}
		return newsList;

	}

}
