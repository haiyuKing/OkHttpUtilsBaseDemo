package com.why.project.okhttputilsbasedemo.logic;

import android.content.Context;
import com.why.project.okhttputilsbasedemo.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


/**
 * @Created HaiyuKing
 * @Used   登录界面相关接口
 */
public class LoginLogic extends BaseLogic {
	
	private static LoginLogic _Instance = null;

    public static LoginLogic Instance(Context context) {
        if (_Instance == null)
            _Instance = new LoginLogic(context);
        return _Instance;
    }
    private LoginLogic(Context context) {
        this.context = context;
    }

	/**
	 * get请求测试
	 */
	public String getJson(StringCallback callback)
			throws Exception {
		String result = "";
		OkHttpUtils
				.get()
				.url(getSpcyUrl(context.getString(R.string.get_url)))
				.id(100)
				.tag(context)
				.build()
				.execute(callback);
		return result;
	}

}