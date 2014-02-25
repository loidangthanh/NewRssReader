package com.example.parsers;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RssParser_PCWorld extends RssParser {
	public RssParser_PCWorld() {
		this.pattern = "EEE, dd MMM yyyy hh:mm:ss Z";
		this.imageTag = "media:thumbnail";
	}

	@Override
	protected String readImageThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		parser.require(XmlPullParser.START_TAG, ns, imageTag);
		String url = readAttribute(parser, "url");
		parser.require(XmlPullParser.END_TAG, ns, imageTag);
		return url;
	}
}
