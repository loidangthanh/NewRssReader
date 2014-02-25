package com.example.components;

public class RssItem {
	private String link, title, pubDate, imageThumbLink;

	public RssItem(String title, String link, String pubDate,
			String imageThumbLink) {
		this.title = title;
		this.link = link;
		this.setPubDate(pubDate);
		this.setImageThumbLink(imageThumbLink);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getImageThumbLink() {
		return imageThumbLink;
	}

	public void setImageThumbLink(String imageThumbLink) {
		this.imageThumbLink = imageThumbLink;
	}

}
