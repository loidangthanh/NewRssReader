package com.example.components;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newrssreader.R;
import com.loopj.android.image.SmartImageView;

public class PageAdapter extends BaseAdapter {

	private List<PageItem> items;
	private Context context;
	public PageAdapter(Context context, List<PageItem> items){
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
		if(convertView == null){
			convertView = View.inflate(context, R.layout.page_item, null);
			holder = new ViewHolder();
			holder.imageViewPage = (SmartImageView) convertView.findViewById(R.id.imageViewPage);
			holder.textPage = (TextView) convertView.findViewById(R.id.txtPage);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.imageViewPage.setImageResource(items.get(position).getImageID());
		holder.textPage.setText(items.get(position).getText());
		return convertView;
	}
	static class ViewHolder{
		SmartImageView imageViewPage;
		TextView textPage;
	}

}
