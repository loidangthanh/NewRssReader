package com.example.newrssreader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.components.ChannelAdapter;
import com.example.components.ChannelItem;
import com.example.components.PageItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ChannelListActivity extends ActivityWithMenu implements
		OnItemClickListener {

	public static final String CHANNEL = "channel";

	@SuppressLint("CommitPrefEdits")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_list_activity);
		PageItem page = (PageItem) getIntent().getExtras().getSerializable(
				PageListActivity.PAGE);
		setTitle("Chọn kênh từ trang " + page.getText());
		if (page.getImageID() == R.drawable.logo_24h) {
			readChannels("24h");
		} else if (page.getImageID() == R.drawable.vn_express) {
			readChannels("vnexpress");
		} else if (page.getImageID() == R.drawable.pc_world_logo_rgb) {
			readChannels("pcworld");
		} else if (page.getImageID() == R.drawable.nguoilaodong) {
			readChannels("nld");
		} else if (page.getImageID() == R.drawable.thanhnien) {
			readChannels("thanhnien");
		} else if (page.getImageID() == R.drawable.tgvt) {
			readChannels("tgvt");
		}else if(page.getImageID()==R.drawable.cnn){
			readChannels("cnn");
		} 
		else {
			Toast.makeText(this, "The channel is being constructed!",
					Toast.LENGTH_LONG).show();
			finish();
		}
		getListView().setOnItemClickListener(this);

	}

	private void readChannels(String key) {
		List<ChannelItem> ds = new ArrayList<ChannelItem>();
		SharedPreferences mPrefs = getSharedPreferences("my_data", MODE_PRIVATE);
		Gson gson = new Gson();
		String json = mPrefs.getString(key, "");
		Type listType = new TypeToken<ArrayList<ChannelItem>>() {
		}.getType();
		ds = gson.fromJson(json, listType);

		ChannelAdapter adapter = new ChannelAdapter(ChannelListActivity.this,
				ds);
		getListView().setAdapter(adapter);
	}

	@SuppressWarnings("unused")
	private void themKenh() {
		List<ChannelItem> ds = new ArrayList<ChannelItem>();
		ds.add(new ChannelItem(R.drawable.cnn, "Top Stories",
				"http://rss.cnn.com/rss/edition.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Business",
				"http://rss.cnn.com/rss/edition_business.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Technology",
				"http://rss.cnn.com/rss/edition_technology.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Science and Space",
				"http://rss.cnn.com/rss/edition_space.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Entertainment",
				"http://rss.cnn.com/rss/edition_entertainment.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "World Sport",
				"http://rss.cnn.com/rss/edition_sport.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Football",
				"http://rss.cnn.com/rss/edition_football.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Golf",
				"http://rss.cnn.com/rss/edition_golf.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "MotorSport",
				"http://rss.cnn.com/rss/edition_motorsport.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Tennis",
				"http://rss.cnn.com/rss/edition_tennis.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Travel",
				"http://rss.cnn.com/rss/edition_travel.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Video",
				"http://rss.cnn.com/rss/cnn_freevideo.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Most Recent",
				"http://rss.cnn.com/rss/cnn_latest.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Business 360",
				"http://rss.cnn.com/rss/edition_business360.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Connect the world",
				"http://rss.cnn.com/rss/edition_connecttheworld.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "In the field",
				"http://rss.cnn.com/rss/edition_inthefield.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Inside the middle earth",
				"http://rss.cnn.com/rss/edition_ime.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "International Desk",
				"http://rss.cnn.com/rss/edition_idesk.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Prism",
				"http://rss.cnn.com/rss/edition_prismblog.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "Quest Means Business",
				"http://rss.cnn.com/rss/edition_questmeansbusiness.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "The Screening Room",
				"http://rss.cnn.com/rss/edition_screeningroom.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "World Sport",
				"http://rss.cnn.com/rss/edition_worldsportblog.rss"));
		ds.add(new ChannelItem(R.drawable.cnn, "UK Election",
				"http://rss.cnn.com/rss/edition_ukelectionblog.rss"));

		SharedPreferences appSharedPrefs = getSharedPreferences("my_data",
				MODE_PRIVATE);
		Editor editor = appSharedPrefs.edit();
		Gson gson = new Gson();
		String json = gson.toJson(ds);
		editor.putString("cnn", json);
		editor.commit();
	}

	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		ChannelAdapter adapter = (ChannelAdapter) parent.getAdapter();
		ChannelItem item = (ChannelItem) adapter.getItem(position);
		Intent intent = new Intent(this, RssReaderActivity.class);
		intent.putExtra(CHANNEL, item);
		startActivity(intent);
	}

}
