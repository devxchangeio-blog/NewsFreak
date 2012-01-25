/**
 * 
 */
package com.credly.newsfreak.model;

import java.util.Date;

/**
 * @author Karthy
 * 
 */
public class Stories
{

	private String description;
	private String title;
	private String url;
	private String topic;
	private String date_created;
	private String thumbnails;
	private String largeImage;
	private String link;
	private Date date;

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	public void setDate_created(String date_created)
	{
		this.date_created = date_created;
	}

	public String getDate_created()
	{
		return date_created;
	}

	public void setThumbnails(String thumbnails)
	{
		this.thumbnails = thumbnails;
	}

	public String getThumbnails()
	{
		return thumbnails;
	}

	public void setTopic(String topic)
	{
		this.topic = topic;
	}

	public String getTopic()
	{
		return topic;
	}

	public void setLargeImage(String largeImage)
	{
		this.largeImage = largeImage;
	}

	public String getLargeImage()
	{
		return largeImage;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getLink()
	{
		return link;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Date getDate()
	{
		return date;
	}

}
