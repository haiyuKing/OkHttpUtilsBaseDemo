package com.why.project.okhttputilsbasedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.why.project.okhttputilsbasedemo.MyApplication;
import com.why.project.okhttputilsbasedemo.R;
import com.why.project.okhttputilsbasedemo.logic.LoginLogic;
import com.why.project.okhttputilsbasedemo.utils.ToastUtil;
import com.why.project.okhttputilsbasedemo.utils.httputil.HttpUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";

	private Button btn_get;
	private TextView tv_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initEvents();
	}

	private void initViews() {
		btn_get = (Button) findViewById(R.id.btn_get);
		tv_show = (TextView) findViewById(R.id.tv_show);
	}

	private void initEvents() {
		btn_get.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getJson();
			}
		});
	}

	private void getJson() {
		if (HttpUtil.isNetworkAvailable(this)) {
			//执行网络请求接口
			try {
				LoginLogic.Instance(MainActivity.this).getJson(new GetJsonStringCallback());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.network_enable));
		}
	}

	/**
	 * get接口的自定义回调函数*/
	public class GetJsonStringCallback extends StringCallback
	{
		@Override
		public void onBefore(Request request, int id)
		{//showProgressDialog("");//显示进度加载框
		}

		@Override
		public void onAfter(int id)
		{//dismissProgressDialog();//隐藏进度加载框
		}

		@Override
		public void onError(Call call, Exception e, int id)
		{
			ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_again));
			Log.w(TAG,"{onError}e="+e.toString());
		}

		@Override
		public void onResponse(String response, int id)
		{
			Log.e(TAG, "onResponse：response="+response);
			switch (id)
			{
				case 100://http
					try {
						if (response != null && !"".equals(response)){
							//解析
							JSONObject responseObj = new JSONObject(response);
							tv_show.setText(responseObj.toString());
						}
						else {
							ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_null_exception));
						}
					} catch (JSONException e) {
						ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_json_exception));
					}catch (Exception e) {
						ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_json_exception));
					} finally {
					}
					break;
				case 101://https
					break;
			}
		}
		@Override
		public void inProgress(float progress, long total, int id)
		{
			Log.e(TAG, "inProgress:" + progress);
		}
	}
}
