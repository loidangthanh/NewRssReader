package com.example.newrssreader;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.components.ChannelItem;
import com.example.components.RssAdapter;
import com.example.components.RssItem;

public class RssReaderActivity extends ActivityWithMenu implements OnItemClickListener {
	private ProgressBar progressBar;
	private ChannelItem channel;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_reader);
		channel = (ChannelItem) getIntent().getExtras().getSerializable(ChannelListActivity.CHANNEL);
		setTitle(channel.getTitle());
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		getListView().setOnItemClickListener(this);
		startService();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
	}
	@Override
	protected void onStart() {
		super.onStart();
	}
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	@Override
	protected void onStop() {
		super.onStop();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	private void startService() {
		Intent intent = new Intent(this, RssService.class);
		intent.putExtra(RssService.RECEIVER, resultReceiver);
		intent.putExtra(RssService.LINK, channel.getLink());
		startService(intent);
	}
	private final ResultReceiver resultReceiver = new ResultReceiver(new Handler()){
		@SuppressWarnings("unchecked")
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			List<RssItem> items = (List<RssItem>) resultData.getSerializable(RssService.ITEMS);
			if(items != null){
				RssAdapter adapter = new RssAdapter(RssReaderActivity.this, items);
				setListAdapter(adapter);
			}else{
				Toast.makeText(RssReaderActivity.this, "An error occured while downloading Rss", Toast.LENGTH_LONG).show();
			}
			progressBar.setVisibility(View.GONE);
			getListView().setVisibility(View.VISIBLE);
		};
	};
	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//http://www.24h.com.vn/bong-da/man-city-barca-hong-dai-tiec-vi-vua-c48a610623.html
		//http://vnexpress.net/tin-tuc/cong-dong/bo-gd-dt-nen-han-che-tuyen-sinh-cac-nganh-thua-thai-2950629.html
		// http://nld.com.vn/thoi-su-trong-nuoc/can-thuong-phuong-bao-kiem-20140219230031133.htm 
		RssAdapter adapter = (RssAdapter) parent.getAdapter();
		RssItem item = (RssItem) adapter.getItem(position);
		Uri uri = Uri.parse(item.getLink());
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
}
