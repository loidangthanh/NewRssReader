package com.example.components;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChannelItem implements Serializable {
	private int pageImageID;
	private String title;
	private String link;
	public ChannelItem(int pageImageID,String title,String link){
		this.pageImageID = pageImageID;
		this.setTitle(title);
		this.setLink(link);
	}
	public int getPageImageID() {
		return pageImageID;
	}
	public void setPageImageID(int pageImageID) {
		this.pageImageID = pageImageID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
