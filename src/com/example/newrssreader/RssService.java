package com.example.newrssreader;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.components.RssItem;
import com.example.parsers.RssParser;
import com.example.parsers.RssParser_24h;
import com.example.parsers.RssParser_CNN;
import com.example.parsers.RssParser_NLD;
import com.example.parsers.RssParser_PCWorld;
import com.example.parsers.RssParser_TGVT;
import com.example.parsers.RssParser_ThanhNien;
import com.example.parsers.RssParser_VNExpress;

public class RssService extends IntentService{

	public static final String ITEMS = "items";
	public static final String RECEIVER = "receiver";
	private static final String TAG = "Service Tag";
	public static final String LINK = "link";
	public RssService() {
		super("RssService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "Service Started");
		List<RssItem> items = null;
		RssParser parser = null;
		ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
		String link = intent.getStringExtra(LINK);
		try {
			if(link.contains("24h.com.vn")){
				parser = new RssParser_24h();
			}else if(link.contains("pcworld.com.vn")){
				parser = new RssParser_TGVT();
			}else if(link.contains("pcworld.com")){
				parser = new RssParser_PCWorld();
			}else if(link.contains("vnexpress.net")){
				parser = new RssParser_VNExpress();
			}else if(link.contains("nld.com.vn")){
				parser = new RssParser_NLD();
			}else if(link.contains("thanhnien.com.vn")){
				parser = new RssParser_ThanhNien();
			}else if(link.contains("cnn.com")){
				parser = new RssParser_CNN();
			}
			URL url = new URL(link);
			URLConnection uc = url.openConnection();
			InputStream is = uc.getInputStream();
			items = parser.parse(is);
		} catch (Exception e) {
			Log.w(TAG, e.getMessage());
		}
		Bundle bundle = new Bundle();
		bundle.putSerializable(ITEMS, (Serializable) items);
		receiver.send(0, bundle);
	}

}
