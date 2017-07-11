package com.why.project.okhttputilsbasedemo;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by HaiyuKing
 * Used 自定义Application
 */

public class MyApplication extends Application{

	/**系统上下文*/
	private static Context mAppContext;

	@Override
	public void onCreate() {
		super.onCreate();
		mAppContext = getApplicationContext();

		initOkHttp();//配置OkhttpClient
	}

	/**获取系统上下文：用于ToastUtil类*/
	public static Context getAppContext()
	{
		return mAppContext;
	}

	/**
	 * 配置OkhttpClient
	 */
	private void initOkHttp() {

		CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));//修改成自带的cookie持久化，可以解决程序崩溃时返回到
		//ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

		HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);//设置可访问所有的https网站

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(60000L, TimeUnit.MILLISECONDS)
				.readTimeout(60000L, TimeUnit.MILLISECONDS)
				//配置Log,通过设置拦截器实现，框架中提供了一个LoggerInterceptor，当然你可以自行实现一个Interceptor
				.addInterceptor(new LoggerInterceptor("TAG"))
				//配置持久化Cookie(包含Session)
				.cookieJar(cookieJar)
				.hostnameVerifier(new HostnameVerifier()
				{
					@Override
					public boolean verify(String hostname, SSLSession session) {
						// TODO Auto-generated method stub
						return false;
					}
				})
				//配置Https
				.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
				.build();
		OkHttpUtils.initClient(okHttpClient);
	}

}
