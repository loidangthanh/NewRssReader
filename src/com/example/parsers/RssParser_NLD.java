package com.example.parsers;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RssParser_NLD extends RssParser {

	public RssParser_NLD() {
		pattern = "M/dd/yyyy hh:mm:ss a";
		imageTag = "description";
	}

	@Override
	protected String readTagText(XmlPullParser parser, String tagName)
			throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, tagName);
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			if(tagName =="link"){
				result = result.substring(1);
			}
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, tagName);
		return result;
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
			String url = description.substring(startPos + 5, endPos + 4);
			return url;
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

}
