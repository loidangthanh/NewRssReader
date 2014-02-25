package com.example.parsers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.components.RssItem;

public class RssParser_ThanhNien extends RssParser {

	public RssParser_ThanhNien() {
		pattern = null;
		imageTag = "description";
	}

	@Override
	protected List<RssItem> readFeed(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		parser.require(XmlPullParser.START_TAG, null, "rss");
		String title = null;
		String link = null;
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
			} else if (name.equals(imageTag)) {
				imageThumbLink = readImageThumb(parser);
			}
			if (title != null && link != null && imageThumbLink != null) {
				RssItem item = new RssItem(title, link, "", imageThumbLink);
				items.add(item);
				title = null;
				link = null;
				imageThumbLink = null;
			}
		}
		return items;
	}

	@Override
	protected String readImageThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		String description = readTagText(parser, imageTag);
		int startPos = description.indexOf("src=");
		int endPos = description.indexOf(".jpg");
		if (startPos == -1)
			return "";
		try {
			String url = "http://www.thanhnien.com.vn"
					+ description.substring(startPos + 5, endPos + 4);
			return url;
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

}
