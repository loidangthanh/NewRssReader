package com.example.components;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newrssreader.R;
import com.loopj.android.image.SmartImageView;

public class ChannelAdapter extends BaseAdapter {

	private List<ChannelItem> items;
	private Context context;
	public ChannelAdapter(Context context,List<ChannelItem> items){
		this.context = context;
		this.items = items;
	}
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.page_item, null);
			holder = new ViewHolder();
			holder.image = (SmartImageView) convertView.findViewById(R.id.imageViewPage);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.txtPage);
			holder.txtTitle.setTextSize(20);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.image.setImageResource(items.get(position).getPageImageID());
		holder.txtTitle.setText(items.get(position).getTitle());
		return convertView;
	}
	static class ViewHolder{
		TextView txtTitle;
		SmartImageView image;
	}
	

}
