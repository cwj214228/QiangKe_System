package datebase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

public class action_now {

	private String xh;
	private String urlxkkh;
	private String __VIEWSTATE;
	private String xkkh;
	private HttpClient httpclient;
	HttpPost post;
	HttpResponse response;
	HttpEntity entity;
	
	public HttpClient getHttpclient() {
		return httpclient;
	}
	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getUrlxkkh() {
		return urlxkkh;
	}
	public void setUrlxkkh(String urlxkkh) {
		this.urlxkkh = urlxkkh;
	}
	public String get__VIEWSTATE() {
		return __VIEWSTATE;
	}
	public void set__VIEWSTATE(String __VIEWSTATE) {
		this.__VIEWSTATE = __VIEWSTATE;
	}
	public String getXkkh() {
		return xkkh;
	}
	public void setXkkh(String xkkh) {
		this.xkkh = xkkh;
	}
	public action_now(String xh, String urlxkkh, String __VIEWSTATE, String xkkh,HttpClient httpclient) {
		super();
		this.xh = xh;
		this.urlxkkh = urlxkkh;
		this.__VIEWSTATE = __VIEWSTATE;
		this.xkkh = xkkh;
		this.httpclient=httpclient;
	}
	
	public void doPost() throws ClientProtocolException, IOException{
		System.out.println(httpclient);
		post=new HttpPost(urlxkkh);
		
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		//params.add(new BasicNameValuePair("xh", xh));
		
		
		params.add(new BasicNameValuePair("__EVENTARGUMENT", " "));
		params.add(new BasicNameValuePair("RadioButtonList1", "0"));
		params.add(new BasicNameValuePair("__VIEWSTATE",__VIEWSTATE));
		
		params.add(new BasicNameValuePair("__EVENTTARGET", "Button1"));
		params.add(new BasicNameValuePair("xkkh", xkkh));
		
		//params.add(new BasicNameValuePair("name", "网站"));
		
		
		post.setEntity(new UrlEncodedFormEntity(params));
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		
		int worker=1;
		while(true){
			response=httpclient.execute(post);
			entity=response.getEntity();
			String pagetext=EntityUtils.toString(entity,"utf-8");
			String result=pagetext.substring(pagetext.indexOf("alert(") + 7, pagetext.indexOf("</script>") - 3);
			
			System.out.println("第"+worker+"抢课结果： "+result);
			if(result.indexOf("成功") > 0){
				break;
			}
			worker++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
