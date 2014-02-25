package com.example.parsers;

import java.io.IOException;
import java.text.ParseException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RssParser_VNExpress extends RssParser {
	public RssParser_VNExpress(){
		this.pattern = "EEE, dd MMM yyyy hh:mm:ss Z";
		this.imageTag = "description";
	}

	@Override
	protected String readImageThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException, ParseException {
        String description = readTagText(parser, imageTag);
        int startPos = description.indexOf("src=");
        int endPos = description.indexOf(".jpg");
        if(startPos == -1) return "";
        try{
        	String url = description.substring(startPos+5, endPos+4);
        	return url;
        }catch(IndexOutOfBoundsException e){
        	return "";
        }
        
        
	}
}
