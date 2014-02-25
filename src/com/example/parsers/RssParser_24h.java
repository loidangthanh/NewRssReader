package com.example.parsers;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RssParser_24h extends RssParser {
	public RssParser_24h() {
		this.pattern = "yyyy-MM-dd hh:mm:ss";
		this.imageTag = "summaryImg";
	}

	@Override
	protected String readImageThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		String url = readTagText(parser, imageTag);
		return "http://24h.com.vn" + url;
	}

}
