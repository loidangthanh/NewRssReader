package com.example.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.util.Xml;

import com.example.components.RssItem;

public abstract class RssParser {
	protected final String ns = null;
	protected String pattern, imageTag;

	public List<RssItem> parse(InputStream inputStream)
			throws XmlPullParserException, IOException, ParseException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(inputStream, null);
			parser.nextTag();
			return readFeed(parser);
		} finally {
			inputStream.close();
		}
	}

	protected List<RssItem> readFeed(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		parser.require(XmlPullParser.START_TAG, null, "rss");
		String title = null;
		String link = null;
		String pubDate = null;
		String imageThumbLink = null;
		List<RssItem> items = new ArrayList<RssItem>();
		while (parser.next() != XmlPullParser.END_DOCUMENT) {
			int et = parser.getEventType();
			if (et != XmlPullParser.START_TAG && et != XmlPullParser.CDSECT) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("title")) {
				title = readTagText(parser, "title");
			} else if (name.equals("link")) {
				link = readTagText(parser, "link");
			} else if (name.equals("pubDate")) {
				pubDate = readPubDate(parser);
			} else if (name.equals(imageTag)) {
				imageThumbLink = readImageThumb(parser);
			}
			if (title != null && link != null && pubDate != null
					&& imageThumbLink != null) {
				RssItem item = new RssItem(title, link, pubDate, imageThumbLink);
				items.add(item);
				title = null;
				link = null;
				pubDate = null;
				imageThumbLink = null;
			}
		}
		return items;
	}

	@SuppressLint("SimpleDateFormat")
	protected String readPubDate(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		if (pattern == null)
			return "";
		SimpleDateFormat dateParser = new SimpleDateFormat(pattern);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm");
		Date pubDate = dateParser.parse(readTagText(parser, "pubDate"));
		return dateFormatter.format(pubDate);
	}

	protected abstract String readImageThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException;

	// For the tags title and link, extract their text values.
	protected String readTagText(XmlPullParser parser, String tagName)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, tagName);
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, tagName);
		return result;
	}

	protected String readAttribute(XmlPullParser parser, String attName)
			throws XmlPullParserException, IOException {
		String result = "";
		result = parser.getAttributeValue(ns, attName);
		parser.nextTag();
		return result;
	}
}
