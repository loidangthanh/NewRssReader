package com.example.parsers;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RssParser_CNN extends RssParser {

	public RssParser_CNN(){
		pattern = "EEE, dd MMM yyyy hh:mm:ss z";
		imageTag = "description";
	}
	@Override
	protected String readImageThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
		return "";
	}

}
