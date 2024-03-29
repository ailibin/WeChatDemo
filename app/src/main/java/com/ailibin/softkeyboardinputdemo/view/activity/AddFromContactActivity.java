package com.ailibin.softkeyboardinputdemo.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ailibin.softkeyboardinputdemo.Constants;
import com.ailibin.softkeyboardinputdemo.R;
import com.ailibin.softkeyboardinputdemo.adpter.FromContactAdapter;
import com.ailibin.softkeyboardinputdemo.bean.User;
import com.ailibin.softkeyboardinputdemo.common.Utils;
import com.ailibin.softkeyboardinputdemo.net.BaseJsonRes;
import com.ailibin.softkeyboardinputdemo.view.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.juns.health.net.loopj.android.http.RequestParams;

import java.util.List;

//从通讯录添加好友
public class AddFromContactActivity extends BaseActivity implements
        OnClickListener {
	private TextView txt_title;
	private ImageView img_back;
	private ListView mlistview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_listview);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initControl() {
		txt_title = (TextView) findViewById(R.id.txt_title);
		txt_title.setText("通讯录朋友");
		img_back = (ImageView) findViewById(R.id.img_back);
		img_back.setVisibility(View.VISIBLE);
		mlistview = (ListView) findViewById(R.id.listview);
	}

	@Override
	protected void initView() {
		getLoadingDialog("正在获取联系人").show();
		String str_contact = Utils.getValue(this, Constants.ContactMsg);
		RequestParams params = new RequestParams();
		params.put("userlist", str_contact);
		netClient.post(Constants.getContactFriendURL, params,
				new BaseJsonRes() {

					@Override
					public void onMySuccess(String data) {
						List<User> UserInfos = JSON
								.parseArray(data, User.class);
						mlistview.setAdapter(new FromContactAdapter(
								AddFromContactActivity.this, UserInfos));
						getLoadingDialog("正在获取联系人").dismiss();
					}

					@Override
					public void onMyFailure() {
						// TODO Auto-generated method stub
						getLoadingDialog("正在获取联系人").dismiss();
					}
				});
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void setListener() {
		img_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_back:
			Utils.finish(AddFromContactActivity.this);
			break;
		default:
			break;
		}
	}

}
