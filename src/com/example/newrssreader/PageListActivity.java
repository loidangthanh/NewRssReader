package com.example.newrssreader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.components.PageAdapter;
import com.example.components.PageItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PageListActivity extends ActivityWithMenu implements
		OnItemClickListener {
	public static final String PAGE = "page";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_list_activity);
		List<PageItem> items = new ArrayList<PageItem>();
		SharedPreferences mPrefs = getSharedPreferences("my_data",
				MODE_PRIVATE);
		Gson gson = new Gson();
		String json = mPrefs.getString("pages", "");
		Type listType = new TypeToken<ArrayList<PageItem>>() {
		}.getType();
		items = gson.fromJson(json, listType);
		
		PageAdapter adapter = new PageAdapter(this, items);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
		
	}

	@SuppressWarnings("unused")
	private void themPage(){
		List<PageItem> ds = new ArrayList<PageItem>();
		ds.add(new PageItem(R.drawable.logo_24h, "24h VN"));
		ds.add(new PageItem(R.drawable.vn_express, "VNExpress"));
		ds.add(new PageItem(R.drawable.nguoilaodong, "Người lao động"));
		ds.add(new PageItem(R.drawable.thanhnien, "Thanh niên"));
		ds.add(new PageItem(R.drawable.tgvt, "Thế giới vi tính"));
		ds.add(new PageItem(R.drawable.pc_world_logo_rgb, "PCWorld"));
		ds.add(new PageItem(R.drawable.cnn, "CNN"));
		SharedPreferences appSharedPrefs = getSharedPreferences("my_data",
				MODE_PRIVATE);
		Editor editor = appSharedPrefs.edit();
		Gson gson = new Gson();
		String json = gson.toJson(ds);
		editor.putString("pages", json);
		editor.commit();
		PageAdapter adapter = new PageAdapter(this, ds);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		PageAdapter adapter = (PageAdapter) parent.getAdapter();
		PageItem item = (PageItem) adapter.getItem(position);
		Intent intent = new Intent(PageListActivity.this, ChannelListActivity.class);
		intent.putExtra(PAGE, item);
		startActivity(intent);
	}
}
