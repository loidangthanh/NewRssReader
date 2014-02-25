package com.example.components;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newrssreader.R;
import com.loopj.android.image.SmartImageView;

public class RssAdapter extends BaseAdapter {

	private List<RssItem> items;
	private Context context;

	public RssAdapter(Context context, List<RssItem> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.rss_item, null);
			holder = new ViewHolder();
			holder.itemTitle = (TextView) convertView.findViewById(R.id.title);
			holder.itemPubDate = (TextView) convertView
					.findViewById(R.id.pubDate);
			holder.imageView = (SmartImageView) convertView
					.findViewById(R.id.rssImage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		RssItem item = items.get(position);
		holder.itemTitle.setText(item.getTitle());
		if (item.getPubDate() == "") {
			holder.itemPubDate.setText("No published");
		} else {
			holder.itemPubDate.setText(item.getPubDate());
		}
		if (item.getImageThumbLink() != "") {
			holder.imageView.setImageUrl(item.getImageThumbLink());
		}else{
			holder.imageView.setImageResource(R.drawable.ic_launcher);
		}
		return convertView;
	}

	static class ViewHolder {
		TextView itemTitle, itemPubDate;
		SmartImageView imageView;
	}

}
