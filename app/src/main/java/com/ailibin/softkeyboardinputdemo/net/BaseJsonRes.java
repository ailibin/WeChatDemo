package com.ailibin.softkeyboardinputdemo.net;

import com.ailibin.softkeyboardinputdemo.App;
import com.ailibin.softkeyboardinputdemo.Constants;
import com.ailibin.softkeyboardinputdemo.common.Utils;
import com.juns.health.net.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseJsonRes extends JsonHttpResponseHandler {

	@Override
	public void onSuccess(JSONObject response) {
		try {
			String result = response.getString(Constants.Result);
			// System.out.println("返回的值" + response);
			if (result == null) {
				Utils.showLongToast(App.getInstance(), Constants.NET_ERROR);
			} else if (result.equals("1")) {
				String str_data = response.getString(Constants.Value);
				onMySuccess(str_data);
			} else {
				String str = response.getString(Constants.Info);
				Utils.showLongToast(App.getInstance(), str);
				onMyFailure();
			}
		} catch (JSONException e) {
			e.printStackTrace();
			onMyFailure();
		}
	}

	public abstract void onMySuccess(String data);

	public abstract void onMyFailure();
}
