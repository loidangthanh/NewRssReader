package com.example.components;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageItem implements Serializable {
	private int imageID;
	private String text;
	public PageItem(int imageID,String text){
		this.imageID = imageID;
		this.text = text;
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
