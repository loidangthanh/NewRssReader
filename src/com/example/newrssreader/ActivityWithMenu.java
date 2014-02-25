package com.example.newrssreader;

import android.app.Dialog;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityWithMenu extends ListActivity {
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.rss_reader, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		final Dialog dialog = new Dialog(ActivityWithMenu.this);
		dialog.setContentView(R.layout.dialog_about);
		TextView txtInfo = (TextView) dialog.findViewById(R.id.txtInfo);
		Button btnDismiss = (Button) dialog.findViewById(R.id.btnDismissDialog);
		btnDismiss.setText("Dismiss");
		btnDismiss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		switch (item.getItemId()) {
		case R.id.menuAbout:
			dialog.setTitle("Thông tin phần mềm");
			txtInfo.setText("Khi số lượng website tin tức ngày càng nhiều\n, việc duyệt Web để tìm những thông tin bạn cần ngày càng mất nhiều thời gian.\n Liệu có tốt hơn không nếu các thông tin và dữ liệu mới nhất được gửi trực tiếp đến bạn,\n thay vì bạn phải tự dò tìm thông tin từ trang web này đến trang web khác?\n Giờ đây, bạn đã có thể sử dụng phần mềm này thông qua một dịch vụ cung cấp thông tin mới gọi là RSS");
			dialog.show();
			break;
		case R.id.menuAuthor:
			dialog.setTitle("Thông tin tác giả");
			txtInfo.setText("Phần mềm đọc Rss được thiết kế bởi Đặng Thành Lợi\nHoàn thành ngày 20/2/2014");
			dialog.show();
			break;
		default:
			break;
		}
		return true;
	}
}
