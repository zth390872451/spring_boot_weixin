package main.com.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 构建http请求:get和post
 */
public class HttpUtil {
	
	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	public static String httpPostRequest(Map<String,Object> param,String url){
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : param.keySet()){
			params.add(new BasicNameValuePair(key,param.get(key).toString()));
		}
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			log.error("http request fail:",e);;
			return "";
		}

	}
	
	public static String httpGetRequest(Map<String,String> param,String url){
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		url = parseGetParam(param,url);
		HttpGet httpget = new HttpGet(url);
		try {
			HttpResponse response = httpclient.execute(httpget);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			log.error("http request fail:",e);;
			return "";
		}

	}
	
	@SuppressWarnings("deprecation")
	public static String parseGetParam(Map<String,String> param,String url){
		StringBuffer buffer = new StringBuffer();
		for(String key : param.keySet()){
			buffer.append(key+"="+URLEncoder.encode(param.get(key))+"&");
		}
		return url+"?"+buffer.toString();
	} 
}
