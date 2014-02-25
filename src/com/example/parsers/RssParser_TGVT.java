package com.example.parsers;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RssParser_TGVT extends RssParser {

	public RssParser_TGVT(){
		pattern = "EEE, dd MMM yyyy hh:mm:ss z";
		imageTag = "media:thumbnail";
	}
	@Override
	protected String readImageThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		return "";
	}

}
