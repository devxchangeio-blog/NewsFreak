/**
 * 
 */
package com.credly.newsfreak.model;

import java.util.Date;

/**
 * @author Karthy
 * 
 */
public class SearchStories
{
	private String description;
	private String title;
	private String url;
	private String topic;
	private String date_created;
	private String thumbnails;
	private String link;
	private Date date;
	private int commentsCount;

	public String getTitle()
	{
		return title;
	}

	public String getUrl()
	{
		return url;
	}

	public String getTopic()
	{
		return topic;
	}

	public String getDate_created()
	{
		return date_created;
	}

	public String getThumbnails()
	{
		return thumbnails;
	}

	public String getLink()
	{
		return link;
	}

	public Date getDate()
	{
		return date;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setTopic(String topic)
	{
		this.topic = topic;
	}

	public void setDate_created(String date_created)
	{
		this.date_created = date_created;
	}

	public void setThumbnails(String thumbnails)
	{
		this.thumbnails = thumbnails;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setCommentsCount(int commentsCount)
	{
		this.commentsCount = commentsCount;
	}

	public int getCommentsCount()
	{
		return commentsCount;
	}

}
